package io.renren.modules.doctor.service;

import com.baomidou.mybatisplus.service.IService;

public interface BaseService<T> extends IService<T> {
    /**
     * 根据手机号码创建用户并返回用户id
     * @param mobile
     * @return
     */
    Integer saveUser(String mobile);

    Integer saveUser (String mobile,String username,String password);
}
