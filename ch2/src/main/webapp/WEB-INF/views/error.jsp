<%-- <%@ page contentType="text/html;charset=utf-8"%> --%>
<%@ page contentType="text/html;charset=utf-8" isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>error.jsp</title>
</head>
<body>
<h1>예외가 발생했습니다.</h1>
<%-- 발생한 예외 : ${ex}<br>
예외 메시지 : ${ex.message}<br> --%>
발생한 예외 : ${pageContext.exception}<br>
예외 메시지 : ${pageContext.exception.message}<br>
<!--기본 객체를 쓸 때는 pageContext를 붙여줘야 한다  -->
<ol>
<c:forEach items="${pageContext.exception.stackTrace}" var="i">
<%-- <c:forEach items="${ex.stackTrace}" var="i"> --%>
	<li>${i.toString()}</li>
</c:forEach>
</ol>
</body>
</html>
