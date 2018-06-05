package io.renren.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import io.renren.modules.member.entity.MemberAllergyEntity;

/**
 * 会员过敏情况
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
@Mapper
public interface MemberAllergyDao extends BaseMapper<MemberAllergyEntity> {
	
}
