package io.renren.modules.doctor.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.doctor.entity.ServiceOrderRecordEntity;
import io.renren.modules.doctor.entity.TagsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xpf
 */
@Mapper
public interface TagsDao  extends BaseMapper<TagsEntity> {
}
