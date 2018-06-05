package io.renren.modules.backend.service;

import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.doctor.entity.DoctorLogEntity;
import io.renren.modules.doctor.entity.DoctorLogTempEntity;
import io.renren.modules.member.entity.MemberTaskEntity;

/**
 * 医生业务处理类
 * @author xpf
 */
public interface DoctorBusinessService {
    /**
     * 接收求助信息
     * @param doctorEntity
     * @param taskEntity
     */
    void  receiveSos (DoctorEntity doctorEntity, MemberTaskEntity taskEntity);

    /**
     * 接收咨询信息
     * @param doctorEntity
     * @param taskEntity
     */
    void  receiveMsg (DoctorEntity doctorEntity, MemberTaskEntity taskEntity);

    /**
     * 定义完成求助信息
     * @param doctorEntity
     * @param taskEntity
     */
    void  finishSos (DoctorEntity doctorEntity, MemberTaskEntity taskEntity);

    /**
     * 定义完成咨询信息
     * @param doctorEntity
     * @param taskEntity
     */
    void  finishMsg (DoctorEntity doctorEntity, MemberTaskEntity taskEntity);

    /**
     * 添加日志
     * @param logEntity
     * @param doctorEntity
     */
    void insertLog (DoctorLogEntity logEntity,DoctorEntity doctorEntity);

    /**
     * 追加日志
     * @param logEntity
     * @param doctorEntity
     */
    void additionalLog (DoctorLogEntity logEntity,DoctorEntity doctorEntity);

    /**
     * 提交草稿箱日志
     * @param logEntity
     * @param doctorEntity
     */
    void submitLog (DoctorLogTempEntity logEntity, DoctorEntity doctorEntity);
}
