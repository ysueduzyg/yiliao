package io.renren.modules.app.controller;

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
import io.renren.modules.app.annotation.MemberLogin;
import io.renren.modules.app.annotation.MemberLoginUser;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.app.form.MemberDetail;
import io.renren.modules.app.form.UpdatePwdForm;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.doctor.entity.*;
import io.renren.modules.doctor.service.*;
import io.renren.modules.member.entity.*;
import io.renren.modules.member.service.*;
import io.renren.modules.user.entity.UserEntity;
import io.renren.modules.user.service.UserService;
import io.swagger.annotations.Api;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/app/m")
@Api("APP会员管理接口")
@SuppressWarnings("unchecked")
public class AppMemberApiController {

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
    private ServiceOrderRecordService recordService;
    @Autowired
    private ContentHtmlService contentHtmlService;
    @Autowired
    private MemberTagService tagService;
    @Autowired
    private TagsService tagsService;
    @Autowired
    private ContentTagTempService contentTagTempService;


    @PostMapping("login")
    public R memberLogin(@RequestBody LoginForm form) {
        ValidatorUtils.validateEntity(form);
        MemberEntity memberEntity = userService.loginByMember(form);
        //生成token
        String token = jwtUtils.generateToken(memberEntity.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());
        map.put("user", memberEntity);
        return R.ok().put("result", map);
    }

    @PostMapping("isLogin")
    @MemberLogin
    public R memberLogin(@MemberLoginUser MemberEntity memberEntity) {
        return R.ok();
    }

    /**
     * 退出登录
     *
     * @return
     */
    public R loginOut() {

        return R.ok();
    }


    /**
     * 获取个人用户信息
     *
     * @return
     */
    @MemberLogin
    @PostMapping("info")
    public R info(@MemberLoginUser MemberEntity memberEntity) {
        Wrapper<DoctorEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("id", memberEntity.getDoctorId());
        DoctorEntity doctorEntity = doctorService.selectOne(wrapper);

        MemberDetail detail = new MemberDetail();
        detail.setAvatar(memberEntity.getAvatar());
        detail.setDoctorId(doctorEntity.getId());
        detail.setDoctorAvatar(doctorEntity.getAvatar());
        detail.setDoctorName(doctorEntity.getName());
        detail.setDoctorLevelName(doctorEntity.getJobLevel());
        detail.setName(memberEntity.getRealName());
        detail.setUserId(memberEntity.getUserId());
        detail.setMemberId(memberEntity.getId());
        detail.setSn(memberEntity.getSn());
        MemberLevelEntity levelEntity = levelService.selectById(memberEntity.getLevel());
        detail.setSos(levelEntity.getSos());
        detail.setLife(levelEntity.getLife());

        if (levelEntity.getSos() == Constant.LevelType.is_sos.getValue()) {
            Wrapper<MemberTaskEntity> taskEntityEntityWrapper = new EntityWrapper<>();
            taskEntityEntityWrapper.eq("publisher_id", memberEntity.getId()).eq("type", Constant.TaskType.is_sos.getValue()).eq("status", Constant.TaskStatus.is_not_receive.getValue());
            List<MemberTaskEntity> list = taskService.selectList(taskEntityEntityWrapper);
            if (list.size() > 0) {
                detail.setSosUsed(Constant.SosUsedType.is_used.getValue());
            } else {
                detail.setSosUsed(Constant.SosUsedType.is_can_use.getValue());
            }
        }
        if (levelEntity.getLife() == Constant.LevelLifeType.is_life.getValue()) {
            Wrapper<MemberTaskEntity> taskEntityEntityWrapper = new EntityWrapper<>();
            taskEntityEntityWrapper.eq("publisher_id", memberEntity.getId()).eq("type", Constant.TaskType.is_call_housekeeper.getValue()).eq("status", Constant.TaskStatus.is_not_receive.getValue());
            List<MemberTaskEntity> list = taskService.selectList(taskEntityEntityWrapper);
            if (list.size() > 0) {
                detail.setLifeUsed(Constant.LifeUsedType.is_used.getValue());
            } else {
                detail.setLifeUsed(Constant.LifeUsedType.is_can_use.getValue());
            }

        }
        Wrapper<ServiceOrderEntity> orderEntityWrapper = new EntityWrapper<>();
        orderEntityWrapper.eq("member_sn", memberEntity.getSn())
                .eq("member_id", memberEntity.getId())
                .eq("status", Constant.ServiceOrderStatus.is_doctor_finish.getValue());

        Integer num = serviceOrderService.selectCount(orderEntityWrapper);
        detail.setCommentsNum(num);
        return R.ok().put("result", detail);
    }

