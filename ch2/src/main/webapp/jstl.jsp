<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 접두사 c : jstl.core 라이브러리를 사용한다는 의미  -->

<html>
<head>
	<title>JSTL</title>
</head>
<body>

<!-- to라는 변수의 값을 10으로 설정 
 왜?? 이렇게 할까 EL은 lv 사용할 수 없다 따라서 pageContext 저장소에 K,V로 저장한다 key:to value:10 
 scope="page"는 생략-->
 
<c:set var="to"   value="10"/>
<c:set var="arr"  value="10,20,30,40,50,60,70"/> 


<c:forEach var="i" begin="1" end="${to}">
	${i}
</c:forEach>
<br>

<!--  c:foreach items="S{리스트가 받아올 배열이름}"
        var="for문 내부에서 사용할 변수"
        varStatus="상태용 변수" -->
<c:if test="${not empty arr}">
	<c:forEach var="elem" items="${arr}" varStatus="status">
		${status.count}.arr[${status.index}]=${elem}<BR>
	</c:forEach>
</c:if>
	<!--count는 1부터 index는 0부터 출력화면 참고  -->
	

<c:if test="${param.msg != null}">
	msg=${param.msg} 
	msg=<c:out value="${param.msg}"/>
	<!-- c:out은 tag로 해석하지 않는다 script 공격을 당하지 않는다 (37line에 p 태그를 사용해봐라
	요청파라미터는 msg -->
</c:if>
<br>


<c:if test="${param.msg == null}">메시지가 없습니다.<br></c:if>
<c:set var="age" value="${param.age}"/>
<c:choose>
	<c:when test="${age >= 19}">성인입니다.</c:when>
	<c:when test="${0 <= age && age < 19}">성인이 아닙니다.</c:when>
	<c:otherwise>값이 유효하지 않습니다.</c:otherwise>
</c:choose>
<br>

<c:set var="now" value="<%=new java.util.Date() %>"/>
Server time is~ <fmt:formatDate value="${now}" type="both" pattern="yyyy/MM/dd HH:mm:ss"/>	
</body>
</html>



