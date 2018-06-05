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
 * @date 2018-05-05 16:40:37
 */
@TableName("tbl_member_level")
public class MemberLevelEntity implements Serializable {
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
	 */
	private Integer sort;
	private Integer sos;
	private Integer life;

	public Integer getLife() {
		return life;
	}

	public void setLife(Integer life) {
		this.life = life;
	}

	public Integer getSos() {
		return sos;
	}
	public void setSos(Integer sos) {
		this.sos = sos;
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
	 * 设置：状态 1、正常 2、过期、3、冻结、0 、未分配用户
	 */
	public void setSort(Integer status) {
		this.sort = status;
	}
	/**
	 * 获取：状态 1、正常 2、过期、3、冻结、0 、未分配用户
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置：状态 1、正常 3、冻结、0 、未分配用户
	 */
	/**
	 * 获取：状态 1、正常 3、冻结、0 、未分配用户
	 */
	private Date gmtModified;

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
