package io.renren.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.member.dao.MemberTagDao;
import io.renren.modules.member.entity.MemberSurgery;
import io.renren.modules.member.entity.MemberTagEntity;
import io.renren.modules.member.service.MemberTagService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author xpf
 */
@Service("memberTagService")
public class MemberTagServiceImpl extends ServiceImpl<MemberTagDao, MemberTagEntity> implements MemberTagService {


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MemberTagEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).eq("member_id",params.get("id"));
        Page<MemberTagEntity> page = this.selectPage(
                new Query<MemberTagEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

}
