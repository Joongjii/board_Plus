package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
	
	// @ExceptionHandler
	// 반복되는 try catch 문을 생략할 수 있다
	// Exception 클래스의 예외들이 발생했을때 error.jsp를 반환하도록 한다

@Controller
public class ExceptionController {
	
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception ex, Model m) {
		m.addAttribute("ex", ex);
		return "/error";
	}

	//	하나의 EH로 여러개의 에러를 처리하고 싶다면 배열로 에러를 작성해주면 된다.
	// EH의 m은 error.jsp로 전달하기 위해서만 사용할 뿐
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String catcher1(Exception ex, Model m) {	
		System.out.println("EC.java에 있는 catcher()가 처리했습니다");
		System.out.println("@EH.m="+m);
		//m.addAttribute("ex", ex);
		// 만약에 error.jsp에 <% page isErrorPage="true">라고 입력한다면
		// m.addAttribute("ex", ex)를 사용하지 않아도 기본 객체 exception을 쓸 수 있다
		return "/error";
	}
	
	@RequestMapping("/ex1")
	public String main(Model m) throws Exception {
		// Model을 선언하면 Model 사용할수 있다 허나 EH에 선언한 모델 객체하고는 다른 객체이다
		m.addAttribute("msg", "메세지 from EC.java의 main() 메서드");
		throw new Exception("예외1가 발생했습니다");
	}
	
	@RequestMapping("/ex2")
	public String main2() throws Exception {
		
			throw new FileNotFoundException("예외2가 발생했습니다");
		 // throw new NullPointerException("예외2가 발생했습니다");
	     // 콘솔 출력 값이 NULL이라 NPE가 에러를 잡았지만 FNFE의 경우에는 조상인 E가 에러를 잡는다
	}
	
	
}
