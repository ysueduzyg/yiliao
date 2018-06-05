package io.renren.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.member.dao.MemberDrugDao;
import io.renren.modules.member.entity.MemberDrugEntity;
import io.renren.modules.member.service.MemberDrugService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("tblMemberDrugService")
public class MemberDrugServiceImpl extends ServiceImpl<MemberDrugDao, MemberDrugEntity> implements MemberDrugService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MemberDrugEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id",params.get("id")).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        Page<MemberDrugEntity> page = this.selectPage(
                new Query<MemberDrugEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

}
