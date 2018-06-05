package io.renren.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.common.utils.DateUtils;
import io.renren.modules.app.form.CommentListForm;
import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.doctor.service.DoctorService;
import io.renren.modules.member.dao.MemberCommentsDao;
import io.renren.modules.member.entity.MemberCommentsEntity;
import io.renren.modules.member.service.MemberCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


/**
 * @author xpf
 */
@Service("memberCommentsService")
public class MemberCommentsServiceImpl extends ServiceImpl<MemberCommentsDao, MemberCommentsEntity> implements MemberCommentsService {

    @Autowired
    private DoctorService doctorService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MemberCommentsEntity> page = this.selectPage(
                new Query<MemberCommentsEntity>(params).getPage(),
                new EntityWrapper<MemberCommentsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryDoctorPage(Map<String, Object> params) {
        Wrapper<MemberCommentsEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue())
                .eq("status", Constant.CommentStatus.is_commented.getValue())
                .eq("focus_type", Constant.CommentFocusType.is_doctor.getValue())
                .eq("focus_id", params.get("doctorId")).orderBy("gmt_create", false);
        Page<MemberCommentsEntity> page = this.selectPage(
                new Query<MemberCommentsEntity>(params).getPage(),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryMemberPage(Map<String, Object> params) {
        Wrapper<MemberCommentsEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue())
                .eq("member_id", params.get("memberId")).eq("type", 1)
                .orderBy("gmt_create", false);
        Page<MemberCommentsEntity> page = this.selectPage(
                new Query<MemberCommentsEntity>(params).getPage(),
                wrapper
        );

        List<MemberCommentsEntity> list = page.getRecords();
        List<CommentListForm> forms = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CommentListForm form = new CommentListForm();
            MemberCommentsEntity commentsEntity = list.get(i);
            form.setAttitude(commentsEntity.getAttitude());
            form.setContent(commentsEntity.getContent());
            form.setCreateTime(DateUtils.format(commentsEntity.getGmtCreate(), DateUtils.DATE_TIME_PATTERN));
            form.setFocusId(commentsEntity.getFocusId());
            form.setFocusType(commentsEntity.getFocusType());
            DoctorEntity doctorEntity = doctorService.selectById(commentsEntity.getFocusId());
            form.setFocusAvatar(doctorEntity.getAvatar());
            form.setFocusName(doctorEntity.getName());
            form.setEffect(commentsEntity.getEffect());
            form.setGetOrderTime(DateUtils.format(commentsEntity.getGmtCreate(), DateUtils.DATE_TIME_PATTERN));
            form.setTaskTime(DateUtils.format(new Date(commentsEntity.getTaskTime()), DateUtils.DATE_TIME_PATTERN));
            form.setMemberId(commentsEntity.getMemberId());
            form.setMemberSn(commentsEntity.getMemberSn());
            form.setTaskSn(commentsEntity.getTaskSn());
            form.setSpeed(commentsEntity.getSpeed());
            form.setType(commentsEntity.getType());
            form.setTaskType(commentsEntity.getTaskType());
            form.setStatus(commentsEntity.getStatus());
            form.setTaskId(commentsEntity.getTaskId());
            form.setId(commentsEntity.getId());
            forms.add(form);
        }

        Page<CommentListForm> formPage = new Page<>();
        formPage.setRecords(forms);
        formPage.setCurrent(page.getCurrent());
        formPage.setTotal(page.getTotal());
        formPage.setSize(page.getSize());
        return new PageUtils(formPage);
    }

    @Override
    public PageUtils queryHouseKeeperPage(Map<String, Object> params) {
        Wrapper<MemberCommentsEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue())
                .eq("status", Constant.CommentStatus.is_commented.getValue())
                .eq("focus_type", Constant.CommentFocusType.is_houseKeeper.getValue())
                .eq("focus_id", params.get("houseKeeperId")).orderBy("gmt_create", false);
        Page<MemberCommentsEntity> page = this.selectPage(
                new Query<MemberCommentsEntity>(params).getPage(),
                wrapper
        );
        return new PageUtils(page);
    }


}
