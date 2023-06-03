package com.fastcampus.ch4.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class PageHandler {
//    private int page; //현재 페이지
//    private int pageSize; // 한 페이지의 크기
//    private String option;
//    private String keyword;


    private SearchCondition sc;

    private int totalCnt;
    private int naviSize=10;
    private int totalPage;
    private  int beginPage;
    private  int endPage;
    private boolean showPrev;
    private boolean showNext;


    public PageHandler(int totalCnt, SearchCondition sc){
        this.totalCnt =totalCnt;
        this.sc = sc;

        doPaging(totalCnt, sc);
    }

    public void doPaging(int totalCnt, SearchCondition sc){
        this.totalCnt = totalCnt;

        totalPage = (int) Math.ceil(totalCnt / (double)sc.getPageSize());
        beginPage = (sc.getPage()-1) / naviSize * naviSize + 1;
        endPage = Math.min(beginPage + naviSize - 1 , totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;

    }


    void print(){
        System.out.println("page = " + sc.getPage());
        System.out.print(showPrev ? "[PREV]" : "");
        for(int i = beginPage; i <=endPage; i++){
            System.out.print(i+" ");
        }
        System.out.println(showNext ? "[NEXT]" : "");
    }

    public String getQueryString(Integer page){
        // ?page=1&pageSize=10&option=T&keyword="title"
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", sc.getPageSize())
                .queryParam("option", sc.getOption())
                .queryParam("keyword", sc.getKeyword())
                .build().toString();
    }
    public String getQueryString() {
        // ?page=1&pageSize=10&option=T&keyword="title"
        return getQueryString(sc.getPage());

    }
    public SearchCondition getSc() {
        return sc;
    }

    public void setSc(SearchCondition sc) {
        this.sc = sc;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }


    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }


    @Override
    public String toString() {
        return "PageHandler{" +
                "sc=" + sc +
                ", totalCnt=" + totalCnt +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                '}';
    }
}
