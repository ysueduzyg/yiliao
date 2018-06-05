package io.renren.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.member.entity.MemberLifeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员生活爱好
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
@Mapper
public interface MemberLifeDao extends BaseMapper<MemberLifeEntity> {
	
}
