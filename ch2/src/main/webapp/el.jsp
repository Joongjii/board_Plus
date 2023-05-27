<%@ page contentType="text/html;charset=utf-8"%>>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.fastcampus.ch2.*" %>
<%	//클래스 영역 
	Person person = new Person();
	request.setAttribute("person", person);
	//jsp 기본 객체인 request(저장소)에 setAttribute를 이용하여 
	//attribute가 가진 Map에 K,V로 저장 
	request.setAttribute("name", "남궁성");   
	request.setAttribute("list", new java.util.ArrayList()); // 빈 list를 저장
%>
<html>  
<head>   
	<title>EL</title>   
</head>  
<body>   
<!-- 출력 부분 -->

person.getCar().getColor() = <%=person.getCar().getColor()%> <br>
person.getCar().getColor() = ${person.getCar().getColor()} <br>
person.getCar().getColor() = ${person.car.color} <br> 
<!-- person은 lv이다 그래서 request객체에 person을 저장해줘야한다
그렇지 않으면 EL에서 쓸 수가 없다 그러나 EL을 쓰면 22line처럼 간단하게 쓸 수 있다 -->   
name = <%=request.getAttribute("name")%> <br>   
name = ${requestScope.name} <br>
name=${name} <br>
<!--requestScope는 request 객체의 attribute의 Map이름이다 
27line은 requestScope을 생략 가능 Scope이 좁은->넓은 곳을 뒤져서 찾는다  -->

id=<%=request.getParameter("id")%> <br>
<%-- id=${pageContext.request.getParameter("person")} <br> --%>
id=${param.id} <br>
<!-- EL에서는 lv를 쓸 수 없기때문에 pageContext를 써줘야한다
	 이것이 pageContext가 필요한 이유이다 
	 id라는 파라미터가 없기 때문에 null
	 El은 Null을 출력하지 않는다-->

"1"+1 = ${"1"+1} <br>
"1"+="1" = ${"1"+="1"} <br>
"2">1 = ${"2">1} <br>   
<!-- 	"1"+1 = 2
		"1"+="1" = 11
		"2">1 = true   --  js랑 유사하다-->
		
		
null = ${null}<br>
null+1 = ${null+1} <br>
null+null = ${null+null} <br>
"" + null = ${""+null} <br>  
""-1 = ${""-1} <br>  
<!-- null은 계산에서는 0으로 바뀐다(""도 0) -->


empty null=${empty null} <br>
empty list=${empty list} <br>
<!-- true -->
null==0 = ${null==0} <br>
null eq 0 = ${null eq 0} <br>
<!-- false -->

name == "남궁성"=${name=="남궁성"} <br>
name != "남궁성"=${name!="남궁성"} <br>
name eq "남궁성"=${name eq "남궁성"} <br>  
name ne "남궁성"=${name ne "남궁성"} <br>  
name.equals("남궁성")=${name.equals("남궁성")} <br>   
</body>
</html>
