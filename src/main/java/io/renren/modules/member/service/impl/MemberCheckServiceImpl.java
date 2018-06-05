package io.renren.modules.member.service.impl;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.member.dao.MemberCheckDao;
import io.renren.modules.member.entity.MemberCheckEntity;
import io.renren.modules.member.service.MemberCheckService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("MemberCheckService")
public class MemberCheckServiceImpl extends ServiceImpl<MemberCheckDao, MemberCheckEntity> implements MemberCheckService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MemberCheckEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id",params.get("id")).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        Page<MemberCheckEntity> page = this.selectPage(
                new Query<MemberCheckEntity>(params).getPage(),
                wrapper
        );
        return new PageUtils(page);
    }

}
