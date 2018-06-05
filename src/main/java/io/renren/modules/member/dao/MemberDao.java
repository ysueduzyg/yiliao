package io.renren.modules.member.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.app.form.MemberListForm;
import io.renren.modules.member.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员信息
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {


}
