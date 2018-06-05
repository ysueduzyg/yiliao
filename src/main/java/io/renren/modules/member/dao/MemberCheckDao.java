package io.renren.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.member.entity.MemberCheckEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员检查
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
@Mapper
public interface MemberCheckDao extends BaseMapper<MemberCheckEntity> {
	
}
