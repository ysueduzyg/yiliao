package io.renren.modules.doctor.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.doctor.entity.DoctorEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 医生信息
 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@Mapper
public interface DoctorDao extends BaseMapper<DoctorEntity> {
	
}
