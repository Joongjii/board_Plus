package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


//객체 생성을 해야하는데 어떻게 인스턴스메서드인 main으로 실행이 가능할까?
//이유는 톰캣 내부에서 객체를 생성한 후 메서드를 호출하기 때문이다
//그러면 static도 가능한데 왜?? 인스턴스메서드로 실행을 선호할까?
//왜냐면 인스턴스메서드는 iv와 cv를 모두 사용 가능하다
//또한 public이 아닌 private도 사용이 가능하다
//왜?? RequestMapping은 외부에서 호출 가능하겠다는 의미이기에 접근제어자는 상관없다
//단 외부(클래스)에서 main함수를 호출하는 것은 불가능하다
//Reflection API를 사용 - 클래스 정보를 얻고 다룰 수 있는 강력한 기능 제공
//java.lang.reflect패키지를 제공


//1. 원격 호출 가능한 프로그램으로 등록
@Controller
public class Hello {
	int iv = 10;
	static int cv = 20;
	
	
	@RequestMapping("/helloSpring")
	// 2. URL과 메서드를 연결
	private void main() { //인스턴스 메서드 - iv, cv를 둘 다 사용가능
		System.out.println("Hello - private");
		System.out.println(cv); //OK
		System.out.println(iv); //OK
	}
	
	public static void main2()	{
		System.out.println(cv); //OK
		//System.out.println(iv);
	}
	
	
	
	
	
	
	
	
	
	

}
