package io.renren.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.member.entity.MemberChildEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 副卡关联表
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@Mapper
public interface MemberChildDao extends BaseMapper<MemberChildEntity> {
	
}
