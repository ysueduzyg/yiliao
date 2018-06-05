package io.renren.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员当前病史
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@TableName("tbl_member_current")
public class MemberCurrentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer memberId;

	/**
	 * 主诉，当前问题
	 */
	private String currentQuestion;
	/**
	 * 当前病史
	 */
	private String currentDisease;


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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * 设置：主诉，当前问题
	 */
	public void setCurrentQuestion(String currentQuestion) {
		this.currentQuestion = currentQuestion;
	}
	/**
	 * 获取：主诉，当前问题
	 */
	public String getCurrentQuestion() {
		return currentQuestion;
	}
	/**
	 * 设置：当前病史
	 */
	public void setCurrentDisease(String currentDisease) {
		this.currentDisease = currentDisease;
	}
	/**
	 * 获取：当前病史
	 */
	public String getCurrentDisease() {
		return currentDisease;
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
