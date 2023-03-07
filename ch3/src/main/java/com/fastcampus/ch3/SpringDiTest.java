//package com.fastcampus.ch3;
//
////BeanFactory interface : 객체 생성과 검색에 대한 기능 정의, getBean() 메서드가 정의되어 있음
////ApplicationContext interface : 빈 객체의 생성, 초기화, 보관, 제거 등을 관리하며 스프링 컨테이너라고 표현함
////GenericXmlApplicationContext class : XML 파일로부터 정보를 읽어와 객체를 생성하고 초기화
//// GenericXmlApplicationContext 클래스는 작성된 스프링 설정 파일을 읽어와 로딩이 시키고, 객체를 생성하며, 초기화하는 역할을 합니다.
//// 또한 getBean 메소드를 이용하여 스프링 컨테이너에 생성된 객체에 접근 가능할 수 있게 됩니다.
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.GenericXmlApplicationContext;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.Arrays;
////자바에서 @Component는 xml파일의 <bean id="engine" class= "com.fastcampu.ch3.Engine/>와 같은 역할을 한다
////@Component("engine")
//class Engine{}
//@Component class SuperEngine extends Engine{}
//@Component class TurboEngine extends Engine{}
//@Component class Door{}
//@Component
//class Car{
//
//    //xml파일에 @scan을 붙이고 @Value와 @Autowired로 값을 읽어낼 수 있다
//    @Value("red") String color;
//    @Value("100") int oil;
//    @Autowired @Qualifier("superEngine") Engine engine; //byType - 타입으로 먼저 검색을 하고 타입이 여러개인 경우에 이름으로 찾는다
//                                              //engine, turboEngine, superEninge
//                              // 따라서 getBean처럼 에러가 나지 않는다
//    // @Resource(name = "superEngine") Engine engine; //byname 위에 표현과 결과는 같지만 과정이 다르다 일반적으로는 byTypedls @Autowired를 많이 쓰긴한다
//                                                      //@Autowired를 더 많이 쓰는 이유는 이름은 타입에 비해 바뀔가능성이 많고 문자열이라 오타도 많이 나기 때문이다
//
//    @Autowired Door[] doors;
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//    public void setOil(int oil) {
//        this.oil = oil;
//    }
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//    }
//    public void setDoors(Door[] doors) {
//        this.doors = doors;
//    }
//        public Car(){ }// 기본생성자를 꼭 만들어주자
//// config.xml에서 property -> constructor-arg 바꿔 생성자를 이용해서 초기화 가능
//        public Car(String color, int oil, Engine engine, Door[] doors) {
//            this.color = color;
//            this.oil = oil;
//            this.engine = engine;
//            this.doors = doors;
//    }
//
//    @Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", door=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//
//
//
//public class SpringDiTest {
//    public static void main(String[] args) {
//    ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
////  Car car = (Car) ac.getBean("car"); //byname 아래와 같은 문장
//    Car car = ac.getBean("car", Car.class);//byname 이름 뒤에 타입정보를 주면 형변환을 생략할 수 있다
//
////   // Car car2 = (Car) ac.getBean(Car.class);
////    Engine engine= (Engine) ac.getBean("engine");
////        // Engine engine= (Engine) ac.getBean(Engine.class); --에러
////        // 같은 타입이 여러 개가 있으면 byType으로 찾을때 에러가 날 수 있다
////    Door door = (Door) ac.getBean("door");
//
//
//    // config.xml에서 car부분에 prototype으로 해줘야 car와 car2의 주소값이 다르게 나온다
//        // 아래 4줄을 주석처리하고 config.xml에서 <property>를 사용해 처리가 가능하다
////        car.setColor("red");
////        car.setOil(100);
////        car.setEngine(engine);
////        car.setDoors(new Door[]{ac.getBean("door",Door.class), (Door) ac.getBean("door")});   // 형변환하거나 타입을 적어주면 된다
//
//        System.out.println("~ car = " + car);
//////        System.out.println("~ car = " + car2);
////        System.out.println("~ engine = " + engine);
//////        System.out.println("~ door = " + door);
//    }
//
//
//
//
////싱글톤 : 하나의 클래스에서 하나의 객체만 생성하는 것이 singleton
//
//    //  getBean()
//// context.getBean(Class<T>)
//// 매개변수로 던지는 Class 타입의 인스턴스를 반환하는 것
//// SpringLearn learn = context.getBean(SpringLearn.class);
////
////
//// context.getBean(Class<T> , id값 (BeanName) )
//// 매개변수로 Class + BeanNaem 을 던지므로 보다 명확하게 객체를 얻어올수가 있다.
//// (동일한 Class 타입이 있을수 있기에)
//// SpringLearn learn = context.getBean("learn",SpringLearn.class);
////
////
//// context.getBean( id값 (BeanName) )
//// id값만 던지게되면 오브젝트 타입으로 리턴하므로 형변환을 해줘야한다.
//// (bean생성할때 id값이 같으면 컴파일 오류를 내므로 2번과 같이 명확하게 얻어올수가 있다.)
//// SpringLearn learn = (SpringLearn)context.getBean("learn");
//
//
//}
