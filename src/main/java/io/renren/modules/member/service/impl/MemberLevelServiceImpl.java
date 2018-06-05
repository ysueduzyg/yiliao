package io.renren.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.member.dao.MemberLevelDao;
import io.renren.modules.member.entity.MemberLevelEntity;
import io.renren.modules.member.service.MemberLevelService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelDao, MemberLevelEntity> implements MemberLevelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MemberLevelEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted).orderBy("sort");
        Page<MemberLevelEntity> page = this.selectPage(
                new Query<MemberLevelEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

}
