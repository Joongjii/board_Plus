package com.fastcampus.ch2;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	@GetMapping("/login")
	public String loginForm() {
 		return "loginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//1. 세션을 종료
		session.invalidate();
		//2. 홈으로 이동
		return "redirect:/";
	}
	
	@PostMapping("/login")
	public String login( String id, String pwd, String toURL, boolean rememberId,
			//@CookieValue("JSESSIONID")String cookieId or @CookieValue("id")String cookieId,
			// 만약에 쿠기 정보가 필요하다면 @CV를 통해 얻을 수 있다.
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("id="+id);
		System.out.println("pwd="+pwd);
		System.out.println("rememberId="+rememberId);
//		1.id와 pwd를 확인
		if(!loginCheck(id,pwd)) {
			//2-1 id와 pwd가 일치하지 않으면, loginForm으로 이동
			String msg = URLEncoder.encode("아이디와 패스워드가 일치하지 않아요", "utf-8");
			return "redirect:/login/login?msg="+msg;
			//여기서 login/login은 redirect이므로 Get으로 간다
		}
		// 2-2 id와 pwd가 일치하면
			// 세션 객체를 얻어와야한다
			HttpSession session = request.getSession();
			// 세션 객체에 id를 저장
			session.setAttribute("id", id); //session 객체를 얻어올려면 HttpServletRequest 추가해야한다
			if(rememberId) {
			//	1. 쿠기를 생성
					Cookie cookie = new Cookie ("id", id); 
			//	2. 응답에 저장 -- HttepServletResponse 추가해야 한다
				response.addCookie(cookie);
			}else {
			//	1. 쿠기를 삭제
				Cookie cookie = new Cookie ("id", id); 
				cookie.setMaxAge(0);
			//	2. 응답에 저장
				response.addCookie(cookie);
			}
		// 3. 홈으로 이동
		 toURL = toURL==null || toURL.equals("") ? "/" : toURL;			
		return "redirect:"+toURL; // HomeController 확인
	}

	private boolean loginCheck(String id, String pwd) {
		
		return "asdf".equals(id) && "1234".equals(pwd);
	}
	
	
	
	
	
	
	
	
	
	
}
