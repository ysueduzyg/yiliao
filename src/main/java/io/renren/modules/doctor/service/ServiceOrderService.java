package io.renren.modules.doctor.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.doctor.entity.ServiceOrderEntity;

import java.util.Map;

/**
 * 会员 的求救任务 被医生接单后会生成一条记录 
这条记录将用来记录改任务是否已完成 
或者是又管理人员将一个任务分配到某个空闲医生上

 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
public interface ServiceOrderService extends IService<ServiceOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryTaskPage(Map<String, Object> params,Integer doctorId);
    PageUtils queryByDoctorPage(Map<String, Object> params,Integer doctorId);
}

