package com.fastcampus.ch3.diCopy2;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car{}
class SportsCar extends Car{}
class Truck extends Car{}
class Engine {}
class AppConcext {
        Map map;  //객체 저장소

        AppConcext(){
/*
            map = new HashMap(); //map을 hashMap으로 구현
            map.put("car", new SportsCar());
            map.put("engine", new Engine());
*/

            try {
                Properties p = new Properties();
                p.load(new FileReader("config.txt"));

                //Properties의 내용 p를 map에 저장
                map = new HashMap(p);

                //반복문으로 클래스 이름을 얻어서 객체를 생성해서 다시 map에 저장
                for (Object key : map.keySet()) {
                    Class clazz = Class.forName((String) map.get(key));
                    map.put(key, clazz.newInstance());
                }
            }catch (Exception e) {
                 e.printStackTrace();
            }
        }

        Object getBean(String key){ //Appcontext(저장소) 내부 getBean메서드 - map의 key를 반환
            return map.get(key);
        }

}

public class Main2 {
    public static void main(String[] args) throws Exception {
        AppConcext ac = new AppConcext();
        Car car = (Car) ac.getBean("car");
        Engine engine = (Engine) ac.getBean("engine");
        System.out.println("~ car = " + car);
        System.out.println("~ engine = " + engine);
    }
}

