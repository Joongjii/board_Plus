package com.fastcampus.ch3.diCopy3;

import com.google.common.reflect.ClassPath;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Component class Car{}
@Component class Person{}
@Component class Lee extends Person{}
@Component class Kim extends Person{}
@Component class Truck extends Car{}
@Component class SportsCar extends Car{}
@Component class Engine {}


 class AppConcext {
    Map map;  //객체 저장소

    AppConcext() {
        map = new HashMap();
        doComponentCsan();

    }

    private void doComponentCsan() {
        try {
            // 1. 패키지내의 클래스 목록을 가져온다
            // 2. 반복문으로 클래스를 하나씩 잀어와서 @Component이 붙어 있는지 확인
            // 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
            ClassLoader classLoader =  AppConcext.class.getClassLoader(); //클래스 목록 가져오기
            ClassPath classPath = ClassPath.from(classLoader); // 클래스 경로 가져오기

            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy3");

            for(ClassPath.ClassInfo classInfo : set){
                Class clazz = classInfo.load();
                Component component = (Component) clazz.getAnnotation(Component.class);
                if (component != null){
                    String id = StringUtils.uncapitalize(classInfo.getSimpleName());
                    map.put(id, clazz.newInstance());
                }

            }
        } catch (Exception e) {
             e.printStackTrace();
        }

    }
    // AC 내부의 getBean메서드를 통해 key에 해당하는 value값을 반환
    Object getBean(String key) {return map.get(key);} //byName

    Object getBean(Class clazz) { //byType
        for (Object obj : map.values()) {
            if (clazz.isInstance(obj))
                return obj;

        }
        return null;
    }
}
    public class Main3 {
    public static void main(String[] args) throws Exception {
        AppConcext ac = new AppConcext();
        Car car = (Car) ac.getBean("car"); //byName "truck" "sprotsCar"
        Car car2 = (Car) ac.getBean(Car.class); //byType
        Person person = (Person) ac.getBean(Person.class); //byType
        Engine engine = (Engine) ac.getBean("engine");

        //System.out.println("~ car = " + car);
        System.out.println("~ car = " + car2);
        System.out.println("~ person = " + person);
        System.out.println("~ engine = " + engine);
    }
}

