package io.renren.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.sys.entity.JPushSet;
import io.renren.modules.sys.entity.SysCaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JPushDao  extends BaseMapper<JPushSet> {

}
