package io.renren.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员检查
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
@TableName("tbl_member_check")
public class MemberCheckEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	/**
	 * 
	 */
	private Integer memberId;
	/**
	 * 检查项目
	 */
	private String projectName;
	/**
	 * 检查地点
	 */
	private String checkAddress;
	/**
	 * 检查部位
	 */
	private String checkParts;
	/**
	 * 检查结果
	 */
	private String checkResults;
	/**
	 * 检查时间
	 */
	private Long checkTime;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 可能需要上传图片
	 */
	private String photos;

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
	/*
	 * 设置：检查项目
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：检查项目
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * 设置：检查地点
	 */
	public void setCheckAddress(String checkAddress) {
		this.checkAddress = checkAddress;
	}
	/**
	 * 获取：检查地点
	 */
	public String getCheckAddress() {
		return checkAddress;
	}
	/**
	 * 设置：检查部位
	 */
	public void setCheckParts(String checkParts) {
		this.checkParts = checkParts;
	}
	/**
	 * 获取：检查部位
	 */
	public String getCheckParts() {
		return checkParts;
	}
	/**
	 * 设置：检查结果
	 */
	public void setCheckResults(String checkResults) {
		this.checkResults = checkResults;
	}
	/**
	 * 获取：检查结果
	 */
	public String getCheckResults() {
		return checkResults;
	}
	/**
	 * 设置：检查时间
	 */
	public void setCheckTime(Long checkTime) {
		this.checkTime = checkTime;
	}
	/**
	 * 获取：检查时间
	 */
	public Long getCheckTime() {
		return checkTime;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}


	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
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
