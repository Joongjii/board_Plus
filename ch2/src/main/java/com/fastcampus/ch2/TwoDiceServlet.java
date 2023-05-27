package com.fastcampus.ch2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Spring에서 쓰는 @Controller + @RequestMapping ---> @WebServlet
//@WebServlet은 HttpServlet을 단일 상속
//메서드 이름은 service로 고정이며 파라미터로 request와 response가 포함되어야한다
//Servlet은 기본적으로 싱글톤 -- 1개의 인스턴스만 생성
//Controller와 유사하지만 Controller가 상속도 없고 더 발전된 모습이다
//WebServlet은 클래스당 URL맵핑이 된다
//Controller는 메서드와 URL맵핑된다 --- 따라서 @WebServlet은 URL당 클래스를 생성해야하지만
//Controller는 맵핑할 메서드만 추가적으로 만들어주면 된다


@WebServlet("/rollDice2")
public class TwoDiceServlet extends HttpServlet {
    int getRandomInt(int range) {
	return new Random().nextInt(range)+1;
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int idx1 = getRandomInt(6);
        int idx2 = getRandomInt(6);

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        out.println("<img src='resources/img/dice"+idx1+".jpg'>");
        out.println("<img src='resources/img/dice"+idx2+".jpg'>");
        out.println("</body>");
        out.println("</html>");
        out.close();	    
    }
}