package io.renren.modules.doctor.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.doctor.entity.EnterpriseEntity;

import java.util.Map;

/**
 * 社区
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-05 17:34:55
 */
public interface EnterpriseService extends IService<EnterpriseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    public void  save (EnterpriseEntity enterpriseEntity);
}

