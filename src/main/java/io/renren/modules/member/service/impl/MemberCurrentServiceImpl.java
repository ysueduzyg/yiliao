package io.renren.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.member.dao.MemberCurrentDao;
import io.renren.modules.member.entity.MemberCurrentEntity;
import io.renren.modules.member.service.MemberCurrentService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("tblMemberCurrentService")
public class MemberCurrentServiceImpl extends ServiceImpl<MemberCurrentDao, MemberCurrentEntity> implements MemberCurrentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MemberCurrentEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id",params.get("id")).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        Page<MemberCurrentEntity> page = this.selectPage(
                new Query<MemberCurrentEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

}
