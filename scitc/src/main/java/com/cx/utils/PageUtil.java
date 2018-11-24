package com.cx.utils;


import java.util.ArrayList;
import java.util.List;

/**
 * @author:Teacher黄
 * @date:Created at 2018/10/22
 */
public class PageUtil<T> {

    public PageUtil(Integer nowPage, Integer pageSize) {
        this.nowPage = nowPage;
        this.pageSize = pageSize;
    }

    /**
     * 当前页
     */
    private Integer nowPage = 1;

    /**
     * 页容量
     */
    private Integer pageSize = 3;


    /**
     * 分页的内容
     */
    private List<T> contentList = null;

    /**
     * 总的条数
     */
    private Integer totalCount;

    /**
     * 总共有多少页
     */
    private Integer pages;

    /**
     * 分页的页码数
     */
    private List<Integer> pageList = new ArrayList<>();

    /**
     * 上一页
     */
    private  Integer prev;

    /**
     * 下一页
     */
    private  Integer next;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    /**
     * 条件参数
     */
    private String queryString;



    public Integer getNowPage() {
        return nowPage;
    }

    public void setNowPage(Integer nowPage) {
        this.nowPage = nowPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getContentList() {
        return contentList;
    }

    public void setContentList(List<T> contentList) {
        this.contentList = contentList;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;

        // 设置总的条数的时候需要给其他的属性赋值
        this.setAll();
    }

    public List<Integer> getPageList() {
        return pageList;
    }

    public void setPageList(List<Integer> pageList) {
        this.pageList = pageList;
    }

    public Integer getPrev() {
        return prev;
    }

    public void setPrev(Integer prev) {
        this.prev = prev;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    /**
     * 统一设置属性
     */
    private void setAll(){
        // 设置总的页数
        this.pages = Double.valueOf(Math.ceil(this.totalCount/(this.pageSize+0.0))).intValue();
        // 下一页
        this.next = nowPage >= pages ? nowPage : nowPage+1;
        // 上一页
        this.prev = nowPage <= 1 ? 1 : nowPage-1;

        // 页码数
        for (int i = 1; i <= pages ; i++) {
            this.pageList.add(i);
        }

    }



    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }




    @Override
    public String toString() {
        return "PageUtil{" +
                "nowPage=" + nowPage +
                ", pageSize=" + pageSize +
                ", contentList=" + contentList +
                ", totalCount=" + totalCount +
                ", pages=" + pages +
                ", pageList=" + pageList +
                ", prev=" + prev +
                ", next=" + next +
                '}';
    }
}
