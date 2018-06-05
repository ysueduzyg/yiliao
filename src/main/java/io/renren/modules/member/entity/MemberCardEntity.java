package io.renren.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员卡
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
@TableName("tbl_member_card")
public class MemberCardEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private Long startTime;
	/**
	 * 
	 */
	private Long endTime;
	/**
	 * 状态 1、正常 2、过期、3、冻结、0 、未分配用户
	 */
	private Integer status;
	/**
	 * 
	 */
	private String img;

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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：
	 */
	public Long getStartTime() {
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：
	 */
	public Long getEndTime() {
		return endTime;
	}
	/**
	 * 设置：状态 1、正常 2、过期、3、冻结、0 、未分配用户
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态 1、正常 2、过期、3、冻结、0 、未分配用户
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：
	 */
	public String getImg() {
		return img;
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
