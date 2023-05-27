package com.fastcampus.ch2;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class YoilTellerMVC4 { //http:localhost/ch2/getYoilMVC?year=2023&month=2&day=27
	
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		ex.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC4")
	public String main( MyDate date, Model model) throws IOException {
//	public void main(int year, int month, int day, Model model) throws IOException {
		//매개 변수와 모델 선언
		
//			ModelAndView mv = new ModelAndVie w();
		
			// 1. 유효성 검사
			if(!isValid(date))
				return "yoilError";
			
			// 2.요일 계산
			char yoil = getYoil(date);
		
			// 3.계산한 결과를 모델에 저장	
			//K를 myDate로 V를 date로 저장
			model.addAttribute("myDate",date);
			model.addAttribute("yoil",yoil);
			
			
		return "yoil"; // /WEB-INF/views/yoil.jsp 
		//반환타입을 void로 하게되면 맵핑된 주소의 .jsp로 View가 호출된다
		
	}

	private boolean isValid(MyDate date) {
		return isValid(date.getYear(),date.getMonth(),date.getDay());
	}

	private char getYoil(MyDate date) {
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
