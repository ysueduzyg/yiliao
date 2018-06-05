package io.renren.modules.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@TableName("tbl_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 1、正常 2、冻结
	 */
	private Integer status;
	/**
	 * 设备号
	 */
	private String deviceId;

	/**
	 * 盐
	 */
	private String  salt;

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 设置：
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Integer
	getUserId() {
		return userId;
	}
	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置：1、正常 2、冻结
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1、正常 2、冻结
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：设备号
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 获取：设备号
	 */
	public String getDeviceId() {
		return deviceId;
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
