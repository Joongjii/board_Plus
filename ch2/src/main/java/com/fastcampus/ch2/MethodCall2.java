package com.fastcampus.ch2;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

public class MethodCall2 {
	public static void main(String[] args) throws Exception{
		
		//1. YoilTellerMVC 클래스의 객체를 생성
		Class clazz = Class.forName("com.fastcampus.ch2.YoilTellerMVC");
		Object obj = clazz.newInstance();
		
		//2. main메서드의 정보를 가져온다
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class); 
												// 이름이 main + 매개변수 int 3개와 Model
		
		//3. 모델을 생성
		Model model = new BindingAwareModelMap();
		System.out.println("[before] model="+model);
		
		//4. main메서드를 호출
		// String viewName = obj.main(2023, 2, 27, model); -- 아래 줄과 동일
		String viewName = (String)main.invoke(obj, new Object[] { 2023, 2, 27, model });
		//ReflectionAPI를 이용했기 때문에 invoke를 사용한다
		//obj라는 객체의 main메서드를 매개변수인 Object[]을 이용하여 호출한다
		//왜 RefAPI를 사용하나? -- 매개변수가 다 다르기때문에 유연함을 위해서
		System.out.println("viewName="+viewName);	
		
		// Model의 내용을 출력 
		System.out.println("[after] model="+model);
				
		// 텍스트 파일을 이용한 rendering
		render(model, viewName);			
	} // main
	
	static void render(Model model, String viewName) throws IOException {
		String result = "";
		
		// 1. 뷰의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/"+viewName+".jsp"), "utf-8");
		
		while(sc.hasNextLine())
			result += sc.nextLine()+ System.lineSeparator();
		
		// 2. model을 map으로 변환 
		Map map = model.asMap();
		
		// 3.key를 하나씩 읽어서 template의 ${key}를 value바꾼다.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			// 4. replace()로 key를 value 치환한다.
			result = result.replace("${"+key+"}", ""+map.get(key));
		}
		
		// 5.렌더링 결과를 출력한다.
		System.out.println(result);
	}
}