    /**
     * 修改用户信息
     */
    @MemberLogin
    public R updateInfo(MemberEntity memberEntity) {
        memberService.updateById(memberEntity);
        return R.ok();
    }

    /**
     * 查看账号下子账户
     *
     * @return
     */
    public R childList() {

        return R.ok();
    }

    /**
     * 查看检查记录
     *
     * @return
     */
    public R checkList() {

        return R.ok();
    }

    /**
     * 用药记录
     *
     * @return
     */
    public R drugList() {

        return R.ok();
    }

    /**
     * 查看用户爱好信息
     *
     * @return
     */
    public R lifeInfo() {
        return R.ok();
    }

    /**
     * 修改用户爱好信息
     *
     * @return
     */
    public R updateLife() {
        return R.ok();
    }


    /**
     * 发送求救信息
     *
     * @return
     */
    @MemberLogin
    @PostMapping("sos")
    public R sendMessageSOS(@MemberLoginUser MemberEntity memberEntity) {
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
     * 管家服务
     *
     * @param memberEntity
     * @return
     */
    @MemberLogin
    @PostMapping("life")
    public R sendMessageLife(@MemberLoginUser MemberEntity memberEntity) {
        MemberTaskEntity taskEntity = new MemberTaskEntity();
        taskEntity.setPublisherId(memberEntity.getId());
        taskEntity.setCustomerMainId(memberEntity.getHouseKeeperId());
        taskEntity.setStatus(Constant.TaskStatus.is_not_receive.getValue());
        taskEntity.setTaskType(Constant.TaskDoctorType.is_default.getValue());
        taskEntity.setTime(System.currentTimeMillis());
        taskEntity.setType(Constant.TaskType.is_call_housekeeper.getValue());
        String date = DateUtils.format(new Date(), "yyyyMMddHHmmss");
        taskEntity.setTaskSn(Constant.HK + memberEntity.getId() + date);
        taskEntity.setMemberSn(memberEntity.getSn());
        taskService.saveHouseKeeper(taskEntity, memberEntity.getRealName());
        return R.ok();
    }


    /**
     * 发送咨询信息
     *
     * @return
     */
    @MemberLogin
    @PostMapping("msg")
    public R sendMessageInfo(@MemberLoginUser MemberEntity memberEntity) {
        MemberTaskEntity taskEntity = new MemberTaskEntity();
        taskEntity.setPublisherId(memberEntity.getId());
        taskEntity.setCustomerMainId(memberEntity.getDoctorId());
        taskEntity.setStatus(Constant.TaskStatus.is_not_receive.getValue());
        taskEntity.setTaskType(Constant.TaskDoctorType.is_default.getValue());
        taskEntity.setTime(System.currentTimeMillis());
        taskEntity.setType(Constant.TaskType.is_message.getValue());
        String date = DateUtils.format(new Date(), "yyyyMMddHHmmss");
        taskEntity.setTaskSn(Constant.MSG + memberEntity.getId() + date);
        taskEntity.setMemberSn(memberEntity.getSn());
        taskService.saveMsg(taskEntity, memberEntity.getRealName());

        return R.ok();
    }

    /**
     * 提交评价信息
     *
     * @return
     */
    public R commitComment() {
        return R.ok();
    }


    /**
     * 评价记录
     *
     * @return
     */
    @PostMapping("comments-list")
    @UserLog("获取评价记录")
    @MemberLogin
    public R commentsList(@MemberLoginUser MemberEntity memberEntity, @RequestBody Map<String, Object> params) {
        params.put("memberId", String.valueOf(memberEntity.getId()));
        PageUtils page = commentsService.queryMemberPage(params);
        return R.ok().put("page", page);
    }

    /**
     * 修改评价
     *
     * @return
     */
    @PostMapping("update-comment")
    @UserLog("会员评价")
    @MemberLogin
    public R updateComment(@MemberLoginUser MemberEntity memberEntity, @RequestBody MemberCommentsEntity memberCommentsEntity) {
        if (memberCommentsEntity.getId() == null) {
            return R.error(410, "订单不存在,请联系客服");
        }

        memberService.updateComment(memberEntity, memberCommentsEntity);

        return R.ok();
    }

    /**
     * 上传设备信息
     *
     * @return
     */

    public R uploadDeviceId() {
        return R.ok();
    }


    @PostMapping("alias/{alias}")
    @MemberLogin
    @UserLog("提交别名")
    public R setMemberDeviceId(@MemberLoginUser MemberEntity memberEntity, @PathVariable String alias) {
        Integer userId = memberEntity.getUserId();
        UserEntity userEntity = userService.selectById(userId);
        userEntity.setDeviceId(alias);
        userService.updateById(userEntity);
        return R.ok();
    }


    @PostMapping("/login-out")
    @MemberLogin
    @UserLog("退出登录")
    public R loginOut(@MemberLoginUser MemberEntity memberEntity) {
        UserEntity userEntity = userService.selectById(memberEntity.getUserId());
        userEntity.setDeviceId("");
        userService.updateById(userEntity);
        return R.ok();
    }


    @PostMapping("/update-pwd")
    @MemberLogin
    @UserLog("修改密码")
    public R additionalLog(@MemberLoginUser MemberEntity memberEntity, @RequestBody UpdatePwdForm updatePwdForm) {
        UserEntity userEntity = userService.selectById(memberEntity.getUserId());
        String oldPwd = new Sha256Hash(updatePwdForm.getOldPwd(), userEntity.getSalt()).toHex();
        if (userEntity.getPassword().equals(oldPwd)) {
            userEntity.setPassword(new Sha256Hash(updatePwdForm.getNewPwd(), userEntity.getSalt()).toHex());
            userService.updateById(userEntity);
        } else {
            return R.error(410, "旧密码错误");
        }
        return R.ok();
    }


    @PostMapping("/message-list")
    @MemberLogin
    @UserLog("获取推送列表")
    public R messageList(@MemberLoginUser MemberEntity memberEntity, @RequestBody Map<String, Object> params) {

        Wrapper<MemberTagEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("member_id", memberEntity.getId()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());

        List<MemberTagEntity> list = tagService.selectList(wrapper);
        if (list.size() == 0) {
            return R.ok().put("list", new ArrayList<>());
        }


        List<Integer> ids = list.stream()
                .map(MemberTagEntity::getTagId)
                .collect(Collectors.toList());
        Wrapper<ContentTagTempEntity> tagTempEntityWrapper = new EntityWrapper<>();
        tagTempEntityWrapper.in("tag_id", ids).setSqlSelect(" DISTINCT content_id as contentId ");
        List<ContentTagTempEntity> tagTempEntities = contentTagTempService.selectList(tagTempEntityWrapper);

        List<Long> contentIds = tagTempEntities.stream()
                .map(ContentTagTempEntity::getContentId)
                .collect(Collectors.toList());
        PageUtils page = contentHtmlService.queryPageByMember(contentIds, params);

        return R.ok().put("page", page);
    }

    @PostMapping("/message-info/{id}")
    @MemberLogin
    @UserLog("获取推送详情")
    public R messageContentInfo(@MemberLoginUser MemberEntity memberEntity, @RequestBody Integer id) {

        ContentHtmlEntity htmlEntity = contentHtmlService.selectById(id);
        return R.ok().put("info", htmlEntity);

    }
}


