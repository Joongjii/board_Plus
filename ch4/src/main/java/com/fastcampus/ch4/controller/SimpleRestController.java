package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimpleRestController {
//    @GetMapping("/ajax")
//    public String ajax() {
//        return "ajax";
//    }

    @PostMapping(value = "/send")
   // @ResponseBody -- @RestController를 사용하면 생략된다
    public Person test(@RequestBody Person p) {
        System.out.println("p = " + p);
        p.setName("ABC");
        p.setAge(p.getAge() + 10);

        return p; //뷰 이름이 아니라 객체를 반환한다
                  //ResponseBody에 의해 적용
    }

    @PostMapping("/send")
  //  @ResponseBody
    public Person test2(@RequestBody Person p) {
        System.out.println("p = " + p);
        p.setName("ABC");
        p.setAge(p.getAge() + 10);

        return p; //뷰 이름이 아니라 객체를 반환한다
        //ResponseBody에 의해 적용
    }

    // @RestController는 @ResponseBody + @Controller이다

}