package io.renren.modules.doctor.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 医生信息
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@TableName("tbl_doctor")
public class DoctorEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId
    private Integer id;
    /**
     * 所属社区
     */
    private Integer communityId;
    /**
     * 医生类型 1、社区医生 2、企业医生
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
     *
     */
    private Integer isDeleted;
    /**
     * 1、男 2、女
     */
    private Integer sex;
    /**
     * 毕业院校
     */
    private String school;
    /**
     * 工作单位
     */
    private String workAt;
    /**
     * 职称
     */
    private String jobLevel;
    /**
     * 联系方式
     */
    private String mobile;
    /**
     * 专业特长
     */
    private String professional;

    private String sn;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getWorkAt() {
        return workAt;
    }

    public void setWorkAt(String workAt) {
        this.workAt = workAt;
    }

    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
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
     * 设置：所属社区
     */
    public void setCommunityId(Integer cId) {
        this.communityId = cId;
    }

    /**
     * 获取：所属社区
     */
    public Integer getCommunityId() {
        return communityId;
    }

    /**
     * 设置：姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：出生日期
     */
    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取：出生日期
     */
    public Long getBirthday() {
        return birthday;
    }

    /**
     * 设置：工作时间
     */
    public void setWorkTime(Long workTime) {
        this.workTime = workTime;
    }

    /**
     * 获取：工作时间
     */
    public Long getWorkTime() {
        return workTime;
    }

    /**
     * 设置：简介
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取：简介
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 设置：头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取：头像
     */
    public String getAvatar() {
        return avatar;
    }


    /**
     * 设置：1、男 2、女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取：1、男 2、女
     */
    public Integer getSex() {
        return sex;
    }


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

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
