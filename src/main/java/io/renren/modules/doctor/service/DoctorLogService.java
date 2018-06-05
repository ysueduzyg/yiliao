package io.renren.modules.doctor.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.doctor.entity.DoctorLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-15 13:17:02
 */
public interface DoctorLogService extends IService<DoctorLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryDoctorPage(Map<String, Object> params,Integer doctorId);
    PageUtils queryBackendDoctorPage(Map<String, Object> params,Integer doctorId);
    PageUtils queryBackendPage(Map<String, Object> params);
}

