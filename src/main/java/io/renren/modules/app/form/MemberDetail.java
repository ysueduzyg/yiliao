package io.renren.modules.app.form;

import io.swagger.models.auth.In;

public class MemberDetail {
    private Integer userId;
    private Integer doctorId;
    private Integer houseKeeperId;
    private String username;
    private String name;
    private String doctorName;
    private String doctorAvatar;
    private String doctorLevelName;
    private String avatar;
    /**
     * 需要评价的数量
     */
    private Integer commentsNum;
    private Integer sos;
    private Integer lifeUsed;
    private Integer memberId;
    private String sn;
    private Integer sosUsed;
    private Integer life;
    private Integer marriage;

    public String getDoctorLevelName() {
        return doctorLevelName;
    }

    public void setDoctorLevelName(String doctorLevelName) {
        this.doctorLevelName = doctorLevelName;
    }

    public Integer getLife() {
        return life;
    }

    public void setLife(Integer life) {
        this.life = life;
    }

    public Integer getLifeUsed() {
        return lifeUsed;
    }

    public void setLifeUsed(Integer lifeUsed) {
        this.lifeUsed = lifeUsed;
    }

    public Integer getHouseKeeperId() {
        return houseKeeperId;
    }

    public void setHouseKeeperId(Integer houseKeeperId) {
        this.houseKeeperId = houseKeeperId;
    }

    public Integer getMarriage() {
        return marriage;
    }

    public void setMarriage(Integer marriage) {
        this.marriage = marriage;
    }

    public Integer getSosUsed() {
        return sosUsed;
    }

    public void setSosUsed(Integer sosUsed) {
        this.sosUsed = sosUsed;
    }


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorAvatar() {
        return doctorAvatar;
    }

    public void setDoctorAvatar(String doctorAvatar) {
        this.doctorAvatar = doctorAvatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getSos() {
        return sos;
    }

    public void setSos(Integer sos) {
        this.sos = sos;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }
}
