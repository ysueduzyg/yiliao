package io.renren.modules.app.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.annotation.UserLog;
import io.renren.common.utils.Constant;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.app.annotation.DoctorLogin;
import io.renren.modules.app.annotation.DoctorLoginUser;
import io.renren.modules.app.annotation.MemberLogin;
import io.renren.modules.app.annotation.MemberLoginUser;
import io.renren.modules.app.form.*;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.backend.service.DoctorBusinessService;
import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.doctor.entity.DoctorLogEntity;
import io.renren.modules.doctor.entity.DoctorLogTempEntity;
import io.renren.modules.doctor.entity.ServiceOrderEntity;
import io.renren.modules.doctor.service.DoctorLogService;
import io.renren.modules.doctor.service.DoctorLogTempService;
import io.renren.modules.doctor.service.DoctorService;
import io.renren.modules.doctor.service.ServiceOrderService;
import io.renren.modules.member.entity.*;
import io.renren.modules.member.service.*;
import io.renren.modules.sys.service.MsgContentService;
import io.renren.modules.user.entity.UserEntity;
import io.renren.modules.user.service.UserService;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.renren.common.utils.DateUtils.DATE_TIME_PATTERN;

@RestController
@RequestMapping("/app/d")
@Api("APP医生接口")
public class AppDoctorApiController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private MemberService memberService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MemberLevelService levelService;
    @Autowired
    private MemberTaskService taskService;
    @Autowired
    private MemberCommentsService commentsService;
    @Autowired
    private ServiceOrderService serviceOrderService;
    @Autowired
    private DoctorBusinessService doctorBusinessService;
    @Autowired
    private MsgContentService msgContentService;
    @Autowired
    private MemberLevelService memberLevelService;
    @Autowired
    private MemberCurrentService currentService;
    @Autowired
    private MemberDrugService drugService;
    @Autowired
    private MemberCheckService checkService;
    @Autowired
    private MemberGenenticDiseaseService genenticDiseaseService;
    @Autowired
    private MemberAllergyService allergyService;
    @Autowired
    private MemberHistoryDiseaseService diseaseService;
    @Autowired
    private MemberLifeService lifeService;
    @Autowired
    private DoctorLogService logService;
    @Autowired
    private DoctorLogTempService logTempService;

    @PostMapping("login")
    public R doctorLogin(@RequestBody LoginForm form) {
        ValidatorUtils.validateEntity(form);
        DoctorEntity doctorEntity = userService.loginByDoctor(form);
        //生成token
        String token = jwtUtils.generateToken(doctorEntity.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());
        map.put("user", doctorEntity);
        return R.ok().put("result", map);
    }


    @PostMapping("isLogin")
    @DoctorLogin
    public R memberLogin(@DoctorLoginUser DoctorEntity doctorEntity) {
        return R.ok();
    }


    @PostMapping("member-list")
    @DoctorLogin
    public R memberList(@DoctorLoginUser DoctorEntity doctorEntity, @RequestBody Map<String, Object> params) {
        params.put("doctorId", String.valueOf(doctorEntity.getId()));
        PageUtils page = memberService.queryPage(params);
        return R.ok().put("page", page);
    }


    @PostMapping("info")
    @DoctorLogin
    @UserLog("获取用户信息")
    public R info(@DoctorLoginUser DoctorEntity doctorEntity) {
        DoctorAppInfoFrom form = new DoctorAppInfoFrom();
        Wrapper<MemberTaskEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("customer_main_id", doctorEntity.getId())
                .eq("type", Constant.TaskType.is_sos.getValue())
                .eq("status", Constant.TaskStatus.is_not_receive.getValue());

        Integer sosNum = taskService.selectCount(wrapper);

        Wrapper<MemberTaskEntity> msgWrapper = new EntityWrapper<>();
        msgWrapper.eq("customer_main_id", doctorEntity.getId())
                .eq("type", Constant.TaskType.is_message.getValue())
                .eq("status", Constant.TaskStatus.is_not_receive.getValue());

        Integer msgNum = taskService.selectCount(msgWrapper);


        Wrapper<ServiceOrderEntity> serviceOrderEntityWrapper = new EntityWrapper<>();
        serviceOrderEntityWrapper.eq("status", Constant.ServiceOrderStatus.is_doctor_disposal.getValue())
                .eq("is_deleted", Constant.DeleteType.un_deleted.getValue())
                .eq("receive_id", doctorEntity.getId());
        Integer logNum = serviceOrderService.selectCount(serviceOrderEntityWrapper);

        form.setLogNum(logNum);
        form.setMsgNum(msgNum);
        form.setSosNum(sosNum);
        Map<String, Object> params = new HashMap<>();
        params.put("doctorId", String.valueOf(doctorEntity.getId()));
        params.put("pageIndex", 1);
        params.put("pageSize", 4);
        PageUtils page = memberService.queryPage(params);

        List list = page.getList();
        form.setMembers(list);
        return R.ok().put("info", form);
    }

    @PostMapping("user-info")
    @DoctorLogin
    @UserLog("获取医生详情")
    public R userInfo(@DoctorLoginUser DoctorEntity doctorEntity) {
        return R.ok().put("info", doctorEntity);
    }

    @PostMapping("user-info-update")
    @DoctorLogin
    @UserLog("获取医生详情")
    public R updateDoctorInfo(@DoctorLoginUser DoctorEntity doctorEntity, @RequestBody DoctorEntity updateDoctorEntity) {
        doctorService.updateById(updateDoctorEntity);
        return R.ok();
    }


    @PostMapping("alias/{alias}")
    @DoctorLogin
    @UserLog("提交别名")
    public R setDoctorAlias(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable String alias) {
        Integer userId = doctorEntity.getUserId();
        UserEntity userEntity = userService.selectById(userId);
        userEntity.setDeviceId(alias);
        userService.updateById(userEntity);
        return R.ok();
    }

    @PostMapping("receiveSos/{taskId}")
    @DoctorLogin
    @UserLog("接收求助任务")
    public R receiveSos(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer taskId) {
        MemberTaskEntity taskEntity = taskService.selectById(taskId);
        if (taskEntity == null) {
            return R.error(404, "呼叫不存在");
        }
        doctorBusinessService.receiveSos(doctorEntity, taskEntity);
        return R.ok();
    }

    @PostMapping("receiveMsg/{taskId}")
    @DoctorLogin
    @UserLog("接收求助任务")
    public R receiveMsg(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer taskId) {
        MemberTaskEntity taskEntity = taskService.selectById(taskId);
        if (taskEntity == null) {
            return R.error(404, "呼叫不存在");
        }
        doctorBusinessService.receiveMsg(doctorEntity, taskEntity);
        return R.ok();
    }




    @PostMapping("taskInfo/{taskId}")
    @DoctorLogin
    @UserLog("任务详情")
    public R taskInfo(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer taskId) {
        MemberTaskEntity taskEntity = taskService.selectById(taskId);
        if (taskEntity == null) {
            return R.error(404, "呼叫不存在");
        }
        TaskInfoForm taskInfoForm = new TaskInfoForm();
        MemberEntity memberEntity = memberService.selectById(taskEntity.getPublisherId());
        taskInfoForm.setMemberAvatar(memberEntity.getAvatar());
        taskInfoForm.setDate(DateUtils.format(new Date(taskEntity.getTime()), DATE_TIME_PATTERN));
        taskInfoForm.setMemberId(memberEntity.getId());
        taskInfoForm.setMemberMobile(memberEntity.getMobile());
        taskInfoForm.setMemberName(memberEntity.getRealName());
        taskInfoForm.setMemberSn(memberEntity.getSn());
        taskInfoForm.setTaskSn(taskEntity.getTaskSn());
        if (taskEntity.getType() == Constant.TaskType.is_sos.getValue()) {
            taskInfoForm.setMsg(msgContentService.getSosMsg());
        } else {
            taskInfoForm.setMsg(msgContentService.getMessageMsg());
        }
        MemberLevelEntity memberLevelEntity = memberLevelService.selectById(memberEntity.getLevel());
        taskInfoForm.setType(taskEntity.getType());
        taskInfoForm.setLevel(memberLevelEntity.getName());
        return R.ok().put("info", taskInfoForm);
    }


    @PostMapping("sosList")
    @DoctorLogin
    @UserLog("获取紧急求助列表")
    public R sosList(@DoctorLoginUser DoctorEntity doctorEntity,
                     @RequestBody Map<String, Object> params) {
        PageUtils page = taskService.queryPageSosByDoctor(params, doctorEntity.getId());
        return R.ok().put("page", page);
    }


    @PostMapping("msgList")
    @DoctorLogin
    @UserLog("获取咨询列表")
    public R msgList(@DoctorLoginUser DoctorEntity doctorEntity,
                     @RequestBody Map<String, Object> params) {
        PageUtils page = taskService.queryPageMsgByDoctor(params, doctorEntity.getId());
        return R.ok().put("page", page);
    }

    @PostMapping("sos/finish/{id}")
    @DoctorLogin
    @UserLog("医生标注处理SOS完成")
    public R sosFinish(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer id) {
        MemberTaskEntity taskEntity = taskService.selectById(id);
        if (taskEntity == null) {
            return R.error(404, "任务不存在");
        }
        doctorBusinessService.finishSos(doctorEntity, taskEntity);
        return R.ok();
    }

    @PostMapping("msg/finish/{id}")
    @DoctorLogin
    @UserLog("医生标注处理咨询完成")
    public R msgFinish(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer id) {
        MemberTaskEntity taskEntity = taskService.selectById(id);
        if (taskEntity == null) {
            return R.error(404, "任务不存在");
        }
        doctorBusinessService.finishMsg(doctorEntity, taskEntity);
        return R.ok();
    }

    @PostMapping("member/{id}")
    @DoctorLogin
    @UserLog("查看会员详情")
    public R memberInfo(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer id) {
        MemberEntity memberEntity = memberService.selectById(id);
        Wrapper<MemberCurrentEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", memberEntity.getId());
        MemberCurrentEntity currentEntity = currentService.selectOne(wrapper);
        MemberLevelEntity levelEntity = memberLevelService.selectById(memberEntity.getLevel());
        memberEntity.setLevel(levelEntity.getName());
        return R.ok().put("info", memberEntity).put("case", currentEntity);
    }


    @PostMapping("/child-list")
    @DoctorLogin
    @UserLog("获取会员的紧急联系人")
    public R childList(@RequestBody Map<String, Object> params) {
        PageUtils page = memberService.queryChildPage(params);
        return R.ok().put("page", page);
    }


    @PostMapping("/drug-list")
    @DoctorLogin
    @UserLog("获取会员用药记录")
    public R drugList(@RequestBody Map<String, Object> params) {
        PageUtils page = drugService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 检查列表
     */
    @PostMapping("/check-list")
    @DoctorLogin
    @UserLog("获取会员检查列表")
    public R checkList(@RequestBody Map<String, Object> params) {
        PageUtils page = checkService.queryPage(params);
        return R.ok().put("page", page);
    }


    //遗传病史
    @PostMapping("/genentic-list")
    @DoctorLogin
    @UserLog("获取会员遗传病史")
    public R genenticSymptom(@RequestBody Map<String, Object> params) {
        PageUtils page = genenticDiseaseService.queryPage(params);
        return R.ok().put("page", page);
    }


    @PostMapping("/allergy-list")
    @DoctorLogin
    @UserLog("获取会员过敏症状")
    public R allergySymptom(@RequestBody Map<String, Object> params) {
        PageUtils page = allergyService.queryPage(params);
        return R.ok().put("page", page);
    }

    //
    @PostMapping("/disease-list")
    @DoctorLogin
    @UserLog("获取会员病例")
    public R historySymptom(@RequestBody Map<String, Object> params) {
        PageUtils page = diseaseService.queryPage(params);
        return R.ok().put("page", page);
    }


    @PostMapping("/like-info/{id}")
    @DoctorLogin
    @UserLog("获取会员like")
    public R likeInfo(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer id) {
        Wrapper<MemberLifeEntity> lifeEntityWrapper = new EntityWrapper<>();
        lifeEntityWrapper.eq("member_id", id);
        MemberLifeEntity lifeEntity = lifeService.selectOne(lifeEntityWrapper);
        return R.ok().put("info", lifeEntity);
    }


    @PostMapping("like-info-update")
    @DoctorLogin
    @UserLog("更新会员信息")
    public R updateLike(@DoctorLoginUser DoctorEntity doctorEntity, MemberLifeEntity lifeEntity) {
        lifeService.updateById(lifeEntity);
        return R.ok();
    }


    @PostMapping("/sos-task-list")
    @DoctorLogin
    @UserLog("会员未提交日志的订单")
    public R logSosTask(@DoctorLoginUser DoctorEntity doctorEntity, @RequestBody Map<String, Object> params) {
        PageUtils page = serviceOrderService.queryTaskPage(params, doctorEntity.getId());
        return R.ok().put("page", page);
    }


    @PostMapping("/temp-log-list")
    @DoctorLogin
    @UserLog("获取草稿箱列表")
    public R tempLogList(@DoctorLoginUser DoctorEntity doctorEntity, @RequestBody Map<String, Object> params) {
        PageUtils page = logTempService.queryDoctorPage(params, doctorEntity.getId());
        return R.ok().put("page", page);
    }

    @PostMapping("/log-list")
    @DoctorLogin
    @UserLog("获取日志列表")
    public R logList(@DoctorLoginUser DoctorEntity doctorEntity, @RequestBody Map<String, Object> params) {
        PageUtils page = logService.queryDoctorPage(params, doctorEntity.getId());
        return R.ok().put("page", page);
    }


    @PostMapping("/add-log")
    @DoctorLogin
    @UserLog("添加医生日志")
    public R addLog(@DoctorLoginUser DoctorEntity doctorEntity, @RequestBody DoctorLogEntity logEntity) {
        if(StringUtils.isBlank(logEntity.getTaskSn())){
            return R.error(410,"请选择任务订单");
        }
        if(StringUtils.isBlank(logEntity.getContent())){
            return R.error(410,"请填写日志内容");
        }
        logEntity.setDoctorId(doctorEntity.getId());
        doctorBusinessService.insertLog(logEntity, doctorEntity);
        return R.ok();
    }

    @PostMapping("/add-temp-log")
    @DoctorLogin
    @UserLog("添加医生日志模版")
    public R addTempLog(@DoctorLoginUser DoctorEntity doctorEntity, @RequestBody DoctorLogTempEntity logEntity) {
        if(StringUtils.isBlank(logEntity.getTaskSn())){
            return R.error(410,"请选择任务订单");
        }
        if(StringUtils.isBlank(logEntity.getContent())){
            return R.error(410,"请填写日志内容");
        }
        logEntity.setDoctorId(doctorEntity.getId());
        logTempService.insert(logEntity);
        return R.ok();
    }
    @PostMapping("/update-temp-log")
    @DoctorLogin
    @UserLog("添加医生日志模版")
    public R updateTempLog(@DoctorLoginUser DoctorEntity doctorEntity, @RequestBody DoctorLogTempEntity logEntity) {
        logEntity.setDoctorId(doctorEntity.getId());

        logTempService.updateById(logEntity);
        return R.ok();
    }

    @PostMapping("/submit-temp-log")
    @DoctorLogin
    @UserLog("添加医生日志模版")
    public R submitTempLog(@DoctorLoginUser DoctorEntity doctorEntity, @RequestBody DoctorLogTempEntity logEntity) {
        logEntity.setDoctorId(doctorEntity.getId());
        doctorBusinessService.submitLog(logEntity, doctorEntity);
        return R.ok();
    }


    @PostMapping("/additional-log")
    @DoctorLogin
    @UserLog("追加日志")
    public R additionalLog(@DoctorLoginUser DoctorEntity doctorEntity, @RequestBody DoctorLogEntity logEntity) {
        logEntity.setDoctorId(doctorEntity.getId());
        doctorBusinessService.additionalLog(logEntity, doctorEntity);
        return R.ok();
    }


    @PostMapping("/update-pwd")
    @DoctorLogin
    @UserLog("修改密码")
    public R additionalLog(@DoctorLoginUser DoctorEntity doctorEntity, @RequestBody UpdatePwdForm updatePwdForm) {
        UserEntity userEntity = userService.selectById(doctorEntity.getUserId());
        String oldPwd = new Sha256Hash(updatePwdForm.getOldPwd(), userEntity.getSalt()).toHex();
        if (userEntity.getPassword().equals(oldPwd)) {
            userEntity.setPassword(new Sha256Hash(updatePwdForm.getNewPwd(), userEntity.getSalt()).toHex());
            userService.updateById(userEntity);
        }else {
            return R.error(410,"旧密码错误");
        }
        return R.ok();
    }

    @PostMapping("/login-out")
    @DoctorLogin
    @UserLog("退出登录")
    public R loginOut(@DoctorLoginUser DoctorEntity doctorEntity){
        UserEntity userEntity = userService.selectById(doctorEntity.getUserId());
        userEntity.setDeviceId("");
        userService.updateById(userEntity);
        return R.ok();
    }
}
