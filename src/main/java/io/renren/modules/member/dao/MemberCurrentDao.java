package io.renren.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.member.entity.MemberCurrentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员当前病史
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@Mapper
public interface MemberCurrentDao extends BaseMapper<MemberCurrentEntity> {
	
}
