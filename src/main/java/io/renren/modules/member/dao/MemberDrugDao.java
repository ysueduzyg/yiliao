package io.renren.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.member.entity.MemberDrugEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员用药
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
@Mapper
public interface MemberDrugDao extends BaseMapper<MemberDrugEntity> {
	
}
