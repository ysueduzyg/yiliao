package io.renren.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.Date;

@TableName("tbl_member_task_list")
public class MemberTaskList implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Integer id;
    private Integer userId;
    private Integer taskId;
    private Integer status;
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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
}
