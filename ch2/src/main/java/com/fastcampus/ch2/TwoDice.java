package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TwoDice {
  @RequestMapping("/rollDice")
	public void main(HttpServletResponse response) throws IOException {
	  int idx1 = (int) (Math.random()*6+1);
	  int idx2 = (int) (Math.random()*6+1);
	  
	  response.setContentType("text/html");
	  response.setCharacterEncoding("utf-8");
	  
	  //PrintWriter 만들어서 html 형식으로 출력하기
	  PrintWriter out = response.getWriter();
	  // 정적리소스(.css .html .js)인 이미지 파일을 이용하여
	  // 실행할때 마다 결과가 변한다 : 동적리소스 (서버가 제공하는 리소스 : 스트리밍, 프로그램)
	  out.println("<html>");
	  out.println("<head>");
	  out.println("</head>");
	  out.println("<body>");
	  out.println("<img src='resources/img/dice"+ idx1 +".jpg'>");
	  out.println("<img src='resources/img/dice"+ idx2 +".jpg'>"); 	  
//	  out.println("<img src='/img/dice1.jpg'>"); -- 경로를 정확하게 입력해야한다
	  out.println("</body>");
	  out.println("</html>");
	  
	  

  }
}
