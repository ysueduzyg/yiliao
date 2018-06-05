package io.renren.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.member.dao.MemberGenenticDiseaseDao;
import io.renren.modules.member.entity.MemberGenenticDiseaseEntity;
import io.renren.modules.member.service.MemberGenenticDiseaseService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



/**
 * @author xpf
 */
@Service("memberGenenticDiseaseService")
public class MemberGenenticDiseaseServiceImpl extends ServiceImpl<MemberGenenticDiseaseDao, MemberGenenticDiseaseEntity> implements MemberGenenticDiseaseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MemberGenenticDiseaseEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).eq("member_id",params.get("id"));
        Page<MemberGenenticDiseaseEntity> page = this.selectPage(
                new Query<MemberGenenticDiseaseEntity>(params).getPage(),
                entityWrapper
        );

        return new PageUtils(page);
    }

}
