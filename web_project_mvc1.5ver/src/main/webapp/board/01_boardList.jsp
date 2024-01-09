<%@page import="board.model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/boardList.css">
</head>
<body>

	<h2>게시판 목록</h2>
	<table border="1">
		<tr>
			<td colspan="4" style="text-align : right">
				<button onclick="location.href='${contextPath}/addDummyPost.do'">더미 추가</button>&nbsp;
				<button onclick="location.href='${contextPath}/allDelete.do'">전체 삭제</button>&nbsp;
				<button onclick="location.href='${contextPath}/writePost.do'" >글쓰기</button>	
			</td>
		</tr>
		<tr>
			<td width="50px">번호</td>
			<td width="320px">제목</td>
			<td width="100px">작성자</td>
			<td width="140px">작성일</td>
		</tr>
		<c:forEach var="i" begin="0" end="${onePagePost-1}">
			<tr>
				<td>${post[i].getNum()}</td>
				<td><a href="${contextPath }/boardInfo.do?num=${post[i].getNum()}">${post[i].getTitle()}</a></td>
				<td>${post[i].getUserId()}</td>
				<td>${post[i].getRegDate()}</td>
			</tr>
		</c:forEach>
	</table>
	
	<br>
	<div align="center">
		<c:if test="${frontPage < startPageNum}">
			<a href="${contextPath}/boardList.do?startPageNum=${startPageNum-frontPage}&startPost=${onePagePost*(startPageNum-2)+1}">[이전]</a>
		</c:if>
		
		<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
			<a href="${contextPath}/boardList.do?startPost=${onePagePost*(i-1)+1}&startPageNum=${startPageNum}">[${i}]</a>
		</c:forEach>
		
		<c:if test="${allPage > endPageNum}">
			<a href="${contextPath}/boardList.do?startPageNum=${endPageNum+1}&startPost=${onePagePost*endPageNum+1}">[이후]</a>
		</c:if>
	</div>
	
</body>
</html>