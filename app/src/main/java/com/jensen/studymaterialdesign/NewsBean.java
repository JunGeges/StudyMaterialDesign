package com.jensen.studymaterialdesign;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/8.
 */

public class NewsBean {

    private String newstitle;

    private String newsTime;

    private ArrayList<Integer> newsIconList;

    public NewsBean(String newstitle, String newsTime, ArrayList<Integer> newsIconList) {
        this.newstitle = newstitle;
        this.newsTime = newsTime;
        this.newsIconList = newsIconList;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public ArrayList<Integer> getNewsIconList() {
        return newsIconList;
    }

    public void setNewsIconList(ArrayList<Integer> newsIconList) {
        this.newsIconList = newsIconList;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "newstitle='" + newstitle + '\'' +
                ", newsTime='" + newsTime + '\'' +
                ", newsIconList=" + newsIconList +
                '}';
    }
}
