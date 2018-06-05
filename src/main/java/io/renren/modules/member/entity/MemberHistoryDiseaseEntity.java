package io.renren.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 过往病史
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
@TableName("tbl_member_history_disease")
public class MemberHistoryDiseaseEntity implements Serializable {
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
	 * 病例
	 */
	private String disease;
	/**
	 * 治疗结果
	 */
	private String treatmentResults;
	/**
	 * 治疗单位
	 */
	private String treatmentCompany;
	/**
	 * 发生时间
	 */
	private Long triggerTime;

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

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * 设置：病例
	 */
	public void setDisease(String disease) {
		this.disease = disease;
	}
	/**
	 * 获取：病例
	 */
	public String getDisease() {
		return disease;
	}
	/**
	 * 设置：治疗结果
	 */
	public void setTreatmentResults(String treatmentResults) {
		this.treatmentResults = treatmentResults;
	}
	/**
	 * 获取：治疗结果
	 */
	public String getTreatmentResults() {
		return treatmentResults;
	}
	/**
	 * 设置：治疗单位
	 */
	public void setTreatmentCompany(String treatmentCompany) {
		this.treatmentCompany = treatmentCompany;
	}
	/**
	 * 获取：治疗单位
	 */
	public String getTreatmentCompany() {
		return treatmentCompany;
	}
	/**
	 * 设置：发生时间
	 */
	public void setTriggerTime(Long triggerTime) {
		this.triggerTime = triggerTime;
	}
	/**
	 * 获取：发生时间
	 */
	public Long getTriggerTime() {
		return triggerTime;
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
