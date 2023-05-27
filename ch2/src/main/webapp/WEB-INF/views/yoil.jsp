<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>

<body>
<!-- Java code에서 출력 부분  -->
	<h1>year=<%=request.getParameter("year") %></h1>


	<!-- EL이라고 한다 -->
	<p>${myDate.year}년 ${myDate.month}월 ${myDate.day}일은 ${yoil}요일입니다!!~~ </p>
	
</body>
</html>
