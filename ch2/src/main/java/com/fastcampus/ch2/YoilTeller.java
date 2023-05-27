package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class YoilTeller { 
	@RequestMapping("/getYoil")
	public void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//1. 입력 값
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		int y = Integer.parseInt(year);
		int m = Integer.parseInt(month);
		int d = Integer.parseInt(day);
		
		// 2. 작업
		Calendar cal = Calendar.getInstance();
		cal.set(y, m - 1, d); //월은 -1 해줘야 한다
		System.out.println(cal);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); //calendar가 가르키는(의미하는) 특정 날짜가 무슨 요일인지 알기 위해 쓰인다.
		char yoil = " 일월화수목금토".charAt(dayOfWeek);
		
		
		//3. 출력
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8"); 
		PrintWriter printOut = response.getWriter(); //response 객체에서 브라우저로의 출력 스트림을 얻는다.
		
			printOut.println("<html>");
			printOut.println("<head>");
			printOut.println("</head>");
			printOut.println("<body>");
			printOut.println(year + "년 " + month + "월 " + day + "날은 ");
			printOut.println(yoil + "요일이다~ ");
			printOut.println("</body>");
			printOut.println("</html>");
		
	}

}
