package com.fastcampus.ch4.domain;

import java.util.Date;
import java.util.Objects;

public class BoardDto {

    private Integer bno;
    private String title;
    private String content;
    private String wirter;
    private int view_cnt;
    private int comment_cnt;
    private Date reg_date;

    public BoardDto(){

    }
    public BoardDto(String title, String content, String wirter) {
        this.title = title;
        this.content = content;
        this.wirter = wirter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDto boardDto = (BoardDto) o;
        return Objects.equals(bno, boardDto.bno) && Objects.equals(title, boardDto.title) && Objects.equals(content, boardDto.content) && Objects.equals(wirter, boardDto.wirter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bno, title, content, wirter);
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", wirter='" + wirter + '\'' +
                ", view_cnt=" + view_cnt +
                ", comment_cnt=" + comment_cnt +
                ", reg_date=" + reg_date +
                '}';
    }

    public Integer getBno() {
        return bno;
    }

    public void setBno(Integer bno) {
        this.bno = bno;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWirter() {
        return wirter;
    }

    public void setWirter(String wirter) {
        this.wirter = wirter;
    }

    public int getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(int view_cnt) {
        this.view_cnt = view_cnt;
    }

    public int getComment_cnt() {
        return comment_cnt;
    }

    public void setComment_cnt(int comment_cnt) {
        this.comment_cnt = comment_cnt;
    }

    public Date getReg_date() {
        return reg_date;
    }

    public void setReg_date(Date reg_date) {
        this.reg_date = reg_date;
    }
}
