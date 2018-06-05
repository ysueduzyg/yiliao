package io.renren.modules.user.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
