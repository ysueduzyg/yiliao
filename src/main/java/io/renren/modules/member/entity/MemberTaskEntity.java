package io.renren.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员求助或者咨询
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@TableName("tbl_member_task")
public class MemberTaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 发布者
	 */
	private Integer publisherId;
	/**
	 * 主要接收人
	 */
	private Integer customerMainId;
	/**
	 * 其他接收人
	 */
	private Long time;
	/**
	 * 1、sos 2、普通
	 */
	private Integer type;
	/**
	 * 0、未接收 1、已接受
	 */
	private Integer status;
	/**
	 * 1、默认任务 2、转发任务
	 */
	private Integer taskType;

	private String memberSn;

	private String taskSn;

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
	 * 设置：发布者
	 */
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	/**
	 * 获取：发布者
	 */
	public Integer getPublisherId() {
		return publisherId;
	}
	/**
	 * 设置：主要接收人
	 */
	public void setCustomerMainId(Integer customerMainId) {
		this.customerMainId = customerMainId;
	}
	/**
	 * 获取：主要接收人
	 */
	public Integer getCustomerMainId() {
		return customerMainId;
	}
	/**
	 * 设置：其他接收人
	 */
	/**
	 * 获取：其他接收人
	 */
	/**
	 * 设置：
	 */
	public void setTime(Long time) {
		this.time = time;
	}
	/**
	 * 获取：
	 */
	public Long getTime() {
		return time;
	}
	/**
	 * 设置：1、sos 2、普通
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：1、sos 2、普通
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：0、未接收 1、已接受
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0、未接收 1、已接受
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：1、默认任务 2、转发任务
	 */
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	/**
	 * 获取：1、默认任务 2、转发任务
	 */
	public Integer getTaskType() {
		return taskType;
	}

	public String getMemberSn() {
		return memberSn;
	}

	public void setMemberSn(String memberSn) {
		this.memberSn = memberSn;
	}

	public String getTaskSn() {
		return taskSn;
	}

	public void setTaskSn(String taskSn) {
		this.taskSn = taskSn;
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
