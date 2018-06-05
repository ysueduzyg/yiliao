package io.renren.modules.backend.form;

/**
 * 医生的草稿列表
 * @author xpf
 */
public class DoctorBackendTempLogForm {
    private String memberName;
    private String memberSn;
    private Integer logId;
    private Integer type;
    private String taskType;
    private Long createTime;
    private String taskSn;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberSn() {
        return memberSn;
    }

    public void setMemberSn(String memberSn) {
        this.memberSn = memberSn;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getTaskSn() {
        return taskSn;
    }

    public void setTaskSn(String taskSn) {
        this.taskSn = taskSn;
    }
}
