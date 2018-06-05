package io.renren.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@TableName("tbl_member_comments")
public class MemberCommentsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    private Integer status;
    private Integer userId;
    private Integer memberId;
    private String taskSn;
    private String memberSn;
    private Date getOrderTime;
    private Long taskTime;
    /**
     * 1、会员 2、会员子女
     */
    private Integer type;
    private Double speed;
    private Double effect;
    private Double attitude;
    private String content;
    private Integer focusType;
    private Integer focusId;
    private Integer taskId;
    private Integer taskType;
    private Date gmtModified;
    private Date gmtCreate;
    private Integer isDeleted;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getTaskSn() {
        return taskSn;
    }

    public void setTaskSn(String taskSn) {
        this.taskSn = taskSn;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getFocusType() {
        return focusType;
    }

    public void setFocusType(Integer focusType) {
        this.focusType = focusType;
    }

    public Integer getFocusId() {
        return focusId;
    }

    public void setFocusId(Integer focusId) {
        this.focusId = focusId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：1、会员 2、会员子女
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取：1、会员 2、会员子女
     */
    public Integer getType() {
        return type;
    }


    /**
     * 设置：接单速度
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     * 获取：接单速度
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * 设置：效果
     */
    public void setEffect(Double effect) {
        this.effect = effect;
    }

    /**
     * 获取：效果
     */
    public Double getEffect() {
        return effect;
    }

    /**
     * 设置：态度
     */
    public void setAttitude(Double attitude) {
        this.attitude = attitude;
    }

    /**
     * 获取：态度
     */
    public Double getAttitude() {
        return attitude;
    }


    /**
     * 设置：保留字段
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：保留字段
     */
    public String getContent() {
        return content;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMemberSn() {
        return memberSn;
    }

    public void setMemberSn(String memberSn) {
        this.memberSn = memberSn;
    }


    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGetOrderTime() {
        return getOrderTime;
    }

    public void setGetOrderTime(Date getOrderTime) {
        this.getOrderTime = getOrderTime;
    }

    public Long getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(Long taskTime) {
        this.taskTime = taskTime;
    }
}
