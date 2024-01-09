<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath }/css/02_writePost.css" />
<title>Insert title here</title>
</head>
<body>
	<h2>게시판 글쓰기 양식</h2>
	<form action="writePro.do">
	<table border="1">
		<tr >
			<td><input type="text" placeholder="글 제목" name="title" maxlength="50" id="title"></td>
		</tr>
		<tr>
			<td><textarea placeholder="글 내용" name="content" maxlength="2048"></textarea></td>
		</tr>
		<tr>
			<td align="center">
				<input type="submit" value="게시하기">
				<input type="button" value="목록보기" onclick="location.href='${contextPath}/boardList.do'">
			</td>
		</tr>
	</table>
	</form>
</body>
</html>