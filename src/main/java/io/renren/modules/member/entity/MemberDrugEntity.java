package io.renren.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员用药
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
@TableName("tbl_member_drug")
public class MemberDrugEntity implements Serializable {
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
	 * 疾病名称
	 */
	private String disease;
	/**
	 * 药品名称
	 */
	private String drug;
	/**
	 * 使用剂量方式
	 */
	private String function;



	private Integer isUsing;


	private Long useTime;

	public Integer getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(Integer isUsing) {
		this.isUsing = isUsing;
	}

	public Long getUseTime() {
		return useTime;
	}

	public void setUseTime(Long useTime) {
		this.useTime = useTime;
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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**

	 * 设置：疾病名称
	 */
	public void setDisease(String disease) {
		this.disease = disease;
	}
	/**
	 * 获取：疾病名称
	 */
	public String getDisease() {
		return disease;
	}
	/**
	 * 设置：药品名称
	 */
	public void setDrug(String drug) {
		this.drug = drug;
	}
	/**
	 * 获取：药品名称
	 */
	public String getDrug() {
		return drug;
	}
	/**
	 * 设置：使用剂量方式
	 */
	public void setFunction(String function) {
		this.function = function;
	}
	/**
	 * 获取：使用剂量方式
	 */
	public String getFunction() {
		return function;
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
