package io.renren.modules.backend.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.sun.org.apache.regexp.internal.RE;
import io.renren.common.utils.Constant;
import io.renren.modules.app.config.JPushUtil;
import io.renren.modules.backend.service.DoctorBusinessService;
import io.renren.modules.doctor.entity.*;
import io.renren.modules.doctor.service.DoctorLogService;
import io.renren.modules.doctor.service.DoctorLogTempService;
import io.renren.modules.doctor.service.ServiceOrderRecordService;
import io.renren.modules.doctor.service.ServiceOrderService;
import io.renren.modules.member.entity.MemberCommentsEntity;
import io.renren.modules.member.entity.MemberEntity;
import io.renren.modules.member.entity.MemberTaskEntity;
import io.renren.modules.member.service.MemberAllergyService;
import io.renren.modules.member.service.MemberCommentsService;
import io.renren.modules.member.service.MemberService;
import io.renren.modules.member.service.MemberTaskService;
import io.renren.modules.sys.entity.JPushSet;
import io.renren.modules.sys.service.JPushService;
import io.renren.modules.sys.service.MsgContentService;
import io.renren.modules.user.entity.UserEntity;
import io.renren.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xpf
 */
@Service("doctorBusinessService")
public class DoctorBusinessServiceImpl implements DoctorBusinessService {

