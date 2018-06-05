package io.renren.modules.doctor.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-05 17:34:55
 */
@TableName("tbl_enterprise")
public class EnterpriseEntity implements Serializable {
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
	 * 企业编号
	 */
	private String enterpriseCode;
	/**
	 * 企业管理员id
	 */
	private Integer enterpriseManagerId;
	/**
	 * 企业管理员名字
	 */
	private String enterpriseManagerName;
	/**
	 * 企业管理员电话
	 */
	private String enterpriseManagerMobile;
	/**
	 * 
	 */
	private String address;
	/**
	 * 客服电话
	 */
	private String mobile;
	/**
	 * 
	 */
	private String img;
	/**
	 * 1、已激活 0、未激活
	 */
	private Integer status;

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
	 * 设置：企业编号
	 */
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
	/**
	 * 获取：企业编号
	 */
	public String getEnterpriseCode() {
		return enterpriseCode;
	}
	/**
	 * 设置：企业管理员id
	 */
	public void setEnterpriseManagerId(Integer enterpriseManagerId) {
		this.enterpriseManagerId = enterpriseManagerId;
	}
	/**
	 * 获取：企业管理员id
	 */
	public Integer getEnterpriseManagerId() {
		return enterpriseManagerId;
	}
	/**
	 * 设置：企业管理员名字
	 */
	public void setEnterpriseManagerName(String enterpriseManagerName) {
		this.enterpriseManagerName = enterpriseManagerName;
	}
	/**
	 * 获取：企业管理员名字
	 */
	public String getEnterpriseManagerName() {
		return enterpriseManagerName;
	}
	/**
	 * 设置：企业管理员电话
	 */
	public void setEnterpriseManagerMobile(String enterpriseManagerMobile) {
		this.enterpriseManagerMobile = enterpriseManagerMobile;
	}
	/**
	 * 获取：企业管理员电话
	 */
	public String getEnterpriseManagerMobile() {
		return enterpriseManagerMobile;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：客服电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：客服电话
	 */
	public String getMobile() {
		return mobile;
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

	/**
	 * 设置：1、已激活 0、未激活
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1、已激活 0、未激活
	 */
	public Integer getStatus() {
		return status;
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
