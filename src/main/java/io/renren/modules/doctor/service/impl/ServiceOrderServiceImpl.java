package io.renren.modules.doctor.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.common.utils.DateUtils;
import io.renren.modules.app.form.MemberListForm;
import io.renren.modules.app.form.TaskServiceOrderForm;
import io.renren.modules.backend.form.WorkForm;
import io.renren.modules.doctor.dao.ServiceOrderDao;
import io.renren.modules.doctor.entity.ServiceOrderEntity;
import io.renren.modules.doctor.service.ServiceOrderService;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.member.entity.MemberTaskEntity;
import io.renren.modules.member.service.MemberService;
import io.renren.modules.member.service.MemberTaskService;
import org.aspectj.weaver.loadtime.Options;
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


@Service("serviceOrderService")
public class ServiceOrderServiceImpl extends ServiceImpl<ServiceOrderDao, ServiceOrderEntity> implements ServiceOrderService {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberTaskService taskService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<ServiceOrderEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        Page<ServiceOrderEntity> page = this.selectPage(
                new Query<ServiceOrderEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryByDoctorPage(Map<String, Object> params, Integer doctorId) {
        Wrapper<ServiceOrderEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).ge("status", Constant.ServiceOrderStatus.is_doctor_disposal.getValue()).orderBy("gmt_create", false);
        Page<ServiceOrderEntity> page = this.selectPage(
                new Query<ServiceOrderEntity>(params).getPage(),
                wrapper
        );
        List<WorkForm> workForms = new ArrayList<>();
        for (ServiceOrderEntity orderEntity : page.getRecords()) {
            WorkForm workForm = new WorkForm();
            workForm.setCreateTime(orderEntity.getGmtCreate().getTime());
            MemberEntity memberEntity = memberService.selectById(orderEntity.getMemberId());

            workForm.setMemberAvatar(memberEntity.getAvatar());
            workForm.setMemberId(memberEntity.getId());
            workForm.setMemberSn(memberEntity.getSn());
            workForm.setMemberName(memberEntity.getRealName());
            workForm.setMobile(memberEntity.getMobile());
            workForm.setTaskId(orderEntity.getTaskId());
            workForm.setTaskSn(orderEntity.getTaskSn());
            workForm.setStatus(orderEntity.getStatus());
            MemberTaskEntity taskEntity = taskService.selectById(orderEntity.getTaskId());
            workForm.setType(taskEntity.getType());
            workForms.add(workForm);

        }

        Page<WorkForm> formPage = new Page<>();
        formPage.setRecords(workForms);
        formPage.setCurrent(page.getCurrent());
        formPage.setTotal(page.getTotal());
        formPage.setSize(page.getSize());

        return new PageUtils(formPage);
    }


    @Override
    public PageUtils queryTaskPage(Map<String, Object> params,Integer doctorId) {
        Wrapper<ServiceOrderEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue())
                .eq("status", Constant.ServiceOrderStatus.is_doctor_disposal.getValue())
                .eq("receive_id",doctorId)
                .orderBy("gmt_create", false);
        Page<ServiceOrderEntity> page = this.selectPage(
                new Query<ServiceOrderEntity>(params).getPage(),
                wrapper
        );

        List<ServiceOrderEntity> list = page.getRecords();
        List<TaskServiceOrderForm> forms = new ArrayList<>();
        for (ServiceOrderEntity entity : list) {
            TaskServiceOrderForm form = new TaskServiceOrderForm();
            form.setDate(DateUtils.format(entity.getGmtCreate(),DateUtils.DATE_TIME_PATTERN));
            form.setDoctorId(entity.getReceiveId());
            form.setMemberId(entity.getMemberId());
            MemberEntity memberEntity = memberService.selectById(entity.getMemberId());
            form.setMemberName(memberEntity.getRealName());
            form.setMemberSn(memberEntity.getSn());
            form.setServiceOrderId(entity.getId());
            form.setTaskId(entity.getTaskId());
            form.setTaskSn(entity.getTaskSn());
            MemberTaskEntity taskEntity = taskService.selectById(entity.getTaskId());
            form.setType(taskEntity.getType());
            if (taskEntity.getType() == Constant.TaskType.is_sos.getValue()) {
                form.setTaskType("求助");
            } else {
                form.setTaskType("咨询");
            }
            forms.add(form);
        }

        Page<TaskServiceOrderForm> formPage = new Page<>();
        formPage.setRecords(forms);
        formPage.setCurrent(page.getCurrent());
        formPage.setTotal(page.getTotal());
        formPage.setSize(page.getSize());

        return new PageUtils(formPage);
    }


}
