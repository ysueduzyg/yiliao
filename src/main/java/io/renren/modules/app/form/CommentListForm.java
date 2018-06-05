package io.renren.modules.app.form;

public class CommentListForm {
    private Integer id;
    private String taskSn;
    private Integer taskId;
    private Integer memberId;
    private Integer focusId;
    private String focusName;
    private String focusAvatar;
    private Integer focusType;
    private Integer status;
    private Integer type;
    private String memberSn;
    private String getOrderTime;
    private Integer taskType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 保留字段
     */
    private String content;
    /**
     * 接单速度
     */
    private Double speed;
    /**
     * 效果
     */
    private Double effect;
    /**
     * 态度
     */
    private Double attitude;
    private String createTime;

    private String taskTime;

    public Integer getFocusId() {
        return focusId;
    }

    public void setFocusId(Integer focusId) {
        this.focusId = focusId;
    }

    public String getFocusName() {
        return focusName;
    }

    public void setFocusName(String focusName) {
        this.focusName = focusName;
    }

    public String getFocusAvatar() {
        return focusAvatar;
    }

    public void setFocusAvatar(String focusAvatar) {
        this.focusAvatar = focusAvatar;
    }

    public Integer getFocusType() {
        return focusType;
    }

    public void setFocusType(Integer focusType) {
        this.focusType = focusType;
    }

    public String getTaskSn() {
        return taskSn;
    }

    public void setTaskSn(String taskSn) {
        this.taskSn = taskSn;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMemberSn() {
        return memberSn;
    }

    public void setMemberSn(String memberSn) {
        this.memberSn = memberSn;
    }

    public String getGetOrderTime() {
        return getOrderTime;
    }

    public void setGetOrderTime(String getOrderTime) {
        this.getOrderTime = getOrderTime;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getEffect() {
        return effect;
    }

    public void setEffect(Double effect) {
        this.effect = effect;
    }

    public Double getAttitude() {
        return attitude;
    }

    public void setAttitude(Double attitude) {
        this.attitude = attitude;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }




}
