package io.renren.modules.doctor.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.app.form.TaskServiceOrderForm;
import io.renren.modules.backend.form.DoctorBackendTempLogForm;
import io.renren.modules.doctor.dao.DoctorLogDao;
import io.renren.modules.doctor.dao.DoctorLogTempDao;
import io.renren.modules.doctor.entity.DoctorLogEntity;
import io.renren.modules.doctor.entity.DoctorLogTempEntity;
import io.renren.modules.doctor.service.DoctorLogService;
import io.renren.modules.doctor.service.DoctorLogTempService;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.member.entity.MemberTaskEntity;
import io.renren.modules.member.service.MemberService;
import io.renren.modules.member.service.MemberTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("doctorLogTempService")
public class DoctorLogTempServiceImpl extends ServiceImpl<DoctorLogTempDao, DoctorLogTempEntity> implements DoctorLogTempService {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberTaskService taskService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DoctorLogTempEntity> page = this.selectPage(
                new Query<DoctorLogTempEntity>(params).getPage(),
                new EntityWrapper<DoctorLogTempEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryDoctorPage(Map<String, Object> params, Integer doctorId) {
        Wrapper<DoctorLogTempEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue())
                .eq("doctor_id", doctorId).orderBy("gmt_create", false);
        Page<DoctorLogTempEntity> page = this.selectPage(
                new Query<DoctorLogTempEntity>(params).getPage(),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    public PageUtils queryDoctorBackendPage(Map<String, Object> params, Integer doctorId) {
        Wrapper<DoctorLogTempEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue())
                .eq("doctor_id", doctorId).orderBy("gmt_create", false);
        Page<DoctorLogTempEntity> page = this.selectPage(
                new Query<DoctorLogTempEntity>(params).getPage(),
                wrapper
        );

        List<DoctorBackendTempLogForm> forms = new ArrayList<>();

        for (DoctorLogTempEntity logTempEntity : page.getRecords()) {
            DoctorBackendTempLogForm form = new DoctorBackendTempLogForm();
            form.setCreateTime(logTempEntity.getGmtCreate().getTime());
            form.setLogId(logTempEntity.getId());
            MemberEntity memberEntity = memberService.selectById(logTempEntity.getMemberId());

            form.setMemberName(memberEntity.getRealName());
            form.setMemberSn(memberEntity.getSn());
            form.setTaskSn(logTempEntity.getTaskSn());
            MemberTaskEntity taskEntity = taskService.selectById(logTempEntity.getTaskId());
            form.setType(taskEntity.getType());
            form.setTaskType(Constant.TaskType.is_sos.getValue() == taskEntity.getType() ? "求助" : "咨询");
            forms.add(form);
        }

        Page<DoctorBackendTempLogForm> formPage = new Page<>();
        formPage.setRecords(forms);
        formPage.setCurrent(page.getCurrent());
        formPage.setTotal(page.getTotal());
        formPage.setSize(page.getSize());
        return new PageUtils(formPage);
    }

}
