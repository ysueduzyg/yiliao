package io.renren.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.member.dao.MemberAllergyDao;
import io.renren.modules.member.dao.MemberSurgeryDao;
import io.renren.modules.member.entity.MemberAllergyEntity;
import io.renren.modules.member.entity.MemberSurgery;
import io.renren.modules.member.service.MemberAllergyService;
import io.renren.modules.member.service.MemberSurgeryService;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * @author xpf
 */
@Service("memberSurgeryService")
public class MemberSurgeryServiceImpl extends ServiceImpl<MemberSurgeryDao, MemberSurgery> implements MemberSurgeryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MemberSurgery> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).eq("member_id",params.get("id"));
        Page<MemberSurgery> page = this.selectPage(
                new Query<MemberSurgery>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

}
