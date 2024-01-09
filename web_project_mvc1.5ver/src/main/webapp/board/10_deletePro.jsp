<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${judg eq 1 }">
		<h3>게시글 삭제 성공!</h3>
	</c:if>
	<c:if test="${judg eq -1 }">
		<h3>게시글 삭제 실패!</h3>
	</c:if>
	<button onclick="location.href='${pageContext.request.contextPath}/boardList.do'">목록보기</button>
</body>
</html>