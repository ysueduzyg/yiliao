package io.renren.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员信息
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@TableName("tbl_member")
public class MemberEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer id;
    /**
     * 级别
     */
    private String level;
    private String avatar;
    private String mobile;
    /**
     * 是否婚配 1是已婚 0 未婚
     */
    private Integer marriage;

    public String getMobile() {
        return mobile;
    }


    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }



    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 医生id
     */
    private Integer doctorId;

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证
     */
    private String idCard;
    /**
     * 出生日期
     */
    private  Long birthday;
    /**
     * 1、阴历 （农历）2、阳历 （公历）
     */
    private Integer birthdayType;
    /**
     * 民族
     */
    private String national;
    /**
     * 1、男 2、女
     */
    private Integer sex;
    /**
     * 地址
     */
    private String address;
    /**
     * 文化程度 0、无 1、小学、2、初中、3、高中、4、中职、5、大专、6、本科、7、硕士、8、博士
     */
    private Integer education;
    /**
     *
     */
    private String professional;
    /**
     * 年收入
     */
    private String income;
    /**
     * 工作单位
     */
    private String workAt;
    /**
     * 会员卡到期时间
     */
    private Long dueTime;

    /**
     * 血型
     */
    private String blood;
    /**
     * 身高
     */
    private Double height;
    /**
     * 体重
     */
    private Double weight;
    /**
     * 血压
     */
    private String bloodPressure;
    /**
     * 血糖
     */
    private String bloodSugar;

    /**
     * 是否企业
     */
    private Integer isEnterprise;
    /**
     * 是否社区
     */
    private Integer isCommunity;
    /**
     * 企业id
     */
    private Integer enterpriseId;
    /**
     * 社区id
     */
    private Integer communityId;
    /**
     * 会员编号
     */
    private String sn;


    private Date gmtModified;
    /**
     *
     */
    private Date gmtCreate;

    private Integer isDeleted;

    private Integer houseKeeperId;


    public Integer getHouseKeeperId() {
        return houseKeeperId;
    }

    public void setHouseKeeperId(Integer houseKeeperId) {
        this.houseKeeperId = houseKeeperId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getIsEnterprise() {
        return isEnterprise;
    }

    public void setIsEnterprise(Integer isEnterprise) {
        this.isEnterprise = isEnterprise;
    }

    public Integer getIsCommunity() {
        return isCommunity;
    }

    public void setIsCommunity(Integer isCommunity) {
        this.isCommunity = isCommunity;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getDueTime() {
        return dueTime;
    }

    public void setDueTime(Long dueTime) {
        this.dueTime = dueTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
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


    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取：真实姓名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置：身份证
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取：身份证
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置：
     */
    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取：
     */
    public Long getBirthday() {
        return birthday;
    }

    /**
     * 设置：1、阴历 （农历）2、阳历 （公历）
     */
    public void setBirthdayType(Integer birthdayType) {
        this.birthdayType = birthdayType;
    }

    /**
     * 获取：1、阴历 （农历）2、阳历 （公历）
     */
    public Integer getBirthdayType() {
        return birthdayType;
    }

    /**
     * 设置：民族
     */
    public void setNational(String national) {
        this.national = national;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取：民族
     */
    public String getNational() {
        return national;
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

    /**
     * 设置：地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置：文化程度 0、无 1、小学、2、初中、3、高中、4、中职、5、大专、6、本科、7、硕士、8、博士
     */
    public void setEducation(Integer education) {
        this.education = education;
    }

    /**
     * 获取：文化程度 0、无 1、小学、2、初中、3、高中、4、中职、5、大专、6、本科、7、硕士、8、博士
     */
    public Integer getEducation() {
        return education;
    }

    /**
     * 设置：
     */
    public void setProfessional(String professional) {
        this.professional = professional;
    }

    /**
     * 获取：
     */
    public String getProfessional() {
        return professional;
    }

    /**
     * 设置：年收入
     */
    public void setIncome(String income) {
        this.income = income;
    }

    /**
     * 获取：年收入
     */
    public String getIncome() {
        return income;
    }

    /**
     * 设置：工作单位
     */
    public void setWorkAt(String workAt) {
        this.workAt = workAt;
    }

    /**
     * 获取：工作单位
     */
    public String getWorkAt() {
        return workAt;
    }

    /**
     * 获取：会员卡到期时间
     */
    /**
     * 设置：血型
     */
    public void setBlood(String blood) {
        this.blood = blood;
    }

    /**
     * 获取：血型
     */
    public String getBlood() {
        return blood;
    }

    /**
     * 设置：身高
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * 获取：身高
     */
    public Double getHeight() {
        return height;
    }

    /**
     * 设置：
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * 获取：
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * 设置：血压
     */
    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    /**
     * 获取：血压
     */
    public String getBloodPressure() {
        return bloodPressure;
    }

    /**
     * 设置：血糖
     */
    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    /**
     * 获取：血糖
     */
    public String getBloodSugar() {
        return bloodSugar;
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
}
