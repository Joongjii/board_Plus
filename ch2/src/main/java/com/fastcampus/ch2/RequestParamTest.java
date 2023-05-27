package com.fastcampus.ch2;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestParamTest {
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		return "yoilError";
	}
	
	
	@RequestMapping("/requestParam")
	public String main(HttpServletRequest request) {
		String year = request.getParameter("year");
//		http://localhost/ch2/requestParam         ---->> year=null
//		http://localhost/ch2/requestParam?year=   ---->> year=""
//		http://localhost/ch2/requestParam?year    ---->> year=""
		System.out.printf("[%s]year=[%s]%n", new Date(), year);
		//Filter가 돌아가며 후처리를 syso로 찍어낸다
		//new Date() --> Thu Mar 02 20:08:02 KST
		return "yoil";
	}

	@RequestMapping("/requestParam2")
//	public String main2(@RequestParam(name="year", required=false) String year) {   // 아래와 동일 
		//RequestParam 요청의 파라미터를 연결할 매개변수에 붙이는 애너테이션
		//year는 파라미터 이름 - 생략가능 required=false -> ?year=2023과 같이 URL에 값을 주는 것
	public String main2(String year) {   
//		http://localhost/ch2/requestParam2         ---->> year=null
//		http://localhost/ch2/requestParam2?year    ---->> year="" -- 빈 문자열       (빈 문자열 != null)
		System.out.printf("[%s]year=[%s]%n", new Date(), year);	 //%s 문자형 %n 줄바꿈
		//첫 번째 [%s]--new Date()  두번째 [%s]--year
		return "yoil";
	}

	@RequestMapping("/requestParam3")
//		public String main3(@RequestParam(name="year", required=true) String year) {   // 아래와 동일 
		public String main3(@RequestParam String year) {   
//		http://localhost/ch2/requestParam3         ---->> year=null   400 Bad Request. required=true라서 
//		http://localhost/ch2/requestParam3?year    ---->> year=""
		System.out.printf("[%s]year=[%s]%n", new Date(), year);
		return "yoil";	
	}

	@RequestMapping("/requestParam4")
	public String main4(@RequestParam(required=false) String year) {   
//		http://localhost/ch2/requestParam4         ---->> year=null 
//		http://localhost/ch2/requestParam4?year    ---->> year=""   
		System.out.printf("[%s]year=[%s]%n", new Date(), year);
		return "yoil";
	}

	@RequestMapping("/requestParam5")
	public String main5(@RequestParam(required=false, defaultValue="1") String year) {   
//		http://localhost/ch2/requestParam5         ---->> year=1   
//		http://localhost/ch2/requestParam5?year    ---->> year=1   
		System.out.printf("[%s]year=[%s]%n", new Date(), year);
		return "yoil";
	}
	
// =======================================================================
	
	@RequestMapping("/requestParam6") 
	public String main6(int year) {   
//		http://localhost/ch2/requestParam6        ---->> 500 java.lang.IllegalStateException: Optional int parameter 'year' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.
//		http://localhost/ch2/requestParam6?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: "" 
		System.out.printf("[%s]year=[%s]%n", new Date(), year);
		return "yoil";
	}
	
	@RequestMapping("/requestParam7") 
	public String main7(@RequestParam int year) {   
//		http://localhost/ch2/requestParam7        ---->> 400 Bad Request, Required int parameter 'year' is not present
//		http://localhost/ch2/requestParam7?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: "" 
		System.out.printf("[%s]year=[%s]%n", new Date(), year);
		return "yoil";
	}

	@RequestMapping("/requestParam8") 
	// "" -> int 변환문제를 해결하기 위해 예외처리를 해준다 catcher 메서드
	public String main8(@RequestParam(required=false, defaultValue="2021") int year) {   
	//	http://localhost/ch2/requestParam8        ---->> 500 java.lang.IllegalStateException: Optional int parameter 'year' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.
								//null -> int 변환할 수 없기에 500 에러
	//	http://localhost/ch2/requestParam8?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: "" 
								// ""  -> int 변환할 수 없기에 400 에러 - 값을 주긴 줬는데 잘못줬기 때문에
		System.out.printf("[%s]year=[%s]%n", new Date(), year);
		return "yoil";
	}
	
	@RequestMapping("/requestParam9") 
	public String main9(@RequestParam(required=true) int year) {   
	//	http://localhost/ch2/requestParam9        ---->> 400 Bad Request, Required int parameter 'year' is not present
	//	http://localhost/ch2/requestParam9?year   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: "" 
								// "" -> int 변환이 안되므로 에러 400에러
		System.out.printf("[%s]year=[%s]%n", new Date(), year);
		return "yoil";
	}
	
	@RequestMapping("/requestParam10")   
	public String main10(@RequestParam(required=true, defaultValue="1") int year) {   
	//	http://localhost/ch2/requestParam10        ---->> year=1   
	//	http://localhost/ch2/requestParam10?year   ---->> year=1   
		System.out.printf("[%s]year=[%s]%n", new Date(), year);
		return "yoil";
	}

	@RequestMapping("/requestParam11")   
	public String main11(@RequestParam(required=false, defaultValue="1") int year) {   
							// 그래서 필수 입력이 아닐때는 기본 값이 있어야한다
//		http://localhost/ch2/requestParam11        ---->> year=1   
//		http://localhost/ch2/requestParam11?year   ---->> year=1   
		System.out.printf("[%s]year=[%s]%n", new Date(), year);
		return "yoil";
	}
} // class
