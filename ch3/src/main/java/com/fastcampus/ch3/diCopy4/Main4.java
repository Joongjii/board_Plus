package com.fastcampus.ch3.diCopy4;

import com.google.common.reflect.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component class Car{
//   @Autowired Engine engine;
//   @Autowired Door door;
    @Resource Engine engine;
    @Resource Door door;

    @Override
    public String toString() {
        return "Car{" +
                "engine=" + engine +
                ", door=" + door +
                '}';
    }
}
@Component class Door{}
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
        doAutowired();
        doResource();
    }

     private void doResource() {
         // map에 저장된 객체의 iv중에 @Resource 저장되어 있으면
         // map에 iv의 이름에 맞는 객체를 찾아서 연결(객체의 주소를 iv저장)
         try {
             for(Object bean : map.values()){
                 for(Field fld : bean.getClass().getDeclaredFields()){
                     if(fld.getAnnotation(Resource.class)!=null) //byName
                         fld.set(bean, getBean(fld.getName()));
                 }
             }
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         }
     }

     private void doAutowired() {
         // map에 저장된 객체의 iv중에 @Autowired가 저장되어 있으면
         // map에 iv의 타입에 맞는 객체를 찾아서 연결(객체의 주소를 iv저장)
         try {
             for(Object bean : map.values()){
                 for(Field fld : bean.getClass().getDeclaredFields()){
                     if(fld.getAnnotation(Autowired.class)!=null) //byType
                         fld.set(bean, getBean(fld.getType()));   //cer.engine =object
                 }
             }
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         }
     }

     private void doComponentCsan() {
        try {
            // 1. 패키지내의 클래스 목록을 가져온다
            // 2. 반복문으로 클래스를 하나씩 잀어와서 @Component이 붙어 있는지 확인
            // 3. @Component가 붙어있으면 객체를 생성해서 map에 저장
            ClassLoader classLoader =  AppConcext.class.getClassLoader(); //클래스 목록 가져오기
            ClassPath classPath = ClassPath.from(classLoader); // 클래스 경로 가져오기

            Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.ch3.diCopy4");

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
    public class Main4 {
    public static void main(String[] args) throws Exception {
        AppConcext ac = new AppConcext();
        Car car = (Car) ac.getBean("car"); //byName "truck" "sprotsCar"
        Car car2 = (Car) ac.getBean(Car.class); //byType
        Person person = (Person) ac.getBean(Person.class); //byType
        Engine engine = (Engine) ac.getBean("engine");
        Door door = (Door) ac.getBean(Door.class);


//        car.engine= engine;  //--Autowired가
//        car.door = door;



        System.out.println("~ car = " + car);
        System.out.println("~ person = " + person);
        System.out.println("~ engine = " + engine);
        System.out.println("~ door = " + door);
    }
}

