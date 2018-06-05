package io.renren.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.member.dao.MemberHistoryDiseaseDao;
import io.renren.modules.member.entity.MemberHistoryDiseaseEntity;
import io.renren.modules.member.service.MemberHistoryDiseaseService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;



@Service("memberHistoryDiseaseService")
public class MemberHistoryDiseaseServiceImpl extends ServiceImpl<MemberHistoryDiseaseDao, MemberHistoryDiseaseEntity> implements MemberHistoryDiseaseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MemberHistoryDiseaseEntity> historyDiseaseEntityWrapper = new EntityWrapper<>();
        historyDiseaseEntityWrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).eq("member_id",params.get("id")).orderBy("trigger_time",false);
        Page<MemberHistoryDiseaseEntity> page = this.selectPage(
                new Query<MemberHistoryDiseaseEntity>(params).getPage(),
                historyDiseaseEntityWrapper
        );
        return new PageUtils(page);
    }

}
