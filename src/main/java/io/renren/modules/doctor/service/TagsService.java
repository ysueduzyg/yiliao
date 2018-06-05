package io.renren.modules.doctor.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.doctor.entity.TagsEntity;

import java.util.Map;

/**
 * @author xpf
 */
public interface TagsService extends IService<TagsEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
