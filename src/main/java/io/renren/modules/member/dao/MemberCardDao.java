package io.renren.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.member.entity.MemberCardEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员卡
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
@Mapper
public interface MemberCardDao extends BaseMapper<MemberCardEntity> {
	
}
