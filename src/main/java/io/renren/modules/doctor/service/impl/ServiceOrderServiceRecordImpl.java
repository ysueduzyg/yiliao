package io.renren.modules.doctor.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.doctor.dao.ServiceOrderDao;
import io.renren.modules.doctor.dao.ServiceOrderRecordDao;
import io.renren.modules.doctor.entity.ServiceOrderEntity;
import io.renren.modules.doctor.entity.ServiceOrderRecordEntity;
import io.renren.modules.doctor.service.ServiceOrderRecordService;
import io.renren.modules.doctor.service.ServiceOrderService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("serviceOrderRecordService")
public class ServiceOrderServiceRecordImpl extends ServiceImpl<ServiceOrderRecordDao, ServiceOrderRecordEntity> implements ServiceOrderRecordService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<ServiceOrderRecordEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        Page<ServiceOrderRecordEntity> page = this.selectPage(
                new Query<ServiceOrderRecordEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

}
