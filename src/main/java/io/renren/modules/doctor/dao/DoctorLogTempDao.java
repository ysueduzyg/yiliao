package io.renren.modules.doctor.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.doctor.entity.DoctorLogEntity;
import io.renren.modules.doctor.entity.DoctorLogTempEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-15 13:17:02
 */
@Mapper
public interface DoctorLogTempDao extends BaseMapper<DoctorLogTempEntity> {
	
}
