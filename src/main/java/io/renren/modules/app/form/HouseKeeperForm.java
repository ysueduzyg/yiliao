package io.renren.modules.app.form;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

public class HouseKeeperForm {
    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
    private String username;
    private String sn;
    private String realName;
    private Long birthday;
    private Long workTime;
    private String desc;
    private String avatar;

    private int sex;

    //用户id
    private Integer userId;
    //医生id
    private Integer houseKeeperId;
    //社区id
    private Integer communityId;
    //类型
    private Integer type;
    //企业id
    private Integer enterpriseId;
    //企业名称
    private String  enterpriseName;
    //社区名称
    private String communityName;

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

    public String  getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String  enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHouseKeeperId() {
        return houseKeeperId;
    }

    public void setHouseKeeperId(Integer houseKeeperId) {
        this.houseKeeperId = houseKeeperId;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
}
