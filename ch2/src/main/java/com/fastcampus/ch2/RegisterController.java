package com.fastcampus.ch2;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
	
	@InitBinder
	public void toDate(WebDataBinder binder) {
		ConversionService cs = binder.getConversionService();
//		System.out.println("conversionservice의 출력값"+cs);
//		User.java에 @DateTimeFormat 사용하면 아래 두 줄은 필요가 없다		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));	//	Date로 변환할때 스프링 제공 CDE클래스를 이용 SDF으로 형식을 지정 false는 빈 값을 허용하냐 안하냐
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("#"));
//		binder.setValidator(new UserValidator()); //UV를 WDB의 로컬(컨트롤러 내에서만) validator로 등록 
		
		// globalValidator를 쓸 때는 로컬 Validator를 추가하려면 아래 처럼 addValidators로 해줘야한다 
		//binder.addValidators(new UserValidator()); //UV를 WDB의 로컬(컨트롤러 내에서만) validator로 등록 with addValidator
		List<Validator> validatorList = binder.getValidators();
		System.out.println("validatorList의 출력값 ="+validatorList);

	}
	
	
	
	@RequestMapping(value="/register/add", method={RequestMethod.GET, RequestMethod.POST})
	//배열을 이용해 둘 다 가능하게 할 수 있다
	//RequestMapping을 간단하게 쓸 수 있게 해주는 것이 @GetMapping, @PostMapping

	
	//servlet-context.xml에 아래 줄을 ViewController로 등록하면 신규회원가입 화면에 대한 내용은 주석이 가능하다
	//<view-controller path="register/add" view-name="registerForm"></view-controller>
	
	
//	@RequestMapping("/register/add") //신규회원 가입 화면은 GET으로 한다 
//	@GetMapping("/register/add")
	public String register() {
		return "/registerForm"; //URL맵핑의 반환을 WEB-INF/views/registerForm.jsp로 한다
//							   //add로 놓고 registerForm으로 반환하면 본인 폼페이지로 이동한다
	}
	
	//@RequestMapping(value="/register/save", method=RequestMethod.POST) -- POST방식으로만 회원가입 가능 방식
	@PostMapping("/register/save") //PostMapping은 스프링 4.3부터 가능해 업데이트 해야한다
	public String save(@Valid User user, BindingResult result, Model m) throws Exception {
		System.out.println("result의 출력값 = "+ result);
		System.out.println("user의 출력값 = "+ user);
		// id, pwd, name등등 User로 묶어 하나로
		
		
//		// 수동 검증 Validator를 직접 생성하고 validator()를 직접 호출
//		UserValidator userValidator = new UserValidator();
//		userValidator.validate(user, result); 
//		//BindingResult는 Errors 인터페이스의 자손이기때문에 errors가 아닌 result를 써도 된다
		
		//User 객체를 검증한 결과 에러가 있으면 , registerForm을 이용해서 에러를 보여줘야 한다
		if(result.hasErrors()) {
			return "registerForm";
		}
		
		//1. 유효성 검사
		
//		if(!isValid(user)) {
//			
//			String msg =URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");
//			// URL 전송할때랑 화면 출력할때 인코딩깨지므로 인코더를 추가해준다
//			//registerForm에는 디코더를 추가한다
//			
//			m.addAttribute("msg",msg);
//			return "forward:/register/add";
//			
//			//redirect와 forward 둘 다 해보면서 F12 결과 확인
//			
//			
//			//redirect는 재요청 model을 재요청에 쓸 수 가 없다
//			//아래 한 줄과 동일하다 넘겨줘야될 값이 msg처럼 하나라면 아래 코드가 쉽지만
//			//여러개라면 model에 담아서 넘겨주는것이 좋다
////			return "redirect:/register/add?msg=" + msg; //URL재작성(rewriting)
//			}
		
		
		
		
		//2. DB에 신규회원 정보를 저장
		return"/registerInfo";
	}

	private boolean isValid(User user) {
	
		return true;
	}
}
