package io.renren.modules.doctor.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.app.config.JPushUtil;
import io.renren.modules.doctor.dao.ContentHtmlDao;
import io.renren.modules.doctor.entity.ContentHtmlEntity;
import io.renren.modules.doctor.entity.HouseKeeperEntity;
import io.renren.modules.doctor.service.ContentHtmlService;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.member.entity.MemberTagEntity;
import io.renren.modules.member.service.MemberService;
import io.renren.modules.member.service.MemberTagService;
import io.renren.modules.sys.entity.JPushSet;
import io.renren.modules.sys.service.JPushService;
import io.renren.modules.user.entity.UserEntity;
import io.renren.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("contentHtmlService")
public class ContentHtmlServiceImpl extends ServiceImpl<ContentHtmlDao, ContentHtmlEntity> implements ContentHtmlService {

    @Autowired
    JPushService jPushService;
    @Autowired
    MemberTagService tagService;
    @Autowired
    MemberService memberService;
    @Autowired
    UserService userService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<ContentHtmlEntity> wrapper= new EntityWrapper<>();
        wrapper.eq("is_deleted",Constant.DeleteType.un_deleted.getValue());
        Page<ContentHtmlEntity> page = this.selectPage(
                new Query<ContentHtmlEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPageByMember(List<Long> ids, Map<String, Object> params) {
        Wrapper<ContentHtmlEntity> wrapper= new EntityWrapper<>();
        wrapper.eq("is_deleted",Constant.DeleteType.un_deleted.getValue()).in("id",ids)
                .orderBy("publish_date",false).setSqlSelect("id,gmt_create as gmtCreate,publish_date as publishDate,title as title,short_content as shortContent,img as img");
        Page<ContentHtmlEntity> page = this.selectPage(
                new Query<ContentHtmlEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertAndPublish(ContentHtmlEntity entity) {
        this.insert(entity);
        this.pushMessage(entity);
    }

    private void pushMessage(ContentHtmlEntity entity) {
        jPushService.getMemberJPushConfig();
        String tags = entity.getTags();
        List<Integer> list = JSONObject.parseArray(tags, Integer.class);
        if (list.size() == 0) {
            throw new RRException("标签不能我空");
        }
        JPushSet config = jPushService.getMemberJPushConfig();
        Map<String, String> doctorMap = new HashMap<>();
        doctorMap.put("type", String.valueOf(Constant.msgRemind.is_weak.getValue()));
        if (list.get(0) == 0) {
            JPushUtil.pushAll("收到一条推送", config.getSecret(), config.getAppKey(), "json", JSON.toJSONString(doctorMap));
        }

        Wrapper<MemberTagEntity> wrapper = new EntityWrapper<>();
        wrapper.in("tag_id", list).eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).setSqlSelect(" DISTINCT member_id as memberId ");
        List<MemberTagEntity> memberTagEntities = tagService.selectList(wrapper);
        List<Integer> memberIds = memberTagEntities.stream()
                .map(MemberTagEntity::getMemberId)
                .collect(Collectors.toList());
        if (memberIds.size() == 0) {
            return;
        }
        Wrapper<MemberEntity> memberEntityWrapper = new EntityWrapper<>();
        memberEntityWrapper.in("id", memberIds).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        List<MemberEntity> memberEntities = memberService.selectList(memberEntityWrapper);
        List<Integer> uIds = memberEntities.stream()
                .map(MemberEntity::getUserId)
                .collect(Collectors.toList());

        Wrapper<UserEntity> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.in("user_id", uIds).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        List<UserEntity> userEntities = userService.selectList(userEntityWrapper);

        List<String> devices = userEntities.stream()
                .map(UserEntity::getDeviceId)
                .collect(Collectors.toList());

        JPushUtil.push(devices, "收到一条推送", config.getSecret(), config.getAppKey(), "json", JSON.toJSONString(doctorMap));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAndPublish(ContentHtmlEntity entity) {
        entity.setStatus(Constant.ContentHtmlStatus.is_publish.getValue());
        this.updateById(entity);
        this.pushMessage(entity);
    }

    @Override
    public void publish(ContentHtmlEntity entity) {
        entity.setStatus(Constant.ContentHtmlStatus.is_publish.getValue());
        this.updateById(entity);
        this.pushMessage(entity);
    }

}