package com.fastcampus.ch3.diCopy1;

import java.io.FileReader;
import java.util.Properties;

class Car{}
class SportsCar extends Car{}
class Truck extends Car{}
class Engine {}

public class Main1 {
    public static void main(String[] args) throws Exception{

       Car car = (Car) getObject("car");
       Engine engine = (Engine) getObject("engine");
       System.out.println("car = " + car);
       System.out.println("engine = " + engine);
    }

    static Object getObject(String key) throws Exception{
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

        Class clazz = Class.forName(p.getProperty(key));
        //key는 config 파일에서 car, engine이다

        return clazz.newInstance();
    }



    static Car getCar() throws Exception{
        //config .txt를 읽어서 Properties에 저장
        Properties p = new Properties();
        p.load(new FileReader("config.txt"));

    // 클래스 객체(설계도) 얻어오기
        Class clazz = Class.forName(p.getProperty("car"));
    //Object타입인 clazz를 Car타입으로 형변환해야 한다
        return (Car)clazz.newInstance();
    }









}
