package io.renren.modules.app.controller;


import com.alibaba.fastjson.JSON;
import io.renren.common.utils.Constant;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.app.config.JPushUtil;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.member.entity.MemberTaskEntity;
import io.renren.modules.member.service.MemberService;
import io.renren.modules.member.service.MemberTaskService;
import io.renren.modules.user.service.UserService;
import io.renren.modules.app.utils.CronDateUtils;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.job.entity.ScheduleJobEntity;
import io.renren.modules.job.service.ScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * APP登录授权
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/app")
@Api("APP登录接口")
public class AppLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private JPushUtil jPushUtil;

    @Autowired
    private ScheduleJobService jobLogService;

    @Autowired
    private MemberTaskService taskService;
    @Autowired
    private MemberService memberService;

    @GetMapping("test/push")
    public R textPush() {
        MemberEntity memberEntity = memberService.selectById(2);
        MemberTaskEntity taskEntity = new MemberTaskEntity();
        taskEntity.setPublisherId(memberEntity.getId());
        taskEntity.setCustomerMainId(memberEntity.getDoctorId());
        taskEntity.setStatus(Constant.TaskStatus.is_not_receive.getValue());
        taskEntity.setTaskType(Constant.TaskDoctorType.is_default.getValue());
        taskEntity.setTime(System.currentTimeMillis());
        taskEntity.setType(Constant.TaskType.is_sos.getValue());
        String date = DateUtils.format(new Date(), "yyyyMMddHHmmss");
        taskEntity.setTaskSn(Constant.SOS + memberEntity.getId() + date);
        taskEntity.setMemberSn(memberEntity.getSn());
        taskService.saveSos(taskEntity, memberEntity.getRealName());
        return R.ok();
    }


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

}
