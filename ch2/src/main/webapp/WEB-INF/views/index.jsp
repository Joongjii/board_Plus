<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<c:set var="loginOutLink" value="${sessionScope.id==null ? 'login/login' :'/login/logout'}"/>
<c:set var="loginOut" value="${sessionScope.id==null ? 'Login' :'Logout'}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>    
</head>
<body>
<div id="menu">
	<!-- 해당 섹션을 누르면 맵핑된 URL로 이동  -->
	<ul>
	    <li id="logo">fastcampus</li>
	    <li><a href="<c:url value='/'/>">Home</a></li>
	    <li><a href="<c:url value='/board/list'/>">Board</a></li>
	   <%--  <li><a href="<c:url value='/login/login'/>">login</a></li> 
	  					 logout을 만들어주기 위해 아래와 같이 변경 후 맨 위에 코드 추가 --%> 
	    <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li> 
	    <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
	    <li><a href=""><i class="fas fa-search small"></i></a></li>
	</ul> 
</div>
<div style="text-align:center">
	<h1>This is HOME</h1>
	<h1>This is HOME</h1>
	<h1>This is HOME</h1>
</div>