package io.renren.modules.member.service.impl;

import io.renren.modules.member.dao.MemberLifeDao;
import io.renren.modules.member.entity.MemberLifeEntity;
import io.renren.modules.member.service.MemberLifeService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


@Service("tblMemberLifeService")
public class MemberLifeServiceImpl extends ServiceImpl<MemberLifeDao, MemberLifeEntity> implements MemberLifeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MemberLifeEntity> page = this.selectPage(
                new Query<MemberLifeEntity>(params).getPage(),
                new EntityWrapper<MemberLifeEntity>()
        );

        return new PageUtils(page);
    }

}
