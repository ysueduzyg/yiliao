package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.member.entity.MemberTagEntity;
import io.renren.modules.sys.dao.JPushDao;
import io.renren.modules.sys.dao.SysCaptchaDao;
import io.renren.modules.sys.entity.JPushSet;
import io.renren.modules.sys.entity.SysCaptchaEntity;
import io.renren.modules.sys.service.JPushService;
import io.renren.modules.sys.service.SysCaptchaService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("jPushService")
public class JPushServiceImpl extends ServiceImpl<JPushDao, JPushSet> implements JPushService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<JPushSet> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        Page<JPushSet> page = this.selectPage(
                new Query<JPushSet>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }
    @Override
    public JPushSet getDoctorJPushConfig() {
        Wrapper<JPushSet> wrapper = new EntityWrapper<>();
        wrapper.eq("type", Constant.JPushType.is_doctor.getValue()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return this.selectOne(wrapper);
    }


    @Override
    public JPushSet getMemberJPushConfig() {
        Wrapper<JPushSet> wrapper = new EntityWrapper<>();
        wrapper.eq("type", Constant.JPushType.is_member.getValue()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return this.selectOne(wrapper);
    }

    @Override
    public JPushSet getHouseKeeperConfig() {
        Wrapper<JPushSet> wrapper = new EntityWrapper<>();
        wrapper.eq("type", Constant.JPushType.is_housekeeper.getValue()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return this.selectOne(wrapper);
    }


    @Override
    public JPushSet getChildJPushConfig() {
        Wrapper<JPushSet> wrapper = new EntityWrapper<>();
        wrapper.eq("type", Constant.JPushType.is_child.getValue()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return this.selectOne(wrapper);
    }


    @Override
    public JPushSet getManagerJPushConfig() {
        Wrapper<JPushSet> wrapper = new EntityWrapper<>();
        wrapper.eq("type", Constant.JPushType.is_manager.getValue()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return this.selectOne(wrapper);
    }
}
