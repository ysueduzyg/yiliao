package io.renren.modules.doctor.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

@TableName("tbl_house_keeper")
public class HouseKeeperEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @TableId
    private Integer id;
    /**
     * 所属社区
     */
    private Integer communityId;
    /**
     * 管家类型 1、社区管家 2、企业管家
     */
    private Integer type;
    /**
     * 所属企业
     */
    private Integer enterpriseId;
    /**
     * 所属企业名称
     */
    private String enterpriseName;
    /**
     * 所属社区
     */
    private String communityName;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 出生日期
     */
    private Long birthday;
    /**
     * 工作时间
     */
    private Long workTime;
    /**
     * 简介
     */
    private String desc;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 1、男 2、女
     */
    private Integer sex;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Long getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Long workTime) {
        this.workTime = workTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 联系方式
     */

    private String mobile;



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




    private String sn;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
