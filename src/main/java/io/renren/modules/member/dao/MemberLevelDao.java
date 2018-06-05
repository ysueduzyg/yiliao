package io.renren.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.member.entity.MemberLevelEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员卡
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-05 16:40:37
 */
@Mapper
public interface MemberLevelDao extends BaseMapper<MemberLevelEntity> {
	
}
