package io.renren.modules.doctor.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xpf
 * @date 2018-05-15 13:17:02
 */
@TableName("tbl_doctor_log_temp")
public class DoctorLogTempEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 *
	 */
	private Integer doctorId;
	/**
	 *
	 */
	private Integer taskId;
	/**
	 *
	 */
	private Integer memberId;

	private String memberSn;
	private String taskSn;

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

	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private String photos;

	/**
	 * 
	 */
	private Integer isAppointment;

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
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	/**
	 * 获取：
	 */
	public Integer getDoctorId() {
		return doctorId;
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
	/**
	 * 设置：
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	/**
	 * 获取：
	 */
	public String getPhotos() {
		return photos;
	}

	/**
	 * 设置：
	 */
	public void setIsAppointment(Integer isAppointment) {
		this.isAppointment = isAppointment;
	}
	/**
	 * 获取：
	 */
	public Integer getIsAppointment() {
		return isAppointment;
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
