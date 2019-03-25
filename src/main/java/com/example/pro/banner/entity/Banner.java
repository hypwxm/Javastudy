package com.example.pro.banner.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Banner implements Serializable {
    private static final long serialVersionUID = 2278835641341997452L;
    private int id;
    private String img;
    private String title;
    private String link;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModify;
    private byte status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date time) {
        this.createTime = time;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date time) {
        this.lastModify = time;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    /*
     * @Override public String toString() { String str = new StringBuffer();
     * str.format("{}", args) }
     */
}