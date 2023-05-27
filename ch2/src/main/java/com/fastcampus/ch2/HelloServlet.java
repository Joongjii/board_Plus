package com.fastcampus.ch2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet{
	// Alt shift O를 통해 init service destory 생성한다

	
	@Override
		public void init() throws ServletException {
		// 서블릿이 초기화될때 자동으로 호출되는 메서드
		// 1. 서블릿의 초기화 작업 담당
		System.out.println("[HelloServlet] init() is called -- 서블릿 호출~");
		}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 입력
		// 2. 처리
		// 3. 출력
		System.out.println("[HelloServlet] service() is called -- 서비스 호출~");
		
	}

	@Override
	public void destroy() {
		// 내용이 변경되어 서버가 재시작될 때
		// 뒷정리 - 서블릿이 메모리에서 제거될 때 서블릿 컨테이너에 의해서 자동 호출
		System.out.println("[HelloServlet] destory() is called -- 종료 호출~");
	}

	

}
