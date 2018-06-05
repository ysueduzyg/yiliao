package io.renren.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.member.dao.MemberAllergyDao;
import io.renren.modules.member.entity.MemberAllergyEntity;
import io.renren.modules.member.service.MemberAllergyService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("tblMemberAllergyService")
public class MemberAllergyServiceImpl extends ServiceImpl<MemberAllergyDao, MemberAllergyEntity> implements MemberAllergyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MemberAllergyEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).eq("member_id",params.get("id"));
        Page<MemberAllergyEntity> page = this.selectPage(
                new Query<MemberAllergyEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

}
