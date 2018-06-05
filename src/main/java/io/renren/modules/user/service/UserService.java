package io.renren.modules.user.service;


import com.baomidou.mybatisplus.service.IService;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.user.entity.UserEntity;

/**
 * 用户
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2017-03-23 15:22:06
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回用户ID
	 */
	long login(LoginForm form);


	/**
	 * 会员登录
	 * @param form
	 * @return
	 */
	MemberEntity loginByMember(LoginForm form);

	/**
	 * 医生登录
	 * @param form
	 * @return
	 */
	DoctorEntity loginByDoctor(LoginForm form);
}
