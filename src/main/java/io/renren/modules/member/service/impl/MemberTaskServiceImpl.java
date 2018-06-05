package io.renren.modules.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.app.config.JPushUtil;
import io.renren.modules.app.utils.CronDateUtils;
import io.renren.modules.backend.form.DoctorTaskForm;
import io.renren.modules.doctor.entity.*;
import io.renren.modules.doctor.service.*;
import io.renren.modules.job.entity.ScheduleJobEntity;
import io.renren.modules.job.service.ScheduleJobService;
import io.renren.modules.member.dao.MemberTaskDao;
import io.renren.modules.member.entity.*;
import io.renren.modules.member.service.*;
import io.renren.modules.sys.dao.JPushDao;
import io.renren.modules.sys.entity.JPushSet;
import io.renren.modules.sys.service.JPushService;
import io.renren.modules.sys.service.MsgContentService;
import io.renren.modules.user.entity.UserEntity;
import io.renren.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.transaction.annotation.Transactional;


@Service("memberTaskService")
public class MemberTaskServiceImpl extends ServiceImpl<MemberTaskDao, MemberTaskEntity> implements MemberTaskService {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberChildService childService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private MemberTaskListService taskListService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ServiceOrderRecordService recordService;
    @Autowired
    private JPushService jPushService;
    @Autowired
    private MsgContentService msgContentService;
    @Autowired
    private ScheduleJobService jobLogService;
    @Autowired
    private MemberLevelService levelService;
    @Autowired
    private HouseKeeperService houseKeeperService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<MemberTaskEntity> taskEntityWrapper = new EntityWrapper<>();
        taskEntityWrapper.eq("type",params.get("type")).orderBy("time",false);
        Page<MemberTaskEntity> page = this.selectPage(
                new Query<MemberTaskEntity>(params).getPage(),
                taskEntityWrapper
        );
        List<DoctorTaskForm> doctorTaskForms = this.taskToForm(page.getRecords());
        Page<DoctorTaskForm> taskFormPage = new Page<>();
        taskFormPage.setRecords(doctorTaskForms);
        taskFormPage.setCurrent(page.getCurrent());
        taskFormPage.setTotal(page.getTotal());
        taskFormPage.setSize(page.getSize());
        return new PageUtils(taskFormPage);
    }

    @Override
    public PageUtils queryPageSosByDoctor(Map<String, Object> params, Integer doctorId) {
        Wrapper<MemberTaskEntity> taskEntityWrapper = new EntityWrapper<>();
        taskEntityWrapper.eq("customer_main_id", doctorId).eq("type", Constant.TaskType.is_sos.getValue()).orderBy("time",false);
        Page<MemberTaskEntity> page = this.selectPage(
                new Query<MemberTaskEntity>(params).getPage(),
                taskEntityWrapper
        );
        List<DoctorTaskForm> doctorTaskForms = this.taskToForm(page.getRecords());
        Page<DoctorTaskForm> taskFormPage = new Page<>();
        taskFormPage.setRecords(doctorTaskForms);
        taskFormPage.setCurrent(page.getCurrent());
        taskFormPage.setTotal(page.getTotal());
        taskFormPage.setSize(page.getSize());
        return new PageUtils(taskFormPage);
    }

    @Override
    public PageUtils queryPageMsgByDoctor(Map<String, Object> params, Integer doctorId) {
        Wrapper<MemberTaskEntity> taskEntityWrapper = new EntityWrapper<>();
        taskEntityWrapper.eq("customer_main_id", doctorId).eq("type", Constant.TaskType.is_message.getValue()).orderBy("time", false);
        Page<MemberTaskEntity> page = this.selectPage(
                new Query<MemberTaskEntity>(params).getPage(),
                taskEntityWrapper
        );
        List<DoctorTaskForm> doctorTaskForms = this.taskToForm(page.getRecords());
        Page<DoctorTaskForm> taskFormPage = new Page<>();
        taskFormPage.setRecords(doctorTaskForms);
        taskFormPage.setCurrent(page.getCurrent());
        taskFormPage.setTotal(page.getTotal());
        taskFormPage.setSize(page.getSize());
        return new PageUtils(taskFormPage);
    }

    @Override
    public PageUtils queryPageMsgByHouseKeeper(Map<String, Object> params, Integer houseKeeperId) {
        Wrapper<MemberTaskEntity> taskEntityWrapper = new EntityWrapper<>();
        taskEntityWrapper.eq("customer_main_id", houseKeeperId).eq("type", Constant.TaskType.is_call_housekeeper.getValue()).orderBy("time", false);
        Page<MemberTaskEntity> page = this.selectPage(
                new Query<MemberTaskEntity>(params).getPage(),
                taskEntityWrapper
        );
        List<DoctorTaskForm> doctorTaskForms = this.taskToForm(page.getRecords());
        Page<DoctorTaskForm> taskFormPage = new Page<>();
        taskFormPage.setRecords(doctorTaskForms);
        taskFormPage.setCurrent(page.getCurrent());
        taskFormPage.setTotal(page.getTotal());
        taskFormPage.setSize(page.getSize());
        return new PageUtils(taskFormPage);
    }

    private List<DoctorTaskForm> taskToForm(List<MemberTaskEntity> list) {
        List<DoctorTaskForm> doctorTaskForms = new ArrayList<>();
        for (MemberTaskEntity taskEntity : list) {
            DoctorTaskForm form = new DoctorTaskForm();
            form.setCreateTime(taskEntity.getTime());
            form.setDoctorId(taskEntity.getCustomerMainId());
            form.setMemberId(taskEntity.getPublisherId());
            form.setTaskId(taskEntity.getId());
            MemberEntity memberEntity = memberService.selectById(taskEntity.getPublisherId());
            form.setMemberName(memberEntity.getRealName());
            form.setMemberAvatar(memberEntity.getAvatar());
            form.setAddress(memberEntity.getAddress());
            form.setMemberMobile(memberEntity.getMobile());
            form.setStatus(taskEntity.getStatus());
            form.setType(taskEntity.getType());
            form.setTaskType(taskEntity.getTaskType());
            form.setTaskSn(taskEntity.getTaskSn());
            MemberLevelEntity levelEntity = levelService.selectById(memberEntity.getLevel());
            form.setLevel(levelEntity.getName());
            doctorTaskForms.add(form);
        }
        return doctorTaskForms;
    }

    @Override
    @Transactional
    public void saveSos(MemberTaskEntity taskEntity, String optionName) {
        this.insert(taskEntity);
        Wrapper<MemberChildEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", taskEntity.getPublisherId());
        List<MemberChildEntity> list = childService.selectList(wrapper);
        for (MemberChildEntity childEntity : list) {
            MemberTaskList taskList = new MemberTaskList();
            taskList.setTaskId(taskEntity.getId());
            taskList.setUserId(childEntity.getUserId());
            taskList.setStatus(Constant.TaskListStatus.is_not_get_order.getValue());
            taskList.setType(Constant.TaskListType.is_child.getValue());
            taskListService.insert(taskList);
        }
        DoctorEntity doctorEntity = doctorService.selectById(taskEntity.getCustomerMainId());
        Integer mangerId = 0;
        if (doctorEntity.getType() == Constant.DoctorType.is_community_doctor.getValue()) {
            CommunityEntity communityEntity = communityService.selectById(doctorEntity.getCommunityId());
            mangerId = communityEntity.getCommunityManagerId();
        } else {
            EnterpriseEntity enterpriseEntity = enterpriseService.selectById(doctorEntity.getEnterpriseId());
            mangerId = enterpriseEntity.getEnterpriseManagerId();
        }
        MemberTaskList manager = new MemberTaskList();
        manager.setTaskId(taskEntity.getId());
        manager.setUserId(mangerId);
        manager.setStatus(Constant.TaskListStatus.is_not_get_order.getValue());
        manager.setType(Constant.TaskListType.is_manager.getValue());
        taskListService.insert(manager);


        ServiceOrderRecordEntity recordEntity = new ServiceOrderRecordEntity();
        recordEntity.setReceiveId(taskEntity.getCustomerMainId());
        recordEntity.setMemberId(taskEntity.getPublisherId());
        recordEntity.setMemberSn(taskEntity.getMemberSn());
        recordEntity.setTaskSn(taskEntity.getTaskSn());
        recordEntity.setRemark(Constant.ServiceOrderRemark.is_not_pick_up_order.getValue());
        recordEntity.setTaskId(taskEntity.getId());
        recordEntity.setType(Constant.TaskType.is_sos.getValue());
        recordEntity.setStatus(Constant.ServiceOrderStatus.is_not_pick_up_order.getValue());
        recordEntity.setOptionName(optionName);
        recordService.insert(recordEntity);
//todo
        //添加推送 添加任务

        //添加主推送
        UserEntity userEntity = userService.selectById(doctorEntity.getUserId());
        String alias = userEntity.getDeviceId();
        String alert = msgContentService.getSosMsg();
        JPushSet config = jPushService.getDoctorJPushConfig();
        Map<String, String> doctorMap = new HashMap<>();
        doctorMap.put("id", String.valueOf(taskEntity.getPublisherId()));
        doctorMap.put("type", String.valueOf(Constant.msgRemind.is_strong.getValue()));
        doctorMap.put("taskId", String.valueOf(taskEntity.getId()));
        doctorMap.put("sn", taskEntity.getTaskSn());
        JPushUtil.push(alias, alert, config.getSecret(), config.getAppKey(), "json", JSON.toJSONString(doctorMap));


//        UserEntity managerUser = userService.selectById(mangerId);
//        String managerAlias = managerUser.getDeviceId();
//        JPushSet managerConfig = jPushService.getManagerJPushConfig();
//        Map<String, String> managerMap = new HashMap<>();
//        managerMap.put("id", String.valueOf(taskEntity.getPublisherId()));
//        managerMap.put("type", String.valueOf(Constant.msgRemind.is_weak.getValue()));
//        managerMap.put("doctorId", String.valueOf(doctorEntity.getId()));
//        //给医生上级发送
//        JPushUtil.push(managerAlias, alert, managerConfig.getAppKey(), managerConfig.getSecret(), "json", JSON.toJSONString(managerMap));
//
//
//        ScheduleJobEntity jobEntity = new ScheduleJobEntity();
//        jobEntity.setCreateTime(new Date());
//        jobEntity.setStatus(0);
//        jobEntity.setBeanName("appTask");
//        jobEntity.setMethodName("jPushManager");
//        Long nextTime = System.currentTimeMillis() + 1 * 60 * 1000;
//        String cron = CronDateUtils.getCron(new Date(nextTime));
//        jobEntity.setCronExpression(cron);
//        jobEntity.setRemark("只执行一次");
//        Map<String, String> obj = new HashMap<>();
//        obj.put("alias", managerAlias);
//        obj.put("alert", alert);
//        obj.put("memberId", String.valueOf(taskEntity.getPublisherId()));
//        obj.put("doctorId", String.valueOf(doctorEntity.getId()));
//        obj.put("appKey", managerConfig.getAppKey());
//        obj.put("secret", managerConfig.getSecret());
//        obj.put("type", String.valueOf(Constant.msgRemind.is_weak.getValue()));
//        jobEntity.setParams(JSON.toJSONString(obj));
//        jobLogService.save(jobEntity);


    }

    @Override
    public void saveMsg(MemberTaskEntity taskEntity, String optionName) {
        this.insert(taskEntity);
        //添加推送
        //添加主推送
        DoctorEntity doctorEntity = doctorService.selectById(taskEntity.getCustomerMainId());
        UserEntity userEntity = userService.selectById(doctorEntity.getUserId());
        String alias = userEntity.getDeviceId();
        String alert = msgContentService.getSosMsg();
        JPushSet config = jPushService.getDoctorJPushConfig();
        Map<String, String> doctorMap = new HashMap<>();
        doctorMap.put("id", String.valueOf(taskEntity.getPublisherId()));
        doctorMap.put("type", String.valueOf(Constant.msgRemind.is_weak.getValue()));
        doctorMap.put("taskId", String.valueOf(taskEntity.getId()));
        doctorMap.put("sn", taskEntity.getTaskSn());
        JPushUtil.push(alias, alert, config.getSecret(), config.getAppKey(), "json", JSON.toJSONString(doctorMap));


        ServiceOrderRecordEntity recordEntity = new ServiceOrderRecordEntity();
        recordEntity.setReceiveId(taskEntity.getCustomerMainId());
        recordEntity.setMemberId(taskEntity.getPublisherId());
        recordEntity.setMemberSn(taskEntity.getMemberSn());
        recordEntity.setTaskSn(taskEntity.getTaskSn());
        recordEntity.setRemark(Constant.ServiceOrderRemark.is_not_pick_up_order.getValue());
        recordEntity.setTaskId(taskEntity.getId());
        recordEntity.setType(Constant.TaskType.is_message.getValue());
        recordEntity.setStatus(Constant.ServiceOrderStatus.is_not_pick_up_order.getValue());
        recordEntity.setOptionName(optionName);
        recordService.insert(recordEntity);
    }

    @Override
    public void saveHouseKeeper(MemberTaskEntity taskEntity, String optionName) {
        this.insert(taskEntity);
        //添加推送
        //添加主推送
        HouseKeeperEntity  houseKeeperEntity = houseKeeperService.selectById(taskEntity.getCustomerMainId());
        UserEntity userEntity = userService.selectById(houseKeeperEntity.getUserId());
        String alias = userEntity.getDeviceId();
        String alert = msgContentService.getHousekeeperMsg();
        JPushSet config = jPushService.getHouseKeeperConfig();
        Map<String, String> doctorMap = new HashMap<>();
        doctorMap.put("id", String.valueOf(taskEntity.getPublisherId()));
        doctorMap.put("type", String.valueOf(Constant.msgRemind.is_strong.getValue()));
        doctorMap.put("taskId", String.valueOf(taskEntity.getId()));
        doctorMap.put("sn", taskEntity.getTaskSn());
        JPushUtil.push(alias, alert, config.getSecret(), config.getAppKey(), "json", JSON.toJSONString(doctorMap));


        ServiceOrderRecordEntity recordEntity = new ServiceOrderRecordEntity();
        recordEntity.setReceiveId(taskEntity.getCustomerMainId());
        recordEntity.setMemberId(taskEntity.getPublisherId());
        recordEntity.setMemberSn(taskEntity.getMemberSn());
        recordEntity.setTaskSn(taskEntity.getTaskSn());
        recordEntity.setRemark(Constant.ServiceOrderRemark.is_not_pick_up_order.getValue());
        recordEntity.setTaskId(taskEntity.getId());
        recordEntity.setType(Constant.TaskType.is_message.getValue());
        recordEntity.setStatus(Constant.ServiceOrderStatus.is_not_pick_up_order.getValue());
        recordEntity.setOptionName(optionName);
        recordService.insert(recordEntity);
    }
}
