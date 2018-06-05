package io.renren.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.member.entity.MemberTaskEntity;
import io.renren.modules.member.entity.MemberTaskList;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员求助或者咨询
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@Mapper
public interface MemberTaskListDao extends BaseMapper<MemberTaskList> {
	
}
