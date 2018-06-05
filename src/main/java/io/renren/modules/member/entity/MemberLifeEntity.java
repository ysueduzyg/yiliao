package io.renren.modules.member.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 会员生活爱好
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
@TableName("tbl_member_life")
public class MemberLifeEntity implements Serializable {
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
	 * 
	 */
	private String dietPreferences;
	private String dietPreferencesRegular;
	private String movementPreferencesRegular;

	public String getDietPreferencesRegular() {
		return dietPreferencesRegular;
	}

	public void setDietPreferencesRegular(String dietPreferencesRegular) {
		this.dietPreferencesRegular = dietPreferencesRegular;
	}

	public String getMovementPreferencesRegular() {
		return movementPreferencesRegular;
	}

	public void setMovementPreferencesRegular(String movementPreferencesRegular) {
		this.movementPreferencesRegular = movementPreferencesRegular;
	}

	/**
	 * 
	 */
	private String movementPreferences;
	/**
	 * 
	 */
	private String sleepPreferences;
	/**
	 */
	private String smokingPreferences;
	/**
	 */
	private String drinkingPreferences;
	/**
	 * 娱乐活动
	 */
	private String entertainment;
	/**
	 * 
	 */
	private String other;

	private Date gmtModified;
	/**
	 *
	 */
	private Date gmtCreate;

	private Integer isDeleted;


	public String getDietPreferences() {
		return dietPreferences;
	}

	public void setDietPreferences(String dietPreferences) {
		this.dietPreferences = dietPreferences;
	}

	public String getMovementPreferences() {
		return movementPreferences;
	}

	public void setMovementPreferences(String movementPreferences) {
		this.movementPreferences = movementPreferences;
	}

	public String getSleepPreferences() {
		return sleepPreferences;
	}

	public void setSleepPreferences(String sleepPreferences) {
		this.sleepPreferences = sleepPreferences;
	}

	public String getSmokingPreferences() {
		return smokingPreferences;
	}

	public void setSmokingPreferences(String smokingPreferences) {
		this.smokingPreferences = smokingPreferences;
	}

	public String getDrinkingPreferences() {
		return drinkingPreferences;
	}

	public void setDrinkingPreferences(String drinkingPreferences) {
		this.drinkingPreferences = drinkingPreferences;
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

	 * 设置：娱乐活动
	 */
	public void setEntertainment(String entertainment) {
		this.entertainment = entertainment;
	}
	/**
	 * 获取：娱乐活动
	 */
	public String getEntertainment() {
		return entertainment;
	}
	/**
	 * 设置：
	 */
	public void setOther(String other) {
		this.other = other;
	}
	/**
	 * 获取：
	 */
	public String getOther() {
		return other;
	}



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
