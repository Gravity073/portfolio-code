<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
		<h2>게시글</h2>
		<table border="1" style="text-align : center;">
			<tr height="50px">
				<td width="60px">글번호</td>
				<td width="50px">${post.getNum()}</td>
				<td width="60px">작성자</td>
				<td width="100px">${post.getUserId() }</td>
				<td width="60px">작성일</td>
				<td width="140px">${post.getRegDate() }</td>
			</tr>
			<tr height="40px">
				<td>제목</td>
				<td colspan="5">${post.getTitle() }</td>
			</tr>
			<tr height="200px">
				<td>내용</td>
				<td colspan="5">
					${post.getContent()}
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<button onclick="location.href='${contextPath}/updatePost.do?num=${post.getNum()}'">수정하기</button>
					<button onclick="location.href='${contextPath}/deletePost.do?num=${post.getNum()}'">삭제하기</button>
					<button onclick="location.href='${contextPath}/boardList.do'">목록보기</button>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>