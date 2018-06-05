package io.renren.modules.backend.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.annotation.SysLog;
import io.renren.common.annotation.UserLog;
import io.renren.common.utils.Constant;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.app.annotation.DoctorLogin;
import io.renren.modules.app.annotation.DoctorLoginUser;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.app.form.MemberForm;
import io.renren.modules.app.form.TaskServiceOrderForm;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.backend.form.DoctorBackendTempLogForm;
import io.renren.modules.backend.service.DoctorBusinessService;
import io.renren.modules.doctor.entity.*;
import io.renren.modules.doctor.service.*;
import io.renren.modules.member.entity.*;
import io.renren.modules.member.service.*;
import io.renren.modules.user.entity.UserEntity;
import io.renren.modules.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/do")
@Api("医生后台管理")
public class DoctorManagerController {


    @Autowired
    UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private MemberChildService childService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private DoctorLogService doctorLogService;
    @Autowired
    private MemberTaskService taskService;
    @Autowired
    private MemberTaskListService taskListService;
    @Autowired
    private ServiceOrderService serviceOrderService;
    @Autowired
    private ServiceOrderRecordService recordService;
    @Autowired
    private DoctorBusinessService doctorBusinessService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MemberLevelService levelService;
    @Autowired
    private MemberLifeService lifeService;
    @Autowired
    private MemberDrugService drugService;
    @Autowired
    private MemberCheckService checkService;
    @Autowired
    private MemberAllergyService allergyService;
    @Autowired
    private MemberGenenticDiseaseService genenticDiseaseService;
    @Autowired
    private MemberCurrentService currentService;
    @Autowired
    private MemberHistoryDiseaseService diseaseService;
    @Autowired
    private DoctorLogService logService;
    @Autowired
    private DoctorLogTempService logTempService;
    @Autowired
    private HouseKeeperService houseKeeperService;
    @Autowired
    private MemberSurgeryService surgeryService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private MemberTagService memberTagService;
    /**
     * 登录
     */
    @PostMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm form) {
        //表单校验
        ValidatorUtils.validateEntity(form);
        //用户登录
        long userId = userService.login(form);
        //生成token
        String token = jwtUtils.generateToken(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());
        return R.ok(map);
    }


    @GetMapping("info")
    @DoctorLogin
    @UserLog("获取医生详情")
    public R index(@DoctorLoginUser DoctorEntity doctorEntity)
    {
        return R.ok().put("doctor", doctorEntity);
    }

    @RequestMapping("/doctor/update")
    @UserLog("添加会员")
    @DoctorLogin
    public R update(@RequestBody DoctorEntity doctorForm, @DoctorLoginUser DoctorEntity doctorEntity) {

        doctorForm.setId(doctorEntity.getId());
        doctorForm.setGmtModified(new Date());
        doctorService.updateById(doctorForm);
        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        MemberEntity memberEntity = memberService.selectById(id);

        return R.ok().put("member", memberEntity);
    }


    @GetMapping("sos-list")
    @DoctorLogin
    @UserLog("获取紧急求助列表")
    public R sosList(@DoctorLoginUser DoctorEntity doctorEntity, @RequestParam Map<String, Object> params) {
        PageUtils page = taskService.queryPageSosByDoctor(params, doctorEntity.getId());
        return R.ok().put("page", page);
    }

    @GetMapping("msg-list")
    @DoctorLogin
    @UserLog("获取咨询列表")
    public R messages(@DoctorLoginUser DoctorEntity doctorEntity, @RequestParam Map<String, Object> params) {
        PageUtils page = taskService.queryPageMsgByDoctor(params, doctorEntity.getId());
        return R.ok().put("page", page);
    }

    @PostMapping("msg/receive/{id}")
    @DoctorLogin
    @UserLog("接受咨询订单")
    public R receiveMsg(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer id) {
        MemberTaskEntity taskEntity = taskService.selectById(id);
        if (taskEntity == null) {
            return R.error(404, "咨询不存在");
        }
        doctorBusinessService.receiveMsg(doctorEntity, taskEntity);
        return R.ok();
    }

    @PostMapping("sos/receive/{id}")
    @DoctorLogin
    @UserLog("接受求助订单")
    public R receiveSos(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer id) {
//        TODO
        //关闭 推送任务
        MemberTaskEntity taskEntity = taskService.selectById(id);
        if (taskEntity == null) {
            return R.error(404, "呼叫不存在");
        }
        doctorBusinessService.receiveSos(doctorEntity, taskEntity);
        return R.ok();
    }


    @PostMapping("sos/info/{id}")
    @DoctorLogin
    @UserLog("查看求助时间轴")
    public R sosInfo(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer id) {
        MemberTaskEntity taskEntity = taskService.selectById(id);
        if (taskEntity == null) {
            return R.error(404, "呼叫不存在");
        }
        Wrapper<ServiceOrderRecordEntity> recordEntityWrapper = new EntityWrapper<>();
        recordEntityWrapper.eq("task_id", taskEntity.getId())
                .eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).orderBy("gmt_create");
        List<ServiceOrderRecordEntity> list = recordService.selectList(recordEntityWrapper);
        return R.ok().put("list", list);
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
    @UserLog("医生标注处理完成咨询")
    public R msgFinish(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer id) {
        MemberTaskEntity taskEntity = taskService.selectById(id);
        if (taskEntity == null) {
            return R.error(404, "任务不存在");
        }
        doctorBusinessService.finishMsg(doctorEntity, taskEntity);
        return R.ok();
    }

    @GetMapping("work-list")
    @DoctorLogin
    @UserLog("医生需要填写的日志列表")
    public R workList(@DoctorLoginUser DoctorEntity doctorEntity, @RequestParam Map<String, Object> params) {
        return R.ok().put("page", serviceOrderService.queryByDoctorPage(params, doctorEntity.getId()));
    }


    @GetMapping("/members/list")
    @DoctorLogin
    @UserLog("查看会员列表")
    public R list(@RequestParam Map<String, Object> params, @DoctorLoginUser DoctorEntity doctorEntity) {
        if (doctorEntity.getType() == Constant.DoctorType.is_community_doctor.getValue()) {
            params.put("communityId", String.valueOf(doctorEntity.getCommunityId()));
        } else {
            params.put("enterpriseId", String.valueOf(doctorEntity.getEnterpriseId()));
        }
        PageUtils page = memberService.queryPage(params);
        return R.ok().put("page", page);
    }


    @GetMapping("/members/sn/{sn}")
    @DoctorLogin
    @UserLog("根据sn编号查看会员")
    public R list(@PathVariable("sn") String sn, @DoctorLoginUser DoctorEntity doctorEntity) {
        Wrapper<MemberEntity> wrapper = new EntityWrapper<>();
        wrapper.like("sn", sn).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        if (doctorEntity.getType() == Constant.DoctorType.is_community_doctor.getValue()) {
            wrapper.eq("community_id", doctorEntity.getCommunityId());
        } else {
            wrapper.eq("enterprise_id", doctorEntity.getEnterpriseId());
        }
        return R.ok().put("list", memberService.selectList(wrapper));
    }


    @GetMapping("/members/saveLog")
    @DoctorLogin
    @UserLog("添加日志")
    public R saveLog(@RequestBody DoctorLogEntity logEntity, @DoctorLoginUser DoctorEntity doctorEntity) {
        logEntity.setDoctorId(doctorEntity.getId());
        doctorLogService.insert(logEntity);
        return R.ok();
    }

    @GetMapping("/members/deleteLog")
    @DoctorLogin
    @UserLog("删除日志")
    public R deleteLog(@RequestBody Integer[] ids, @DoctorLoginUser DoctorEntity doctorEntity) {
        Wrapper<DoctorLogEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        DoctorLogEntity logEntity = new DoctorLogEntity();
        logEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        doctorLogService.update(logEntity, wrapper);
        return R.ok();
    }


    @GetMapping("/members/updateLog")
    @DoctorLogin
    @UserLog("修改日志")
    public R updateLog(@RequestBody DoctorLogEntity logEntity, @DoctorLoginUser DoctorEntity doctorEntity) {
        if (logEntity.getDoctorId().equals( doctorEntity.getId())) {
            doctorLogService.updateById(logEntity);
        } else {
            return R.ok("没有权限修改");
        }
        return R.ok();
    }

    @GetMapping("/doctor/communityDoctorsById/{id}")
    @UserLog("查询社区下所有的医生")
    @DoctorLogin
    public R communityDoctorsById(@PathVariable Integer id, @DoctorLoginUser DoctorEntity doctorEntity) {
        Wrapper<DoctorEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("community_id", id).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("doctors", doctorService.selectList(wrapper));
    }

    @GetMapping("/doctor/enterpriseDoctorsById/{id}")
    @UserLog("查询企业下所有的医生")
    @DoctorLogin
    public R enterpriseDoctorsById(@PathVariable Integer id, @DoctorLoginUser DoctorEntity doctorEntity) {
        Wrapper<DoctorEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("enterprise_id", id).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("doctors", doctorService.selectList(wrapper));
    }


    @RequestMapping("/member/save")
    @UserLog("添加会员")
    @DoctorLogin
    public R memberSave(@RequestBody MemberForm memberForm, @DoctorLoginUser DoctorEntity doctorEntity) {
        String mobile = memberForm.getMobile();
        if (StringUtils.isBlank(mobile)) {
            return R.error("手机号码不能为空");
        }

        Wrapper<UserEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("mobile", mobile);
        List<UserEntity> list = userService.selectList(wrapper);
        if (list.size() > 0) {
            return R.error("手机号码重复");
        }
        memberService.save(memberForm);
        return R.ok();
    }


    @RequestMapping("/member/info/{id}")
    @UserLog("会员详情")
    @DoctorLogin
    public R memberInfo(@PathVariable("id") Integer id, @DoctorLoginUser DoctorEntity doctorEntity) {
        MemberEntity memberEntity = memberService.selectById(id);
        return R.ok().put("member", memberEntity);
    }

    @RequestMapping("/member/update")
    @UserLog("修改会员信息")
    @DoctorLogin
    public R updateMember(@RequestBody MemberEntity tblMember, @DoctorLoginUser DoctorEntity doctorEntity) {
        memberService.updateById(tblMember);
        return R.ok();
    }


    @GetMapping("/levels")
    @UserLog("获取所有会员等级")
    @DoctorLogin
    public R levels(@DoctorLoginUser DoctorEntity doctorEntity) {
        Wrapper<MemberLevelEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).orderBy("sort");
        List<MemberLevelEntity> list = levelService.selectList(wrapper);
        return R.ok().put("list", list);
    }

    @RequestMapping("/child-list")
    @UserLog("获取会员副卡列表")
    @DoctorLogin
    public R childList(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryChildPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 检查列表
     */
    @RequestMapping("/drug-list")
    public R drugList(@RequestParam Map<String, Object> params) {
        PageUtils page = drugService.queryPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 修改
     */
    @RequestMapping("/update-check")
    public R updateCheck(@RequestBody MemberCheckEntity checkEntity) {
        checkService.updateById(checkEntity);
        return R.ok();
    }


    /**
     * 信息
     */
    @RequestMapping("/info-check/{id}")
    public R checkInfo(@PathVariable("id") Integer id) {
        MemberCheckEntity checkEntity = checkService.selectById(id);
        return R.ok().put("check", checkEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/add-check")
    public R saveCheck(@RequestBody MemberCheckEntity checkEntity) {
        checkEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        checkService.insert(checkEntity);
        return R.ok();
    }


    @RequestMapping("/update-allergy-disease")
    public R updateAllergyDisease(@RequestBody MemberAllergyEntity allergyEntity) {
        if (allergyEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        allergyService.updateById(allergyEntity);
        return R.ok();
    }


    /**
     * 检查列表
     */
    @RequestMapping("/check-list")
    public R checkList(@RequestParam Map<String, Object> params) {
        PageUtils page = checkService.queryPage(params);
        return R.ok().put("page", page);
    }


    @RequestMapping("/update-genentic-disease")
    public R updateGenenticDisease(@RequestBody MemberGenenticDiseaseEntity genenticDiseaseEntity) {
        if (genenticDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        genenticDiseaseService.updateById(genenticDiseaseEntity);
        return R.ok();
    }


    //历史
    @RequestMapping("/genentic-disease")
    public R genenticSymptom(@RequestParam Map<String, Object> params) {
        PageUtils page = genenticDiseaseService.queryPage(params);
        return R.ok().put("page", page);
    }


    @RequestMapping("/allergy-disease")
    public R allergySymptom(@RequestParam Map<String, Object> params) {
        PageUtils page = allergyService.queryPage(params);
        return R.ok().put("page", page);
    }


    @RequestMapping("/save-allergy-disease")
    public R saveAllergyDisease(@RequestBody MemberAllergyEntity allergyEntity) {
        if (allergyEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        allergyEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        allergyService.insert(allergyEntity);
        return R.ok();
    }


    @RequestMapping("/save-genentic-disease")
    public R saveGenenticDisease(@RequestBody MemberGenenticDiseaseEntity genenticDiseaseEntity) {
        if (genenticDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        genenticDiseaseEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        genenticDiseaseService.insert(genenticDiseaseEntity);
        return R.ok();
    }


    /**
     *
     * @param params
     * @return
     */
    @RequestMapping("/history-disease")
    public R historySymptom(@RequestParam Map<String, Object> params) {
        PageUtils page = diseaseService.queryPage(params);
        return R.ok().put("page", page);
    }

    //当前
    @GetMapping("/current-disease/{memberId}")
    public R currentSymptom(@PathVariable Integer memberId) {
        Wrapper<MemberCurrentEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", Integer.valueOf(memberId)).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        MemberCurrentEntity entity = currentService.selectOne(wrapper);
        return R.ok().put("disease", entity);
    }


    @RequestMapping("/save-current-disease")
    public R saveCurrentSymptom(@RequestBody MemberCurrentEntity memberCurrentEntity) {

        Integer memberId = memberCurrentEntity.getMemberId();
        if (memberId == null || memberId == 0) {
            return R.error(404, "未找到会员信息");
        }
        Wrapper<MemberCurrentEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", memberId);
        MemberCurrentEntity old = currentService.selectOne(wrapper);
        if (old == null) {
            currentService.insert(memberCurrentEntity);
        } else {
            old.setCurrentDisease(memberCurrentEntity.getCurrentDisease());
            old.setCurrentQuestion(memberCurrentEntity.getCurrentQuestion());
            currentService.updateById(old);
        }
        return R.ok();
    }

    @RequestMapping("/save-history-disease")
    public R saveHistoryDisease(@RequestBody MemberHistoryDiseaseEntity historyDiseaseEntity) {
        if (historyDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        diseaseService.insert(historyDiseaseEntity);
        return R.ok();
    }

    @RequestMapping("/update-history-disease")
    public R updateHistoryDisease(@RequestBody MemberHistoryDiseaseEntity historyDiseaseEntity) {
        if (historyDiseaseEntity.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        diseaseService.updateById(historyDiseaseEntity);
        return R.ok();
    }


    @GetMapping("/sn/{sn}")
    public R list(@PathVariable("sn") String sn) {
        Wrapper<MemberEntity> wrapper = new EntityWrapper<>();
        wrapper.like("sn", sn).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("list", memberService.selectList(wrapper));
    }


    /**
     * 信息
     */
    @RequestMapping("/info-child/{id}")
    public R chidInfo(@PathVariable("id") Integer id) {
        MemberChildEntity childEntity = childService.selectById(id);
        return R.ok().put("child", childEntity);
    }

    /**
     * 保存
     */
    @RequestMapping("/add-child")
    public R saveChild(@RequestBody MemberChildEntity childEntity) {
        childService.insert(childEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberEntity tblMember) {
        memberService.updateById(tblMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update-child")
    public R updateChild(@RequestBody MemberChildEntity childEntity) {
        childService.updateById(childEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        Wrapper<MemberEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        memberService.update(memberEntity, wrapper);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete-child")
    public R deleteChild(@RequestBody Integer[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Wrapper<MemberChildEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("id", ids[0]);
            MemberChildEntity childEntity = new MemberChildEntity();
            childEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
            childService.update(childEntity, wrapper);
        }
        return R.ok();
    }

    @RequestMapping("/delete-history-disease")
    public R deleteHistoryDisease(@RequestBody MemberHistoryDiseaseEntity diseaseEntity) {
        MemberHistoryDiseaseEntity historyDiseaseEntity = new MemberHistoryDiseaseEntity();
        historyDiseaseEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        historyDiseaseEntity.setId(diseaseEntity.getId());
        diseaseService.updateById(historyDiseaseEntity);
        return R.ok();
    }

    @RequestMapping("/delete-genentic-disease")
    public R deleteGenenticDisease(@RequestBody MemberGenenticDiseaseEntity genenticDiseaseEntity) {
        MemberGenenticDiseaseEntity genenticDisease = new MemberGenenticDiseaseEntity();
        genenticDisease.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        genenticDisease.setId(genenticDiseaseEntity.getId());
        genenticDiseaseService.updateById(genenticDisease);
        return R.ok();
    }


    @RequestMapping("/delete-allergy-disease")
    public R deleteAllergyDisease(@RequestBody MemberAllergyEntity entity) {
        MemberAllergyEntity allergyEntity = new MemberAllergyEntity();
        allergyEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        allergyEntity.setId(entity.getId());
        allergyService.updateById(allergyEntity);
        return R.ok();
    }


    @RequestMapping("/delete-check")
    public R deleteCheck(@RequestBody Integer[] ids) {
        MemberCheckEntity checkEntity = new MemberCheckEntity();
        checkEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        Wrapper<MemberCheckEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        checkService.update(checkEntity, wrapper);
        return R.ok();
    }


    @RequestMapping("/delete-drug")
    public R deleteDrug(@RequestBody Integer[] ids) {
        MemberDrugEntity drugEntity = new MemberDrugEntity();
        drugEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        Wrapper<MemberDrugEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", Arrays.asList(ids));
        drugService.update(drugEntity, wrapper);
        return R.ok();
    }

    @UserLog("添加用药记录")
    @RequestMapping("/add-drug")
    public R saveDrug(@RequestBody MemberDrugEntity drugEntity) {
        drugEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        drugService.insert(drugEntity);
        return R.ok();
    }


    @RequestMapping("/info-drug/{id}")
    @UserLog("查看用药信息")
    public R drugInfo(@PathVariable("id") Integer id) {
        MemberDrugEntity drugEntity = drugService.selectById(id);
        return R.ok().put("drug", drugEntity);
    }


    @RequestMapping("/update-drug")
    @UserLog("修改用药记录")
    public R updateDrug(@RequestBody MemberDrugEntity drugEntity) {
        drugService.updateById(drugEntity);
        return R.ok();
    }

    @UserLog("查看兴趣爱好")
    @RequestMapping("/info-life/{id}")
    public R lifeInfo(@PathVariable("id") Integer id) {
        Wrapper<MemberLifeEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", id);
        MemberLifeEntity lifeEntity = lifeService.selectOne(wrapper);
        return R.ok().put("life", lifeEntity);
    }


    @RequestMapping("/save-life")
    @UserLog("保存兴趣爱好")
    public R save(@RequestBody MemberLifeEntity lifeEntity) {
        Integer memberId = lifeEntity.getMemberId();
        if (memberId == null || memberId == 0) {
            return R.error(404, "未找到会员信息");
        }
        Wrapper<MemberLifeEntity> lifeEntityWrapper = new EntityWrapper<>();
        lifeEntityWrapper.eq("member_id", memberId);
        MemberLifeEntity memberLifeEntity = lifeService.selectOne(lifeEntityWrapper);
        if (memberLifeEntity == null) {
            lifeEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
            lifeService.insert(lifeEntity);
        } else {
            lifeService.updateById(lifeEntity);
        }
        return R.ok();
    }

    @RequestMapping("/log-list")
    @DoctorLogin
    @UserLog("获取日志列表")
    public R logList(@DoctorLoginUser DoctorEntity doctorEntity, @RequestParam Map<String, Object> params) {
        PageUtils page = logService.queryBackendDoctorPage(params, doctorEntity.getId());
        return R.ok().put("page", page).put("info", doctorEntity);
    }


    @GetMapping("/sos-task-list")
    @DoctorLogin
    @UserLog("会员未提交日志的订单")
    public R logSosTask(@DoctorLoginUser DoctorEntity doctorEntity) {
        Map<String, Object> params = new HashMap<>();
        params.put("limit", "100");
        params.put("page", "1");
        PageUtils page = serviceOrderService.queryTaskPage(params, doctorEntity.getId());
        return R.ok().put("page", page);
    }

    @GetMapping("/temp-log-list")
    @DoctorLogin
    @UserLog("获取草稿箱列表")
    public R tempLogList(@DoctorLoginUser DoctorEntity doctorEntity, @RequestParam Map<String, Object> params) {
        PageUtils page = logTempService.queryDoctorBackendPage(params, doctorEntity.getId());
        return R.ok().put("page", page);
    }


    @GetMapping("/temp-log-info/{logId}")
    @DoctorLogin
    @UserLog("获取草稿详情")
    public R tempLogInfo(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer logId) {
        DoctorLogTempEntity tempEntity = logTempService.selectById(logId);
        TaskServiceOrderForm form = new TaskServiceOrderForm();
        form.setDate(DateUtils.format(tempEntity.getGmtCreate(),DateUtils.DATE_TIME_PATTERN));
        form.setDoctorId(tempEntity.getDoctorId());
        form.setMemberId(tempEntity.getMemberId());
        MemberEntity memberEntity = memberService.selectById(tempEntity.getMemberId());
        form.setMemberName(memberEntity.getRealName());
        form.setMemberSn(memberEntity.getSn());
        form.setServiceOrderId(tempEntity.getId());
        form.setTaskId(tempEntity.getTaskId());
        form.setTaskSn(tempEntity.getTaskSn());
        MemberTaskEntity taskEntity = taskService.selectById(tempEntity.getTaskId());
        form.setType(taskEntity.getType());
        if (taskEntity.getType() == Constant.TaskType.is_sos.getValue()) {
            form.setTaskType("求助");
        } else {
            form.setTaskType("咨询");
        }
        return R.ok().put("info", form).put("log",tempEntity);
    }


    @GetMapping("/temp-log-delete/{logId}")
    @DoctorLogin
    @UserLog("删除草稿")
    public R tempLogDelete(@DoctorLoginUser DoctorEntity doctorEntity, @PathVariable Integer logId) {
        DoctorLogTempEntity tempEntity = logTempService.selectById(logId);
        tempEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        logTempService.updateById(tempEntity);
        return R.ok();
    }


    @UserLog("查询社区下所有的管家")
    @GetMapping("/communityHouseKeeperById/{id}")
    @DoctorLogin
    public R communityHouseKeeperById(@PathVariable Integer id,@DoctorLoginUser DoctorEntity doctorEntity) {
        Wrapper<HouseKeeperEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("community_id", id).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        return R.ok().put("houseKeepers", houseKeeperService.selectList(wrapper));
    }


    //历史
    @UserLog("会员外伤手术史")
    @DoctorLogin
    @RequestMapping("/member/surgical-list")
    public R surgeryList(@RequestParam Map<String, Object> params,@DoctorLoginUser DoctorEntity doctorEntity) {
        PageUtils page = surgeryService.queryPage(params);
        return R.ok().put("page", page);
    }

    @UserLog("添加会员外伤手术史")
    @DoctorLogin
    @RequestMapping("/member/add-surgical")
    public R saveSurgery(@RequestBody MemberSurgery surgery,@DoctorLoginUser DoctorEntity doctorEntity) {
        if (surgery.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        surgeryService.insert(surgery);
        return R.ok();
    }

    @UserLog("修改会员病史")
    @DoctorLogin
    @RequestMapping("/member/update-surgical")
    public R updateSurgical(@RequestBody MemberSurgery surgery,@DoctorLoginUser DoctorEntity doctorEntity) {
        if (surgery.getMemberId() == null) {
            return R.error(404, "未找到会员信息");
        }
        surgery.setGmtModified(new Date());
        surgeryService.updateById(surgery);
        return R.ok();
    }

    @UserLog("删除会员病史")
    @DoctorLogin
    @RequestMapping("/member/delete-surgical")
    public R deleteSurgical(@RequestBody MemberSurgery surgery,@DoctorLoginUser DoctorEntity doctorEntity) {
        surgery.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        surgery.setGmtModified(new Date());
        surgeryService.updateById(surgery);

        return R.ok();
    }
    @UserLog("标签列表")
    @DoctorLogin
    @RequestMapping("/tag/all-list")
    public R tagList(@DoctorLoginUser DoctorEntity doctorEntity) {
        List<TagsEntity> list = tagsService.selectList(null);
        return R.ok().put("list", list);
    }



    @UserLog("会员标签")
    @DoctorLogin
    @RequestMapping("/member/memberTags/{id}")
    public R memberTags(@PathVariable("id") Integer id) {
        if (id == null) {
            return R.error(404, "未找到会员信息");
        }
        Wrapper<MemberTagEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", id).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        List<MemberTagEntity> list = memberTagService.selectList(wrapper);
        return R.ok().put("list", list);
    }

    @UserLog("更新或添加会员标签")
    @DoctorLogin
    @RequestMapping("/member/add-or-update-tags")
    public R memberUpdateTags(@RequestBody Map<String, String> params) {
        String id = params.get("memberId");
        String tagId = params.get("tagId");
        String tagName = params.get("tagName");
        if (StringUtils.isBlank(id)) {
            return R.error(404, "未找到会员");
        }

        if (StringUtils.isBlank(tagId)) {
            return R.error(401, "未选择标签");
        }
        MemberTagEntity tagEntity = new MemberTagEntity();
        tagEntity.setMemberId(Integer.valueOf(id));
        tagEntity.setTagId(Integer.valueOf(tagId));
        tagEntity.setTagName(tagName);
        memberTagService.insertOrUpdate(tagEntity);
        return R.ok();
    }

    @UserLog("删除会员标签")
    @DoctorLogin
    @RequestMapping("/member/delete-tags")
    public R memberDeleteTags(@RequestBody Map<String, String> params) {
        String id = params.get("memberId");
        String tagId = params.get("tagId");
        if (StringUtils.isBlank(id)) {
            return R.error(404, "未找到会员");
        }

        if (StringUtils.isBlank(tagId)) {
            return R.error(401, "未选择标签");
        }

        Wrapper<MemberTagEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id",Integer.valueOf(id)).eq("tag_id",tagId);
        memberTagService.delete(wrapper);
        return R.ok();
    }

}
