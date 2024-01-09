package board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {
	
	private BoardDAO() {}
	private static BoardDAO instance;
	public static BoardDAO getInstance() {
		if(instance == null) {
			instance = new BoardDAO();
		}
		return instance;
	}
	
	private PreparedStatement psmt;
	private Connection con;
	private ResultSet rs;
	
	// DB 로딩, 연동
	public Connection getCon() {
		String dbUrl = "jdbc:mysql://localhost:3306/web_board";
		String dbId = "root";
		String dbPd = "1234";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(dbUrl, dbId, dbPd);
			System.out.println("DB 연결 완료");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	// interface close
	public void closeAll() {
		if(con!=null) {try {con.close();} catch(Exception e) {}}
		if(psmt!=null) {try {psmt.close();} catch(Exception e) {}}
		if(rs!=null) {try {rs.close();} catch(Exception e) {}}
	}
	
	// DB에 들어있는 게시글 제일 마지막 번호 + 1
	public int getNext() {
		try {
			con = getCon();
			String sql = "SELECT num FROM board ORDER BY num DESC";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		}catch(Exception e) {
			System.out.println("getNext() 오류");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return -1;
	}
	
	// 날짜, 시간 추출 함수
	public String getDate() {

		try {
			con = getCon();
			String sql = "SELECT NOW()";
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			System.out.println("getDate() 오류");
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return "";
	}
	
	// 게시글 작성
	public int writePost(String title, String userId, String content) {
		int nextNum = getNext();
		String nowDate = getDate();
		
		try {
			con = getCon();
			
			String sql = "INSERT INTO board VALUES(?,?,?,?,?,1)";
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, nextNum);
			psmt.setString(2, title);
			psmt.setString(3, userId);
			psmt.setString(4, nowDate);
			psmt.setString(5, content);
			
			return psmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("writePost() 오류");
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return -1; // DB 오류
	}
	
	// 전체 게시글 수
	public int allPostNum() {
		try {
			con = getCon();
			String sql = "SELECT count(*) FROM board";
			
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return -1; // 오류 발생 
	}
	
	// 보여줄 게시글 목록
	public ArrayList<BoardDTO> getPostList(int start, int end) {
		ArrayList<BoardDTO> postList = new ArrayList<BoardDTO>();
		
		try {
			con = getCon();
			String sql = "SELECT * FROM board ";
			sql += "WHERE NUM >= ? AND NUM <= ? ";
			sql += "order by num desc";
			
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO post = new BoardDTO();
				post.setNum(rs.getInt(1));
				post.setTitle(rs.getString(2));
				post.setUserId(rs.getString(3));
				post.setRegDate(rs.getString(4));
				
				postList.add(post);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return postList;
	}
	
	// 하나의 게시글 가져오기(게시글 보기)
	public BoardDTO getPost(int i) {
		try {
			con = getCon();
			String sql = "SELECT * FROM board WHERE num=?";
			
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, i);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				BoardDTO post = new BoardDTO();
				post.setNum(rs.getInt(1));
				post.setTitle(rs.getString(2));
				post.setUserId(rs.getString(3));
				post.setRegDate(rs.getString(4));
				post.setContent(rs.getString(5));
				post.setAvailable(rs.getInt(6));
				
				return post;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
		return null;
	}

	// 더미 추가
	public void addDummyPost() {
		
		for(int i=0; i<10; i++) {
			try {
				int nextNum = getNext();
				String nowDate = getDate();

				con = getCon();
				
				String sql = "INSERT INTO board VALUES(?,?,?,?,?,1)";
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, nextNum);
				psmt.setString(2, "제목" + nextNum);
				psmt.setString(3, "USER" + nextNum);
				psmt.setString(4, nowDate);
				psmt.setString(5, "더미 게시글 " + nextNum + "번");
				
				psmt.executeUpdate();
			}catch(SQLException e) {
				System.out.println("writePost() 오류");
				e.printStackTrace();
			}finally {
				closeAll();
			}
		}
	}
	
	// 게시글 전체 삭제
	public void allDelete() {
		try {
			con = getCon();
			String sql = "DELETE FROM board";
			
			psmt = con.prepareStatement(sql);
			psmt.executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll();
		}
	}
	
	// 게시글 수정
	public int updatePost(String title, String content, String num) {
		
		try {
			con = getCon();
			
			String sql = "UPDATE board SET title=?, content=? ";
			sql += "WHERE num=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setString(3, num);
			
			return psmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("updatePost() 오류");
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return -1; // DB 오류
	}
	
	// 게시글 삭제
	public int deletePost(String num) {
		
		try {
			con = getCon();
			
			String sql = "DELETE FROM board ";
			sql += "WHERE num=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, num);
			
			return psmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("deletePost() 오류");
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return -1; // DB 오류
	}
	
}

