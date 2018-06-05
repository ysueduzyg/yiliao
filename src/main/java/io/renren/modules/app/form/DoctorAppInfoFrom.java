package io.renren.modules.app.form;

import io.renren.modules.member.entity.MemberEntity;

import java.util.List;

public class DoctorAppInfoFrom {
    private Integer sosNum;
    private Integer msgNum;
    private Integer logNum;
    private List<MemberEntity> members;

    public Integer getSosNum() {
        return sosNum;
    }

    public void setSosNum(Integer sosNum) {
        this.sosNum = sosNum;
    }

    public Integer getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(Integer msgNum) {
        this.msgNum = msgNum;
    }

    public Integer getLogNum() {
        return logNum;
    }

    public void setLogNum(Integer logNum) {
        this.logNum = logNum;
    }

    public List<MemberEntity> getMembers() {
        return members;
    }

    public void setMembers(List<MemberEntity> members) {
        this.members = members;
    }
}
