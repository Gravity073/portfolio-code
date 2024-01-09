<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var context = window.location.pathname.substring(0,window.location.pathname.indexOf("/",2));
	location.href = context + "/boardList.do";
</script>