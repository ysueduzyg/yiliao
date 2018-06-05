package io.renren.modules.app.form;


public class MemberListForm {

    private Integer id;
    /**
     * 级别
     */
    private String level;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**

     *
     */
    private Integer userId;
    /**
     * 医生id
     */
    private Integer doctorId;
    /**
     * 医生姓名
     */
    private String doctorName;

    private Integer houseKeeperId;

    private String houseKeeperName;

    public Integer getHouseKeeperId() {
        return houseKeeperId;
    }

    public void setHouseKeeperId(Integer houseKeeperId) {
        this.houseKeeperId = houseKeeperId;
    }

    public String getHouseKeeperName() {
        return houseKeeperName;
    }

    public void setHouseKeeperName(String houseKeeperName) {
        this.houseKeeperName = houseKeeperName;
    }

    /**
     * 社区
     */
    private Integer isCommunity;
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private Integer isEnterprise;
    private Integer communityId;
    /**
     * 社区名称
     */
    private String communityName ;
    /**
     * 企业名称
     */
    private String enterpriseName;
    /**
     * 企业id
     */
    private Integer enterpriseId;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证
     */
    private String idCard;
    /**
     *
     */
    private Long birthday;
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
     * 1、删除
     */
    private Integer isDelete;
    /**
     * 血型
     */
    private String blood;
    /**
     * 身高
     */
    private Double height;
    /**
     *
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

    public Integer getIsCommunity() {
        return isCommunity;
    }

    public void setIsCommunity(Integer isCommunity) {
        this.isCommunity = isCommunity;
    }

    public Integer getIsEnterprise() {
        return isEnterprise;
    }

    public void setIsEnterprise(Integer isEnterprise) {
        this.isEnterprise = isEnterprise;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Integer getBirthdayType() {
        return birthdayType;
    }

    public void setBirthdayType(Integer birthdayType) {
        this.birthdayType = birthdayType;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getWorkAt() {
        return workAt;
    }

    public void setWorkAt(String workAt) {
        this.workAt = workAt;
    }

    public Long getDueTime() {
        return dueTime;
    }

    public void setDueTime(Long dueTime) {
        this.dueTime = dueTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }
}