    @Autowired
    ServiceOrderService serviceOrderService;
    @Autowired
    ServiceOrderRecordService recordService;
    @Autowired
    MemberTaskService taskService;
    @Autowired
    MemberService memberService;
    @Autowired
    DoctorLogService doctorLogService;
    @Autowired
    DoctorLogTempService logTempService;
    @Autowired
    MemberCommentsService commentsService;
    @Autowired
    UserService userService;
    @Autowired
    JPushService jPushService;
    @Autowired
    MsgContentService msgContentService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void receiveSos(DoctorEntity doctorEntity, MemberTaskEntity taskEntity) {
        taskEntity.setStatus(Constant.TaskStatus.is_received.getValue());
        taskService.updateById(taskEntity);
        MemberEntity memberEntity = memberService.selectById(taskEntity.getPublisherId());

        ServiceOrderEntity serviceOrderEntity = new ServiceOrderEntity();
        serviceOrderEntity.setReceiveId(taskEntity.getCustomerMainId());
        serviceOrderEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        serviceOrderEntity.setMemberId(taskEntity.getPublisherId());
        serviceOrderEntity.setMemberSn(memberEntity.getSn());
        serviceOrderEntity.setStatus(Constant.ServiceOrderStatus.is_pick_up_order.getValue());
        serviceOrderEntity.setTaskId(taskEntity.getId());
        serviceOrderEntity.setTaskSn(taskEntity.getTaskSn());
        serviceOrderService.insert(serviceOrderEntity);

        //
        ServiceOrderRecordEntity receiveRecord = new ServiceOrderRecordEntity();
        receiveRecord.setReceiveId(serviceOrderEntity.getReceiveId());
        receiveRecord.setMemberId(serviceOrderEntity.getMemberId());
        receiveRecord.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        receiveRecord.setMemberSn(serviceOrderEntity.getMemberSn());
        receiveRecord.setTaskSn(taskEntity.getTaskSn());
        receiveRecord.setRemark(Constant.ServiceOrderRemark.is_pick_up_order.getValue());
        receiveRecord.setTaskId(taskEntity.getId());
        receiveRecord.setType(Constant.TaskType.is_sos.getValue());
        receiveRecord.setStatus(Constant.ServiceOrderStatus.is_pick_up_order.getValue());
        receiveRecord.setOptionName(doctorEntity.getName());
        recordService.insert(receiveRecord);

//个体会员的推送
        UserEntity userEntity = userService.selectById(memberEntity.getUserId());
        String alias = userEntity.getDeviceId();
        String alert = msgContentService.getReceiveMsg();
        JPushSet config = jPushService.getMemberJPushConfig();
        Map<String, String> doctorMap = new HashMap<>();
        doctorMap.put("id", String.valueOf(taskEntity.getPublisherId()));
        doctorMap.put("type", String.valueOf(Constant.msgRemind.is_weak.getValue()));
        doctorMap.put("taskId", String.valueOf(taskEntity.getId()));
        doctorMap.put("avatar", doctorEntity.getAvatar());
        doctorMap.put("name", doctorEntity.getName());
        doctorMap.put("sn", taskEntity.getTaskSn());
        JPushUtil.push(alias, alert, config.getSecret(), config.getAppKey(), "json", JSON.toJSONString(doctorMap));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void receiveMsg(DoctorEntity doctorEntity, MemberTaskEntity taskEntity) {
        taskEntity.setStatus(Constant.TaskStatus.is_received.getValue());
        taskService.updateById(taskEntity);
        MemberEntity memberEntity = memberService.selectById(taskEntity.getPublisherId());

        ServiceOrderEntity serviceOrderEntity = new ServiceOrderEntity();
        serviceOrderEntity.setReceiveId(taskEntity.getCustomerMainId());
        serviceOrderEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        serviceOrderEntity.setMemberId(taskEntity.getPublisherId());
        serviceOrderEntity.setMemberSn(memberEntity.getSn());
        serviceOrderEntity.setStatus(Constant.ServiceOrderStatus.is_pick_up_order.getValue());
        serviceOrderEntity.setTaskId(taskEntity.getId());
        serviceOrderEntity.setTaskSn(taskEntity.getTaskSn());
        serviceOrderService.insert(serviceOrderEntity);

        //
        ServiceOrderRecordEntity receiveRecord = new ServiceOrderRecordEntity();
        receiveRecord.setReceiveId(serviceOrderEntity.getReceiveId());
        receiveRecord.setMemberId(serviceOrderEntity.getMemberId());
        receiveRecord.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        receiveRecord.setMemberSn(serviceOrderEntity.getMemberSn());
        receiveRecord.setTaskSn(taskEntity.getTaskSn());
        receiveRecord.setRemark(Constant.ServiceOrderRemark.is_pick_up_order.getValue());
        receiveRecord.setTaskId(taskEntity.getId());
        receiveRecord.setType(Constant.TaskType.is_message.getValue());
        receiveRecord.setStatus(Constant.ServiceOrderStatus.is_pick_up_order.getValue());
        receiveRecord.setOptionName(doctorEntity.getName());
        recordService.insert(receiveRecord);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void finishSos(DoctorEntity doctorEntity, MemberTaskEntity taskEntity) {

        this.saveAndUpdateOrder(doctorEntity, taskEntity);
    }

    private void saveAndUpdateOrder(DoctorEntity doctorEntity, MemberTaskEntity taskEntity) {
        taskEntity.setStatus(Constant.TaskStatus.is_close.getValue());
        taskService.updateById(taskEntity);

        ServiceOrderRecordEntity recordEntity = new ServiceOrderRecordEntity();
        recordEntity.setOptionName(doctorEntity.getName());
        recordEntity.setStatus(Constant.ServiceOrderStatus.is_doctor_disposal.getValue());
        recordEntity.setTaskSn(taskEntity.getTaskSn());
        recordEntity.setTaskId(taskEntity.getId());
        recordEntity.setType(taskEntity.getType());
        recordEntity.setRemark(Constant.ServiceOrderRemark.is_doctor_disposal.getValue());
        recordEntity.setMemberSn(taskEntity.getMemberSn());
        recordEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        recordEntity.setReceiveId(doctorEntity.getId());
        recordEntity.setMemberId(taskEntity.getPublisherId());
        recordService.insert(recordEntity);

        Wrapper<ServiceOrderEntity> serviceOrderEntityWrapper = new EntityWrapper<>();
        serviceOrderEntityWrapper.eq("task_id", taskEntity.getId()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue()).eq("receive_id", doctorEntity.getId());
        ServiceOrderEntity serviceOrderEntity = serviceOrderService.selectOne(serviceOrderEntityWrapper);
        serviceOrderEntity.setStatus(Constant.ServiceOrderStatus.is_doctor_disposal.getValue());
        serviceOrderEntity.setGmtModified(new Date());
        serviceOrderService.updateById(serviceOrderEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void finishMsg(DoctorEntity doctorEntity, MemberTaskEntity taskEntity) {
        this.saveAndUpdateOrder(doctorEntity, taskEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertLog(DoctorLogEntity logEntity, DoctorEntity doctorEntity) {
        logEntity.setIsAppointment(0);
        doctorLogService.insert(logEntity);
        Wrapper<ServiceOrderEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("task_id", logEntity.getTaskId()).eq("receive_id", logEntity.getDoctorId());
        ServiceOrderEntity orderEntity = serviceOrderService.selectOne(wrapper);
        orderEntity.setStatus(Constant.ServiceOrderStatus.is_doctor_finish.getValue());
        serviceOrderService.updateById(orderEntity);
        ServiceOrderRecordEntity recordEntity = new ServiceOrderRecordEntity();
        recordEntity.setMemberId(logEntity.getMemberId());
        recordEntity.setReceiveId(logEntity.getDoctorId());
        recordEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        recordEntity.setRemark(Constant.ServiceOrderRemark.is_doctor_finish.getValue());
        recordEntity.setTaskId(logEntity.getTaskId());
        recordEntity.setTaskSn(logEntity.getTaskSn());
        recordEntity.setStatus(Constant.ServiceOrderStatus.is_doctor_finish.getValue());
        recordEntity.setMemberSn(logEntity.getMemberSn());
        recordEntity.setOptionName(doctorEntity.getName());
        MemberTaskEntity taskEntity = taskService.selectById(logEntity.getTaskId());
        recordEntity.setType(taskEntity.getType());
        recordService.insert(recordEntity);

        MemberCommentsEntity memberCommentsEntity = new MemberCommentsEntity();
        memberCommentsEntity.setAttitude(0.0);
        memberCommentsEntity.setContent("");
        memberCommentsEntity.setFocusId(doctorEntity.getId());
        memberCommentsEntity.setFocusType(Constant.CommentFocusType.is_doctor.getValue());
        memberCommentsEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        memberCommentsEntity.setEffect(0.0);
        memberCommentsEntity.setMemberSn(logEntity.getMemberSn());
        memberCommentsEntity.setSpeed(0.0);
        memberCommentsEntity.setTaskId(taskEntity.getId());
        memberCommentsEntity.setType(Constant.CommentType.is_member.getValue());
        memberCommentsEntity.setTaskType(taskEntity.getType());
        memberCommentsEntity.setTaskSn(taskEntity.getTaskSn());
        MemberEntity memberEntity = memberService.selectById(taskEntity.getPublisherId());
        memberCommentsEntity.setMemberSn(memberEntity.getSn());
        memberCommentsEntity.setUserId(memberEntity.getUserId());
        memberCommentsEntity.setMemberId(memberEntity.getId());
        memberCommentsEntity.setTaskTime(taskEntity.getTime());
        memberCommentsEntity.setGetOrderTime(orderEntity.getGmtCreate());
        memberCommentsEntity.setStatus(Constant.CommentStatus.is_not_comment.getValue());
        commentsService.insert(memberCommentsEntity);

//TODO:添加推送给会员

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void additionalLog(DoctorLogEntity logEntity, DoctorEntity doctorEntity) {
        logEntity.setIsAppointment(1);
        doctorLogService.insert(logEntity);
        Wrapper<ServiceOrderEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("task_id", logEntity.getTaskId()).eq("receive_id", logEntity.getDoctorId());
        ServiceOrderEntity orderEntity = serviceOrderService.selectOne(wrapper);
        orderEntity.setStatus(Constant.ServiceOrderStatus.is_doctor_finish.getValue());
        orderEntity.setGmtModified(new Date());
        serviceOrderService.updateById(orderEntity);
        ServiceOrderRecordEntity recordEntity = new ServiceOrderRecordEntity();
        recordEntity.setMemberId(logEntity.getMemberId());
        recordEntity.setReceiveId(logEntity.getDoctorId());
        recordEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        recordEntity.setRemark(Constant.ServiceOrderRemark.is_doctor_finish.getValue());
        recordEntity.setTaskId(logEntity.getTaskId());
        recordEntity.setTaskSn(logEntity.getTaskSn());
        recordEntity.setStatus(Constant.ServiceOrderStatus.is_doctor_finish.getValue());
        recordEntity.setMemberSn(logEntity.getMemberSn());
        recordEntity.setOptionName(doctorEntity.getName());
        MemberTaskEntity taskEntity = taskService.selectById(logEntity.getTaskId());
        recordEntity.setType(taskEntity.getType());
        recordService.insert(recordEntity);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void submitLog(DoctorLogTempEntity logEntity, DoctorEntity doctorEntity) {
        Wrapper<ServiceOrderEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("task_id", logEntity.getTaskId()).eq("receive_id", logEntity.getDoctorId());
        ServiceOrderEntity orderEntity = serviceOrderService.selectOne(wrapper);
        if (orderEntity.getStatus() != Constant.ServiceOrderStatus.is_doctor_finish.getValue()) {
            orderEntity.setStatus(Constant.ServiceOrderStatus.is_doctor_finish.getValue());
            orderEntity.setGmtModified(new Date());
            serviceOrderService.updateById(orderEntity);
            ServiceOrderRecordEntity recordEntity = new ServiceOrderRecordEntity();
            recordEntity.setMemberId(logEntity.getMemberId());
            recordEntity.setReceiveId(logEntity.getDoctorId());
            recordEntity.setRemark(Constant.ServiceOrderRemark.is_doctor_finish.getValue());
            recordEntity.setTaskId(logEntity.getTaskId());
            recordEntity.setTaskSn(logEntity.getTaskSn());
            recordEntity.setStatus(Constant.ServiceOrderStatus.is_doctor_finish.getValue());
            recordEntity.setMemberSn(logEntity.getMemberSn());
            recordEntity.setOptionName(doctorEntity.getName());
            MemberTaskEntity taskEntity = taskService.selectById(logEntity.getTaskId());
            recordEntity.setType(taskEntity.getType());
            recordService.insert(recordEntity);
            DoctorLogEntity doctorLogEntity = new DoctorLogEntity();
            doctorLogEntity.setDoctorId(logEntity.getDoctorId());
            doctorLogEntity.setContent(logEntity.getContent());
            doctorLogEntity.setIsAppointment(0);
            doctorLogEntity.setMemberId(logEntity.getMemberId());
            doctorLogEntity.setMemberSn(logEntity.getMemberSn());
            doctorLogEntity.setPhotos(logEntity.getPhotos());
            doctorLogEntity.setTaskId(logEntity.getTaskId());
            doctorLogEntity.setTaskSn(logEntity.getTaskSn());
            logTempService.deleteById(logEntity.getId());
            doctorLogService.insert(doctorLogEntity);

            MemberCommentsEntity memberCommentsEntity = new MemberCommentsEntity();
            memberCommentsEntity.setAttitude(0.0);
            memberCommentsEntity.setContent("");
            memberCommentsEntity.setFocusId(doctorEntity.getId());
            memberCommentsEntity.setFocusType(Constant.CommentFocusType.is_doctor.getValue());
            memberCommentsEntity.setEffect(0.0);
            memberCommentsEntity.setMemberSn(logEntity.getMemberSn());
            memberCommentsEntity.setSpeed(0.0);
            memberCommentsEntity.setTaskId(taskEntity.getId());
            memberCommentsEntity.setTaskSn(taskEntity.getTaskSn());
            memberCommentsEntity.setType(Constant.CommentType.is_member.getValue());
            memberCommentsEntity.setTaskType(taskEntity.getType());
            MemberEntity memberEntity = memberService.selectById(taskEntity.getPublisherId());
            memberCommentsEntity.setUserId(memberEntity.getUserId());
            memberCommentsEntity.setMemberId(memberEntity.getId());
            memberCommentsEntity.setMemberSn(memberEntity.getSn());
            memberCommentsEntity.setTaskTime(taskEntity.getTime());
            memberCommentsEntity.setGetOrderTime(orderEntity.getGmtCreate());
            memberCommentsEntity.setStatus(Constant.CommentStatus.is_not_comment.getValue());
            commentsService.insert(memberCommentsEntity);

        } else {
            ServiceOrderRecordEntity recordEntity = new ServiceOrderRecordEntity();
            recordEntity.setMemberId(logEntity.getMemberId());
            recordEntity.setReceiveId(logEntity.getDoctorId());
            recordEntity.setRemark(Constant.ServiceOrderRemark.is_doctor_additional.getValue());
            recordEntity.setTaskId(logEntity.getTaskId());
            recordEntity.setTaskSn(logEntity.getTaskSn());
            recordEntity.setStatus(Constant.ServiceOrderStatus.is_doctor_finish.getValue());
            recordEntity.setMemberSn(logEntity.getMemberSn());
            recordEntity.setOptionName(doctorEntity.getName());
            MemberTaskEntity taskEntity = taskService.selectById(logEntity.getTaskId());
            recordEntity.setType(taskEntity.getType());
            recordService.insert(recordEntity);
            DoctorLogEntity doctorLogEntity = new DoctorLogEntity();
            doctorLogEntity.setDoctorId(logEntity.getDoctorId());
            doctorLogEntity.setContent(logEntity.getContent());
            doctorLogEntity.setMemberId(logEntity.getMemberId());
            doctorLogEntity.setMemberSn(logEntity.getMemberSn());
            doctorLogEntity.setPhotos(logEntity.getPhotos());
            doctorLogEntity.setTaskId(logEntity.getTaskId());
            doctorLogEntity.setTaskSn(logEntity.getTaskSn());
            doctorLogEntity.setIsAppointment(1);
            logTempService.deleteById(logEntity.getId());
            doctorLogService.insert(doctorLogEntity);
        }


        //TODO:添加推送给会员

    }
}
