package com.fastcampus.ch4.controller;


import com.fastcampus.ch4.domain.CommentDto;
import com.fastcampus.ch4.service.CommentService;
import com.fasterxml.jackson.databind.DatabindContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

//@ResponseBody
//@Controller
//둘을 합쳐서 @RestController로 쓸 수 있다

@RestController
public class CommentController {

    @Autowired
    CommentService service;

    //댓글을 수정하는 메서드
//    {
//        "pcno":0,
//            "comment" : "hihihi",
//            "commenter" : "asdf"
//    }

    //@ResponseBody -- 클래스 앞에다 @ResponseBody를 붙이면 각 메서드 앞에 붙인 @ResponseBody를 생략 가능
    @PatchMapping("/comments/{cno}") // /ch4/comments?bno=1085 POST
    public ResponseEntity<String> modify(@PathVariable Integer cno, @RequestBody CommentDto dto, HttpSession session){
        // String commenter = (String)session.getAttribute("id");
        String commenter ="asdf";
        dto.setCommenter(commenter);
        dto.setCno(cno);
        System.out.println("dto = " + dto);

        try {
            if (service.modify(dto)!=1)
                throw new Exception("Modify failed.");
            return new ResponseEntity<>("MOD_OK",HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("MOD_ERR",HttpStatus.BAD_REQUEST);
        }
    }


    //댓글을 등록하는 메서드
   // @ResponseBody
    @PostMapping("/comments") // /ch4/comments/70 POST
    public ResponseEntity<String> write(@RequestBody CommentDto dto, Integer bno, HttpSession session){
        // String commenter = (String)session.getAttribute("id");
        String commenter = "asdf";
        dto.setCommenter(commenter);
        dto.setBno(bno);
        System.out.println("dto = " + dto);

       try {
         if (service.write(dto)!=1)
             throw new Exception("Write failed.");

         return new ResponseEntity<>("WRT_OK",HttpStatus.OK);
       }catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<>("WRT_ERR",HttpStatus.BAD_REQUEST);
       }
    }

    //지정된 댓글을 삭제하는 메서드
    @DeleteMapping("/comments/{cno}") //commnets/1 <-- 삭제할 댓글 번호
   // @ResponseBody
    public ResponseEntity<String> remove(@PathVariable Integer cno, Integer bno, HttpSession session){
        // String commenter = (String)session.getAttribute("id");
         String commenter = "asdf";
         try {
                int rowCnt= service.remove(cno, bno, commenter);
                if(rowCnt!=1)
                    throw new Exception("Delete Failed");

                return new ResponseEntity<>("DEL_OK", HttpStatus.OK);

            } catch (Exception e) {
                e.printStackTrace();

                return new ResponseEntity<>("DEL_ERR", HttpStatus.BAD_REQUEST);
            }
    }



    //지정된 게시물의 모든 댓글을 가져오는 메서드
    @GetMapping("/comments") //comments?bno=1080 GET
  //  @ResponseBody
    public ResponseEntity<List<CommentDto>> list(Integer bno) {
        List<CommentDto> list = null;
        try {
            list = service.getList(bno);
            System.out.println("list = " + list);
            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK); //200
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST); //400
        }
    }
}
