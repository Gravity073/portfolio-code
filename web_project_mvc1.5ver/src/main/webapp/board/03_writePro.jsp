<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check eq 1}">
	<script>
		alert("게시글을 등록하였습니다.");
		var context = window.location.pathname.substring(0,window.location.pathname.indexOf("/",2));
		location.href = context + "/boardList.do";
	</script>	
</c:if>
<c:if test="${check eq -1}">
	<script>
		alert("게시글 등록을 실패하였습니다.");
		var context = window.location.pathname.substring(0,window.location.pathname.indexOf("/",2));
		location.href = context + "/writePost.do";
	</script>	
</c:if>
<c:if test="${check eq 0}">
	<script>
		alert("입력이 안된 사항이 있습니다.");
		var context = window.location.pathname.substring(0,window.location.pathname.indexOf("/",2));
		location.href = context + "/writePost.do";
	</script>	
</c:if>


