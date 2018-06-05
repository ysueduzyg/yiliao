package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.member.service.MemberService;
import io.renren.modules.sys.dao.JPushDao;
import io.renren.modules.sys.dao.MsgContentDao;
import io.renren.modules.sys.entity.JPushSet;
import io.renren.modules.sys.entity.MsgContent;
import io.renren.modules.sys.service.JPushService;
import io.renren.modules.sys.service.MsgContentService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("msgContentService")
public class MsgContentServiceImpl extends ServiceImpl<MsgContentDao, MsgContent> implements MsgContentService {
    @Override
    public String getSosMsg() {
        Wrapper<MsgContent> wrapper = new EntityWrapper<>();
        wrapper.eq("type", Constant.TaskType.is_sos.getValue()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return this.selectOne(wrapper).getContent();
    }

    @Override
    public String getHousekeeperMsg() {
        Wrapper<MsgContent> wrapper = new EntityWrapper<>();
        wrapper.eq("type", Constant.TaskType.is_call_housekeeper.getValue()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return this.selectOne(wrapper).getContent();
    }

    @Override
    public String getMessageMsg() {
        Wrapper<MsgContent> wrapper = new EntityWrapper<>();
        wrapper.eq("type", Constant.TaskType.is_message.getValue()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return this.selectOne(wrapper).getContent();
    }

    @Override
    public String getReceiveMsg() {
        Wrapper<MsgContent> wrapper = new EntityWrapper<>();
        wrapper.eq("type", Constant.TaskType.is_doctor_received.getValue()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return this.selectOne(wrapper).getContent();
    }

    @Override
    public String getReceiveHouseKeeperMsg() {
        Wrapper<MsgContent> wrapper = new EntityWrapper<>();
        wrapper.eq("type", Constant.TaskType.is_housekeeper_received.getValue()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return this.selectOne(wrapper).getContent();
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MsgContent> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        Page<MsgContent> page = this.selectPage(
                new Query<MsgContent>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }
}
