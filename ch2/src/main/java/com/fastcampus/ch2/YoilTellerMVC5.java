package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@ModelAttribute 적용 대상을 Model의 속성으로 자동 추가해주는 에너테이션 -- K, V로 모델에 저장
//반환 타입 또는 컨트롤러 메서드의 매개변수에 적용 가능

//참조형 매개변수에는 @ModelAttribute를 생략 가능
//반대로 기본형과 String에는 @RequestParam 생략 가능


@Controller
public class YoilTellerMVC5 { //http:localhost/ch2/getYoilMVC?year=2023&month=2&day=27
	
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC5")
	// public String main(@ModelAttribute("myDate") MyDate date, Model model) throws IOException {
	// ("myDate") 생략 가능 -- 타입의 첫 글자를 소문자로 하여 K 값에 저장 V는 date의 주소값
	// 메서드의 매개변수에 @를 붙인 케이스
	   public String main(@ModelAttribute MyDate date, Model model) throws IOException {	

		
			// 1. 유효성 검사
			if(!isValid(date))
				return "yoilError";
			
		//  Model Attribute를 이용하면 이 부분은 필요가 없다
//			// 2.요일 계산
//			char yoil = getYoil(date);
//		
//			// 3.계산한 결과를 모델에 저장	
//			//K를 myDate로 V를 date로 저장
//			model.addAttribute("myDate",date);
//			model.addAttribute("yoil",yoil);
			
			
		return "yoil"; // /WEB-INF/views/yoil.jsp 
		//반환타입을 void로 하게되면 맵핑된 주소의 .jsp로 View가 호출된다
		
	}

	private boolean isValid(MyDate date) {
		return isValid(date.getYear(),date.getMonth(),date.getDay());
	}
	//메서드의 반환 타입에 @를 붙인 케이스
	//반환타입의 호출 결과를 모델에 저장 yoil-키 '수'라는 요일은-값(value)
	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		return getYoil(date.getYear(),date.getMonth(),date.getDay());
	}

	private boolean isValid(int year, int month, int day) {
			return true;
		}
	
	private char getYoil(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day); //월은 -1 해줘야 한다
		System.out.println(cal);
	
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //calendar가 가르키는(의미하는) 특정 날짜가 무슨 요일인지 알기 위해 쓰인다.
	return " 일월화수목금토".charAt(dayOfWeek);
}

}
