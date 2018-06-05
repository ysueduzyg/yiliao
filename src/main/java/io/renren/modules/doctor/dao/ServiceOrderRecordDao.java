package io.renren.modules.doctor.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.renren.modules.doctor.entity.ServiceOrderEntity;
import io.renren.modules.doctor.entity.ServiceOrderRecordEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员 的求救任务 被医生接单后会生成一条记录 
这条记录将用来记录改任务是否已完成 
或者是又管理人员将一个任务分配到某个空闲医生上

 * 
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
@Mapper
public interface ServiceOrderRecordDao extends BaseMapper<ServiceOrderRecordEntity> {
	
}
