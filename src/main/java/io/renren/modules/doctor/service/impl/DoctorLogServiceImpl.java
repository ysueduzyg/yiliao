package io.renren.modules.doctor.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.common.utils.DateUtils;
import io.renren.modules.app.form.TaskServiceOrderForm;
import io.renren.modules.backend.form.BackendLogListForm;
import io.renren.modules.backend.form.DoctorLogListForm;
import io.renren.modules.doctor.dao.DoctorLogDao;
import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.doctor.entity.DoctorLogEntity;
import io.renren.modules.doctor.service.DoctorLogService;
import io.renren.modules.doctor.service.DoctorService;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.member.service.MemberService;
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
@Service("doctorLogService")
public class DoctorLogServiceImpl extends ServiceImpl<DoctorLogDao, DoctorLogEntity> implements DoctorLogService {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MemberService memberService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DoctorLogEntity> page = this.selectPage(
                new Query<DoctorLogEntity>(params).getPage(),
                new EntityWrapper<DoctorLogEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryDoctorPage(Map<String, Object> params, Integer doctorId) {
        Wrapper<DoctorLogEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).eq("doctor_id", doctorId).orderBy("gmt_create", false);
        ;
        Page<DoctorLogEntity> page = this.selectPage(
                new Query<DoctorLogEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryBackendDoctorPage(Map<String, Object> params, Integer doctorId) {
        Wrapper<DoctorLogEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).eq("doctor_id", doctorId).orderBy("gmt_create", false);
        Page<DoctorLogEntity> page = this.selectPage(
                new Query<DoctorLogEntity>(params).getPage(),
                wrapper
        );

        List<DoctorLogListForm> forms = new ArrayList<>();
        for (DoctorLogEntity logEntity : page.getRecords()) {
            DoctorLogListForm form = new DoctorLogListForm();
            form.setContent(logEntity.getContent());
            form.setDate(DateUtils.format(logEntity.getGmtCreate(),DateUtils.DATE_TIME_PATTERN));
            form.setLogId(logEntity.getId());
            MemberEntity memberEntity = memberService.selectById(logEntity.getMemberId());
            form.setMemberAvatar(memberEntity.getAvatar());
            form.setMemberId(logEntity.getMemberId());
            form.setMemberName(memberEntity.getRealName());
            form.setPhotos(logEntity.getPhotos());
            form.setTaskSn(logEntity.getTaskSn());
            form.setMemberSn(memberEntity.getSn());
            forms.add(form);
        }


        Page<DoctorLogListForm> formPage = new Page<>();
        formPage.setRecords(forms);
        formPage.setCurrent(page.getCurrent());
        formPage.setTotal(page.getTotal());
        formPage.setSize(page.getSize());
        return new PageUtils(formPage);
    }

    @Override
    public PageUtils queryBackendPage(Map<String, Object> params) {
        Wrapper<DoctorLogEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).orderBy("gmt_create", false);
        Page<DoctorLogEntity> page = this.selectPage(
                new Query<DoctorLogEntity>(params).getPage(),
                wrapper
        );

        List<BackendLogListForm> forms = new ArrayList<>();
        for (DoctorLogEntity logEntity : page.getRecords()) {
            BackendLogListForm form = new BackendLogListForm();
            form.setContent(logEntity.getContent());
            form.setDate(DateUtils.format(logEntity.getGmtCreate(),DateUtils.DATE_TIME_PATTERN));
            form.setLogId(logEntity.getId());
            MemberEntity memberEntity = memberService.selectById(logEntity.getMemberId());
            form.setMemberAvatar(memberEntity.getAvatar());
            form.setMemberId(logEntity.getMemberId());
            form.setMemberName(memberEntity.getRealName());
            form.setPhotos(logEntity.getPhotos());
            form.setTaskSn(logEntity.getTaskSn());
            form.setMemberSn(memberEntity.getSn());
            DoctorEntity doctorEntity = doctorService.selectById(logEntity.getDoctorId());
            form.setDoctorId(doctorEntity.getId());
            form.setDoctorAvatar(doctorEntity.getAvatar());
            form.setDoctorName(doctorEntity.getName());
            form.setDoctorType(doctorEntity.getEnterpriseId() > 0 && doctorEntity.getCommunityId() > 0 ? 3 : doctorEntity.getCommunityId() > 0 ? 1 : 2 );
            forms.add(form);
        }


        Page<BackendLogListForm> formPage = new Page<>();
        formPage.setRecords(forms);
        formPage.setCurrent(page.getCurrent());
        formPage.setTotal(page.getTotal());
        formPage.setSize(page.getSize());
        return new PageUtils(formPage);
    }

}
