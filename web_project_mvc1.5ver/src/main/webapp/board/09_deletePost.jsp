<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>정말로 삭제하시겠습니까?</h3>
	<form action="deletePro.do">
		<input type="text" placeholder="삭제하시려면 '확인'을 입력해주세요." name="check">
		<input type="hidden" name="num" value="${num }">
		<input type="submit" value="확인" >
	</form>
</body>
</html>