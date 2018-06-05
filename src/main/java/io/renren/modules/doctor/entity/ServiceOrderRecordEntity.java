package io.renren.modules.doctor.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员 的求救任务 被医生接单后会生成一条记录 
这条记录将用来记录改任务是否已完成 
或者是又管理人员将一个任务分配到某个空闲医生上

 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@TableName("tbl_service_order_record")
public class ServiceOrderRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer taskId;
	/**
	 * 
	 */
	private Integer memberId;
	/**
	 * 医生或者管家
	 */
	private Integer receiveId;
	/**
	 * 1、已接单 2、已处理（需要填写日志、病例） 3、已完成（已经填写日志、病例）
	 */
	private Integer type;
	private String remark;
	private String memberSn;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private String taskSn;
	private Integer status;
	private String  optionName;

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getTaskSn() {
		return taskSn;
	}

	public void setTaskSn(String taskSn) {
		this.taskSn = taskSn;
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
	 * 设置：
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：
	 */
	public Integer getTaskId() {
		return taskId;
	}


	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	/**
	 * 设置：1、已接单 2、已处理（需要填写日志、病例） 3、已完成（已经填写日志、病例）
	 */


	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}

	public String getMemberSn() {
		return memberSn;
	}

	public void setMemberSn(String memberSn) {
		this.memberSn = memberSn;
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
