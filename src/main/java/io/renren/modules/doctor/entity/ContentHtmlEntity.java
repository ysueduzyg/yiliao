package io.renren.modules.doctor.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("tbl_content_html")
public class ContentHtmlEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Long id;
    /**
     *
     */
    private Date gmtCreate;
    /**
     *
     */
    private Integer isDeleted;
    /**
     *
     */
    private Date gmtModified;
    /**
     *
     */
    private String content;
    /**
     *
     */
    private Long publishDate;
    /**
     *
     */
    private String publishAuthor;
    /**
     *
     */
    private String title;
    /**
     *
     */
    private String shortContent;
    /**
     *
     */
    private String img;

    private String tags;

    private Integer status ;
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    /**
     * 获取：
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }
    /**
     * 设置：
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
    /**
     * 获取：
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }
    /**
     * 设置：
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
    /**
     * 获取：
     */
    public Date getGmtModified() {
        return gmtModified;
    }
    /**
     * 设置：
     */
    public void setContent(String content) {
        this.content = content;
    }
    /**
     * 获取：
     */
    public String getContent() {
        return content;
    }
    /**
     * 设置：
     */
    public void setPublishDate(Long publishDate) {
        this.publishDate = publishDate;
    }
    /**
     * 获取：
     */
    public Long getPublishDate() {
        return publishDate;
    }
    /**
     * 设置：
     */
    public void setPublishAuthor(String publishAuthor) {
        this.publishAuthor = publishAuthor;
    }
    /**
     * 获取：
     */
    public String getPublishAuthor() {
        return publishAuthor;
    }
    /**
     * 设置：
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * 获取：
     */
    public String getTitle() {
        return title;
    }
    /**
     * 设置：
     */
    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }
    /**
     * 获取：
     */
    public String getShortContent() {
        return shortContent;
    }
    /**
     * 设置：
     */
    public void setImg(String img) {
        this.img = img;
    }
    /**
     * 获取：
     */
    public String getImg() {
        return img;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
