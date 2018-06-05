package io.renren.modules.doctor.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.doctor.entity.CommunityEntity;

import java.util.Map;

/**
 * 社区
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
public interface CommunityService extends IService<CommunityEntity> {

    PageUtils queryPage(Map<String, Object> params);


    void  save (CommunityEntity communityEntity);
}

