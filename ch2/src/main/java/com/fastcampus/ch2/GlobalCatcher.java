package com.fastcampus.ch2;

import java.io.FileNotFoundException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@ContorllerAdvice란?
//모든 컨트롤러에서 발생하는 예외를 처리하게 된다(전역 예외 처리작성 가능)

//@ControllerAdvice() -- 아래 처럼 지정된 패키지에서 발생한 예외만 처리 가능
@ControllerAdvice("com.fastcampus.ch3")
//@ControllerAdvice("com.fastcampus.ch2")
public class GlobalCatcher {
	@ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
	public String catcher2(Exception ex, Model m) {
		m.addAttribute("ex", ex);
		return "/error";
	}

//	하나의 EH로 여러개의 에러를 처리하고 싶다면 배열로 에러를 작성해주면 된다.
	@ExceptionHandler(Exception.class)
	public String catcher1(Exception ex, Model m) {
		m.addAttribute("ex", ex);
		return "/error";
	}
}
