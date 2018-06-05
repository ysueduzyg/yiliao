package io.renren.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 副卡关联表
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@TableName("tbl_member_child")
public class MemberChildEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *
     */
    private Integer userId;

    private String avatar;

    private Integer sex;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }



    /**
     *
     */
    private Integer memberId;
    /**
     *
     */
    private String realName;
    /**
     *
     */
    private String mobile;
    /**
     * 与会员关系
     */
    private String relationship;
    /**
     *
     */
    private String workAt;
    /**
     *
     */
    private String address;


    private Integer isContact;


    public Integer getIsContact() {
        return isContact;
    }

    public void setIsContact(Integer isContact) {
        this.isContact = isContact;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 设置：
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取：
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置：
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：与会员关系
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    /**
     * 获取：与会员关系
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * 设置：
     */
    public void setWorkAt(String workAt) {
        this.workAt = workAt;
    }

    /**
     * 获取：
     */
    public String getWorkAt() {
        return workAt;
    }

    /**
     * 设置：
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：
     */
    public String getAddress() {
        return address;
    }


    private Date gmtModified;
    /**
     *
     */
    private Date gmtCreate;

    private Integer isDeleted;

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
}
