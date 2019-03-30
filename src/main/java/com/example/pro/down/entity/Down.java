package com.example.pro.down.entity;

import java.io.Serializable;
import java.util.Date;

public class Down implements Serializable {
    private static final long serialVersionUID = 5128946888898205009L;
    private String shortTitle;
    private String title;
    private String link;
    private String surface;
    private String content;
    private String imgs;
    private Date createTime;
    private Date modifyTime;
    // 标签
    private String label;
    // 分类
    private Integer category;
    // 点赞
    private Integer likeTimes;
    private Integer stick;
    private String stickText;
    private Integer status;
    private String statusText;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the statusText
     */
    public String getStatusText() {
        return Status.getTypeName(this.status);
    }

    /**
     * @param statusText the statusText to set
     */
    public void setStatusText() {
        this.statusText = Status.getTypeName(this.status);;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the stickText
     */
    public String getStickText() {
        return Stick.getNameByType(this.stick);
    }

    /**
     * @param stickText the stickText to set
     */
    public void setStickText() {
        this.stickText = Stick.getNameByType(this.stick);;
    }

    /**
     * @return the stick
     */
    public Integer getStick() {
        return stick;
    }

    /**
     * @param stick the stick to set
     */
    public void setStick(Integer stick) {
        this.stick = stick;
    }

    /**
     * @return the likeTimes
     */
    public Integer getLikeTimes() {
        return likeTimes;
    }

    /**
     * @param likeTimes the likeTimes to set
     */
    public void setLikeTimes(Integer likeTimes) {
        this.likeTimes = likeTimes;
    }

    /**
     * @return the category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * @return the shortTitle
     */
    public String getShortTitle() {
        return shortTitle;
    }

    /**
     * @param shortTitle the shortTitle to set
     */
    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the modifyTime
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime the modifyTime to set
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the imgs
     */
    public String getImgs() {
        return imgs;
    }

    /**
     * @param imgs the imgs to set
     */
    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the surface
     */
    public String getSurface() {
        return surface;
    }

    /**
     * @param surface the surface to set
     */
    public void setSurface(String surface) {
        this.surface = surface;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
}