package io.renren.modules.member.service.impl;

import io.renren.modules.member.dao.MemberCardDao;
import io.renren.modules.member.entity.MemberCardEntity;
import io.renren.modules.member.service.MemberCardService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("memberCardService")
public class MemberCardServiceImpl extends ServiceImpl<MemberCardDao, MemberCardEntity> implements MemberCardService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MemberCardEntity> page = this.selectPage(
                new Query<MemberCardEntity>(params).getPage(),
                new EntityWrapper<MemberCardEntity>()
        );

        return new PageUtils(page);
    }

}
