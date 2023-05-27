<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>id=${user.id}</h1>
<h1>pwd=${user.pwd}</h1>
<h1>name=${user.name}</h1>
<h1>email=${user.email}</h1>
<h1>birth=${user.birth}</h1>
<h1>hobby=${user.hobby}</h1>
<h1>sns=${user.sns}</h1>





<!--param을 user로 바꿔 확인해볼 것  -->
<%-- <h1>sns=${userValues.sns[0]}</h1>
	 <h1>sns=${userValues.sns[1]}</h1>
	 <h1>sns=${userValues.sns[2]}</h1>  --%>


<!-- HTML에서 값을 여러개 전송할때 EL에서는 paramValues.sns[]을 사용한다 
 차례대로 kakao facebook instagram -->
</body>
</html>