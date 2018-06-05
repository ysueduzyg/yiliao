package io.renren.modules.doctor.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.doctor.entity.ContentHtmlEntity;

import java.util.List;
import java.util.Map;


/**
 * @author xpf
 */
public interface ContentHtmlService extends IService<ContentHtmlEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPageByMember(List<Long> ids, Map<String, Object> params);

    void insertAndPublish(ContentHtmlEntity entity);
    void updateAndPublish(ContentHtmlEntity entity);
    void publish(ContentHtmlEntity entity);

}

