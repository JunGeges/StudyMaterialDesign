package com.jensen.studymaterialdesign;

/**
 * Created by Administrator on 2017/9/9.
 */

public class GvItemBean {

    private int icon;

    private String title;

    public GvItemBean(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GvItemBean{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                '}';
    }
}
