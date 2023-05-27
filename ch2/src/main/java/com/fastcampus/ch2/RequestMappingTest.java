package com.fastcampus.ch2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RequestMappingTest {
//  @RequestMapping({"/login/hello.do", "/login/hi.do"}) 
    @RequestMapping("/login/hello.do") // http://localhost/ch2/login/hello.do
    public void test1(){
        System.out.println("urlpattern=/login/hello.do - Mapping 성공");
    }

    @RequestMapping("/login/*")   // /login/hello, /login/hi
    public void test2(){
        System.out.println("urlpattern=/login/*");
    }

    @RequestMapping("/login/**/tmp/*.do") 
    // /login/tmp/hello.do, /login/aaa/tmp/hello.do
    // **은 사이에 들어간 것이 없어도 되고 뒤에 여러개가 붙어도 된다
    public void test3(){
        System.out.println("urlpattern=/login/**/tmp/*.do");
    }

    @RequestMapping("/login/??")
    public void test4(){   // /login/hi, /login/my.car
        System.out.println("urlpattern=/login/??");
    }

    @RequestMapping("*.do") // /hello.do, /hi.do, /login/hi.do
    public void test5(){ 
        System.out.println("urlpattern=*.do");
    }
    
    @RequestMapping("/*.???") //  /hello.aaa, /abc.txt
    public void test6(){ 
        System.out.println("urlpattern=*.???");
    }
}