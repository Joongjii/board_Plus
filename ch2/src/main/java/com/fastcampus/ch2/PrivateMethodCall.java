package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main(); //private이라서 외부 호출 불가
	
		//Reflection API를 사용 - 클래스 정보를 얻고 다룰 수 있는 강력한 기능 제공
		//java.lang.reflect패키지를 제공
		
		//Hello 클래스의 Class 객체(클래스의 정보를 담고 있는 객체)를 얻어온다.
		//Class == 설계도 : 객체를 만들 수 있고 객체가 어떤 멤버를 가지고 있는지도 알 수 있다
		Class helloOfClass = Class.forName("com.fastcampus.ch2.Hello");
		Hello hello = (Hello) helloOfClass.newInstance(); //Class객체가 가진 정보로 객체 생성
		
		Method main = helloOfClass.getDeclaredMethod("main"); 
		main.setAccessible(true); //private인 main()을 호출가능하게 한다.
		
		main.invoke(hello); //hello.main();
	
		
		
	}
}
