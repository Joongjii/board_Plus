package com.fastcampus.ch2;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
	//어떤 객체를 검증하려고 하면 그 객체의 클래스 clazz객체가 넘어오는데 그게 User 클래스와 같으면 그게 User 클래스란 뜻
	//	return User.class.equals(clazz);
		return User.class.isAssignableFrom(clazz); // clazz가 User 또는 그 자손인지 확인
	}

	@Override
	public void validate(Object target, Errors errors) {
	//	if(!target instanceof User) return; 생략 가능 -- 왜? supports메서드로 이미 형변환에 대한 확인을 마쳤기 때문에
		
		System.out.println("GlobalValidator.validate()가 호출되었습니다");
		
		User user = (User)target;
		
		String id = user.getId();
		
		//		if(id==null || "".equals(id.trim())) {
		//			errors.rejectValue("id", "required");
		//		}
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required");
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
				
				if(id==null || id.length() <  5 || id.length() > 12) {
					errors.rejectValue("id","invalidLength", new String[] {"", "5","12"} , null);
				}	
		}
}
