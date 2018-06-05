package io.renren.modules.member.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.member.entity.MemberLifeEntity;
import io.renren.modules.member.entity.MemberSurgery;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author xpf
 */
@Mapper
public interface MemberSurgeryDao extends BaseMapper<MemberSurgery> {

}