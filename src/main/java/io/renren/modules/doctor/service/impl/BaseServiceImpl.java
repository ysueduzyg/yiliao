package io.renren.modules.doctor.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.modules.doctor.dao.CommunityDao;
import io.renren.modules.doctor.entity.CommunityEntity;
import io.renren.modules.doctor.service.BaseService;
import io.renren.modules.user.dao.UserDao;
import io.renren.modules.user.entity.UserEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xpf
 * @param <M>
 * @param <T>
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<BaseMapper<T>, T> implements BaseService<T>
{
    @Autowired
    private UserDao userDao;
    @Override
    public Integer saveUser(String mobile) {
        String password = mobile.substring(5);
        UserEntity user = new UserEntity();
        user.setMobile(mobile);
        user.setUsername(mobile);
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(password, salt).toHex());
        user.setSalt(salt);
        user.setDeviceId(null);
        user.setStatus(Constant.UserStatus.is_default.getValue());
        userDao.insert(user);
        return user.getUserId();
    }

    @Override
    public Integer saveUser(String mobile,String username, String password) {
        UserEntity user = new UserEntity();
        user.setMobile(mobile);
        user.setUsername(username);
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setSalt(salt);
        user.setPassword(new Sha256Hash(password,salt).toHex());
        user.setDeviceId(null);
        user.setStatus(Constant.UserStatus.is_default.getValue());
        userDao.insert(user);
        return user.getUserId();
    }
}
