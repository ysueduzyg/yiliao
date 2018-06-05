package io.renren.modules.doctor.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.modules.doctor.dao.ContentTagTempDao;
import io.renren.modules.doctor.entity.ContentTagTempEntity;
import io.renren.modules.doctor.service.ContentTagTempService;
import org.springframework.stereotype.Service;

/**
 * @author xpf
 */
@Service("contentTagTempService")
public class ContentTagTempServiceImpl extends ServiceImpl<ContentTagTempDao, ContentTagTempEntity> implements ContentTagTempService
{
}
