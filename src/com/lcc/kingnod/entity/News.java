package com.lcc.kingnod.entity;

import java.util.Date;
import io.searchbox.annotations.JestId;

/**
 * Created by asus on 2017/3/21.
 */
public class News {

    @JestId
    private Long id;
    private String title;
    private String content;
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
