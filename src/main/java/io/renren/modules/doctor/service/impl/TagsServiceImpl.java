package io.renren.modules.doctor.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.doctor.dao.TagsDao;
import io.renren.modules.doctor.entity.ServiceOrderRecordEntity;
import io.renren.modules.doctor.entity.TagsEntity;
import io.renren.modules.doctor.service.TagsService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xpf
 */
@Service("tagsService")
public class TagsServiceImpl  extends ServiceImpl<TagsDao, TagsEntity> implements TagsService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<TagsEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        Page<TagsEntity> page = this.selectPage(
                new Query<TagsEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }
}
