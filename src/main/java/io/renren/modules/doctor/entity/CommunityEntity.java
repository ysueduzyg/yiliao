package io.renren.modules.doctor.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 社区
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@TableName("tbl_community")
public class CommunityEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *
     */
    private String name;
    /**
     * 社区管理员id
     */
    private Integer communityManagerId;
    private String communityCode;

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    /**
     * 社区管理员名字
     */
    private String communityManagerName;
    /**
     * 社区管理员电话
     */
    private String communityManagerMobile;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private String mobile;
    /**
     *
     */
    private String img;
    /**
     *
     */
    private Integer isDeleted;
    /**
     * 1、已激活 0、未激活
     */
    private Integer status;

    private Date gmtModified;
    /**
     *
     */
    private Date gmtCreate;

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
     * 设置：
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：社区管理员id
     */
    public void setCommunityManagerId(Integer communityManagerId) {
        this.communityManagerId = communityManagerId;
    }

    /**
     * 获取：社区管理员id
     */
    public Integer getCommunityManagerId() {
        return communityManagerId;
    }

    /**
     * 设置：社区管理员名字
     */
    public void setCommunityManagerName(String communityManagerName) {
        this.communityManagerName = communityManagerName;
    }

    /**
     * 获取：社区管理员名字
     */
    public String getCommunityManagerName() {
        return communityManagerName;
    }

    /**
     * 设置：社区管理员电话
     */
    public void setCommunityManagerMobile(String communityManagerMobile) {
        this.communityManagerMobile = communityManagerMobile;
    }

    /**
     * 获取：社区管理员电话
     */
    public String getCommunityManagerMobile() {
        return communityManagerMobile;
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



    /**
     * 设置：1、已激活 0、未激活
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：1、已激活 0、未激活
     */
    public Integer getStatus() {
        return status;
    }
}
