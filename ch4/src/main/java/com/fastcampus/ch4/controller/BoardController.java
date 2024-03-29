package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.*;
import com.fastcampus.ch4.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import javax.servlet.http.*;
import java.time.*;
import java.util.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;
    @PostMapping("/modify")
    public String modify(BoardDto boardDto, Model m, HttpSession session,RedirectAttributes rattr){
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.modify(boardDto);

            if(rowCnt!=1)
                throw new Exception("Modify failed");

            rattr.addFlashAttribute("msg","MOD_OK"); //addFlash는 세션을 이용한 일회성 저장

            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);
            m.addAttribute("msg", "MOD_ERR");
            return "board";
        }
    }
    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, HttpSession session,RedirectAttributes rattr){
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
           int rowCnt = boardService.write(boardDto);

           if(rowCnt!=1)
               throw new Exception("Write failed");

            rattr.addFlashAttribute("msg","WRT_OK"); //addFlash는 세션을 이용한 일회성 저장

            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
           // m.addAttribute("boardDto",boardDto);
            m.addAttribute(boardDto);
            m.addAttribute("msg", "WRT_ERR");
            return "board";
        }
    }

    @GetMapping("/write")
    public String write(Model m){
        m.addAttribute("mode", "new");
        return "board"; //읽기와 쓰기에 사용, 쓰기에 사용할때는 mode=new

    }

    //redirect는 항상 GET이다

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr){
        String writer = (String)session.getAttribute("id");
        try {
            m.addAttribute("page", page);
            m.addAttribute("pageSize",pageSize);

            int rowCnt = boardService.remove(bno,writer);

            if(rowCnt!=1)
                throw new Exception("board remove error");
            rattr.addFlashAttribute("msg", "DEL_OK");


        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ER");
        }

        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(Integer bno, Model m, Integer page, Integer pageSize){
        try{
             BoardDto boardDto = boardService.read(bno);
             //m.addAttribute(boardDto); 아래 문장과 동일
             m.addAttribute("boardDto",boardDto);
             m.addAttribute("page",page);
             m.addAttribute("pageSize",pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }

    @GetMapping("/list")
    public String list(@ModelAttribute SearchCondition sc,
                       Model m, HttpServletRequest request) {
        if(!loginCheck(request))
            return "redirect:/login/login?toURL="+request.getRequestURL();// 로그인을 안했으면 로그인 화면으로 이동


        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);

            List<BoardDto> list = boardService.getSearchResultPage(sc);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id")!=null;
    }
}