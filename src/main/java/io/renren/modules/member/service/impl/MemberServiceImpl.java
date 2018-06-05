package io.renren.modules.member.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.app.form.MemberForm;
import io.renren.modules.app.form.MemberListForm;
import io.renren.modules.doctor.entity.*;
import io.renren.modules.doctor.service.*;
import io.renren.modules.doctor.service.impl.BaseServiceImpl;
import io.renren.modules.member.dao.MemberDao;
import io.renren.modules.member.entity.*;
import io.renren.modules.member.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.transaction.annotation.Transactional;


@Service("memberService")
public class MemberServiceImpl extends BaseServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    DoctorService doctorService;
    @Autowired
    HouseKeeperService houseKeeperService;
    @Autowired
    CommunityService communityService;
    @Autowired
    EnterpriseService enterpriseService;
    @Autowired
    MemberChildService memberChildService;
    @Autowired
    MemberLevelService levelService;
    @Autowired
    MemberCommentsService commentsService;
    @Autowired
    MemberTaskService taskService;
    @Autowired
    ServiceOrderRecordService recordService;
    @Autowired
    ServiceOrderService serviceOrderService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        Wrapper<MemberEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        String doctorIdStr = (String) params.get("doctorId");
        String houseKeeperIdStr = (String) params.get("houseKeeperId");
        String communityIdStr = (String) params.get("communityId");
        String enterpriseIdStr = (String) params.get("enterpriseId");
        String key = (String) params.get("name");
        String sn = (String) params.get("sn");
        Integer communityId = 0;
        Integer doctorId = 0;
        Integer enterpriseId = 0;
        Integer houseKeeperId = 0;
        if (StringUtils.isNotBlank(doctorIdStr)) {
            doctorId = Integer.valueOf(doctorIdStr);
        }
        if (StringUtils.isNotBlank(enterpriseIdStr)) {
            enterpriseId = Integer.valueOf(enterpriseIdStr);
        }
        if (StringUtils.isNotBlank(communityIdStr)) {
            communityId = Integer.valueOf(communityIdStr);
        }

        if (StringUtils.isNotBlank(houseKeeperIdStr)) {
            houseKeeperId = Integer.valueOf(houseKeeperIdStr);
        }
        if (communityId > 0) {
            wrapper.eq("community_id", communityId);
        }
        if (enterpriseId > 0) {
            wrapper.eq("enterprise_id", enterpriseId);
        }
        if (doctorId > 0) {
            wrapper.eq("doctor_id", doctorId);
        }
        if (houseKeeperId > 0) {
            wrapper.eq("house_keeper_id", houseKeeperId);
        }


        if (StringUtils.isNotBlank(sn)) {
            wrapper.eq("sn", sn);
        }
        if (StringUtils.isNotBlank(key)) {
            wrapper.like("real_name", key);
        }

        Page<MemberEntity> page = this.selectPage(
                new Query<MemberEntity>(params).getPage(),
                wrapper
        );
        List<MemberEntity> list = page.getRecords();
        Wrapper<MemberLevelEntity> levelWrapper = new EntityWrapper<>();
        levelWrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        List<MemberLevelEntity> levelList = levelService.selectList(levelWrapper);
        List<MemberListForm> listForms = new ArrayList<>();
        for (MemberEntity entity : list) {
            MemberListForm memberListForm = new MemberListForm();
            memberListForm.setAddress(entity.getAddress());
            memberListForm.setMobile(entity.getMobile());
            memberListForm.setBirthday(entity.getBirthday());
            memberListForm.setBirthdayType(entity.getBirthdayType());
            memberListForm.setBlood(entity.getBlood());
            memberListForm.setBloodPressure(entity.getBloodPressure());
            memberListForm.setBloodSugar(entity.getBloodSugar());
            memberListForm.setCommunityId(entity.getCommunityId());
            memberListForm.setDoctorId(entity.getDoctorId());
            memberListForm.setDueTime(entity.getDueTime());
            memberListForm.setEducation(entity.getEducation());
            memberListForm.setEnterpriseId(entity.getEnterpriseId());
            memberListForm.setHeight(entity.getHeight());
            memberListForm.setId(entity.getId());
            memberListForm.setIdCard(entity.getIdCard());
            memberListForm.setIncome(entity.getIncome());
            for (int g = 0; g < levelList.size(); g++) {
                MemberLevelEntity levelEntity = levelList.get(g);
                if (Integer.valueOf(entity.getLevel()).equals(levelEntity.getId())) {
                    memberListForm.setLevel(levelEntity.getName());
                }
            }
            memberListForm.setNational(entity.getNational());
            memberListForm.setProfessional(entity.getProfessional());
            memberListForm.setRealName(entity.getRealName());
            memberListForm.setSex(entity.getSex());
            memberListForm.setUserId(entity.getUserId());
            memberListForm.setWeight(entity.getWeight());
            memberListForm.setWorkAt(entity.getWorkAt());
            memberListForm.setIsCommunity(entity.getIsCommunity());
            memberListForm.setIsEnterprise(entity.getIsEnterprise());
            memberListForm.setAvatar(entity.getAvatar());

            if (entity.getCommunityId() != null && entity.getCommunityId() > 0) {
                CommunityEntity communityEntity = communityService.selectById(entity.getCommunityId());
                if (communityEntity != null) {
                    memberListForm.setCommunityName(communityEntity.getName());
                }
            }
            if (entity.getEnterpriseId() != null) {
                EnterpriseEntity enterpriseEntity = enterpriseService.selectById(entity.getEnterpriseId());
                if (enterpriseEntity != null) {
                    memberListForm.setEnterpriseName(enterpriseEntity.getName());
                }
            }
            if (entity.getDoctorId() != null) {
                DoctorEntity doctorEntity = doctorService.selectById(entity.getDoctorId());
                if (doctorEntity != null) {
                    memberListForm.setDoctorName(doctorEntity.getName());
                }
            }
            if (entity.getHouseKeeperId() != null) {
                HouseKeeperEntity houseKeeperEntity = houseKeeperService.selectById(entity.getHouseKeeperId());
                if (houseKeeperEntity != null) {
                    memberListForm.setHouseKeeperName(houseKeeperEntity.getName());
                }
            }

            listForms.add(memberListForm);
        }
        Page<MemberListForm> formPage = new Page<>();
        formPage.setRecords(listForms);
        formPage.setCurrent(page.getCurrent());
        formPage.setTotal(page.getTotal());
        formPage.setSize(page.getSize());

        return new PageUtils(formPage);
    }

    @Override
    public PageUtils queryByDoctorPage(Map<String, Object> params, DoctorEntity doctorEntity) {
        return null;
    }

    @Override
    public PageUtils queryChildPage(Map<String, Object> params) {

        Wrapper<MemberChildEntity> wrapper = new EntityWrapper<>();
        String id = (String) params.get("id");
        wrapper.eq("member_id", Integer.valueOf(id)).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        Page<MemberChildEntity> page = memberChildService.selectPage(
                new Query<MemberChildEntity>(params).getPage(),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional
    public void save(MemberForm memberForm) {
        Integer userId = this.saveUser(memberForm.getMobile());
        memberForm.setUserId(userId);
        this.saveMember(memberForm);
    }

    @Override
    @Transactional
    public void updateComment(MemberEntity memberEntity, MemberCommentsEntity memberCommentsEntity) {
        MemberCommentsEntity commentsEntity = commentsService.selectById(memberCommentsEntity.getId());
        if (commentsEntity.getStatus() == Constant.CommentStatus.is_not_comment.getValue()) {
            ///标记task任务完成
            commentsEntity.setSpeed(memberCommentsEntity.getSpeed());
            commentsEntity.setAttitude(memberCommentsEntity.getAttitude());
            commentsEntity.setEffect(memberCommentsEntity.getEffect());
            commentsEntity.setStatus(Constant.CommentStatus.is_commented.getValue());
            commentsService.updateById(commentsEntity);

            MemberTaskEntity taskEntity = taskService.selectById(commentsEntity.getTaskId());
            taskEntity.setStatus(Constant.TaskStatus.is_close.getValue());
            taskService.updateById(taskEntity);

            Wrapper<ServiceOrderEntity> wrapper = new EntityWrapper<>();
            wrapper.eq("receive_id", commentsEntity.getFocusId()).eq("task_id", commentsEntity.getTaskId()).eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
            ServiceOrderEntity serviceOrderEntity = serviceOrderService.selectOne(wrapper);
            serviceOrderEntity.setStatus(Constant.ServiceOrderStatus.is_order_finish.getValue());

            serviceOrderService.updateById(serviceOrderEntity);

            ServiceOrderRecordEntity recordEntity = new ServiceOrderRecordEntity();
            recordEntity.setReceiveId(serviceOrderEntity.getReceiveId());
            recordEntity.setMemberId(serviceOrderEntity.getMemberId());
            recordEntity.setMemberSn(serviceOrderEntity.getMemberSn());
            recordEntity.setTaskSn(taskEntity.getTaskSn());
            recordEntity.setRemark(Constant.ServiceOrderRemark.is_order_finish.getValue());
            recordEntity.setTaskId(taskEntity.getId());
            recordEntity.setType(commentsEntity.getTaskType());
            recordEntity.setStatus(Constant.ServiceOrderStatus.is_order_finish.getValue());
            recordEntity.setOptionName(memberEntity.getRealName());
            recordService.insert(recordEntity);


        } else {
            commentsEntity.setSpeed(memberCommentsEntity.getSpeed());
            commentsEntity.setAttitude(memberCommentsEntity.getAttitude());
            commentsEntity.setEffect(memberCommentsEntity.getEffect());
            commentsService.updateById(commentsEntity);
        }
    }

    public void saveMember(MemberForm memberForm) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setAddress(memberForm.getAddress());
        memberEntity.setMobile(memberForm.getMobile());
        memberEntity.setAvatar(memberForm.getAvatar());
        memberEntity.setBirthday(memberForm.getBirthday());
        memberEntity.setBirthdayType(memberForm.getBirthdayType());
        memberEntity.setBlood(memberForm.getBlood());
        memberEntity.setBloodSugar(memberForm.getBloodSugar());
        memberEntity.setBloodPressure(memberForm.getBloodPressure());
        memberEntity.setCommunityId(memberForm.getCommunityId());
        memberEntity.setDoctorId(memberForm.getDoctorId());
        memberEntity.setDueTime(memberForm.getDueTime());
        memberEntity.setEducation(memberForm.getEducation());
        memberEntity.setEnterpriseId(memberForm.getEnterpriseId());
        memberEntity.setHeight(memberForm.getHeight());
        memberEntity.setIsEnterprise(memberForm.getIsEnterprise());
        memberEntity.setIsCommunity(memberForm.getIsCommunity());
        memberEntity.setIncome(memberForm.getIncome());
        memberEntity.setIdCard(memberForm.getIdCard());
        memberEntity.setLevel(memberForm.getLevel());
        memberEntity.setNational(memberForm.getNational());
        memberEntity.setProfessional(memberForm.getProfessional());
        memberEntity.setRealName(memberForm.getRealName());
        memberEntity.setSn(memberForm.getSn());
        memberEntity.setUserId(memberForm.getUserId());
        memberEntity.setWeight(memberForm.getWeight());
        memberEntity.setWorkAt(memberForm.getWorkAt());
        memberEntity.setSex(memberForm.getSex());
        memberEntity.setMarriage(memberForm.getMarriage());
        memberEntity.setHouseKeeperId(memberForm.getHouseKeeperId());
        this.insert(memberEntity);
    }
}
