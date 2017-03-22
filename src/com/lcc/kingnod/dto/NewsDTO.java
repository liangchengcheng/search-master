package com.lcc.kingnod.dto;

/**
 * Created by asus on 2017/3/21.
 */
public class NewsDTO {
    private String title;
    private String content;
    //数量
    private String count;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
