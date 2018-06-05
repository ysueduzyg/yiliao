package io.renren.modules.doctor.service.impl;

import com.baomidou.mybatisplus.entity.Column;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.app.form.DoctorForm;
import io.renren.modules.doctor.dao.CommunityDao;
import io.renren.modules.doctor.dao.DoctorDao;
import io.renren.modules.doctor.dao.EnterpriseDao;
import io.renren.modules.doctor.entity.CommunityEntity;
import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.doctor.entity.DoctorLogEntity;
import io.renren.modules.doctor.entity.EnterpriseEntity;
import io.renren.modules.doctor.service.DoctorService;
import io.renren.modules.user.dao.UserDao;
import io.renren.modules.user.entity.UserEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.transaction.annotation.Transactional;

import javax.print.Doc;


@Service("doctorService")
public class DoctorServiceImpl extends BaseServiceImpl<DoctorDao, DoctorEntity> implements DoctorService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommunityDao communityDao;
    @Autowired
    private EnterpriseDao enterpriseDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<DoctorEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        Page<DoctorEntity> page = this.selectPage(
                new Query<DoctorEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(DoctorForm doctorForm) {
        Integer userId = this.saveUser(doctorForm.getMobile(), doctorForm.getUsername(), doctorForm.getPassword());
        this.saveDoctor(doctorForm, userId);
    }

    private void saveDoctor(DoctorForm doctorForm, Integer userId) {
        DoctorEntity doctorEntity = new DoctorEntity();
        if (doctorForm.getType() == Constant.DoctorType.is_community_doctor.getValue()) {
            CommunityEntity communityEntity = communityDao.selectById(doctorForm.getCommunityId());
            doctorEntity.setCommunityName(communityEntity.getName());
            doctorEntity.setCommunityId(doctorForm.getCommunityId());
            doctorEntity.setEnterpriseId(0);
            doctorEntity.setEnterpriseName("");
        } else {
            EnterpriseEntity entity = enterpriseDao.selectById(doctorForm.getEnterpriseId());
            doctorEntity.setEnterpriseName(entity.getName());
            doctorEntity.setEnterpriseId(entity.getId());
            doctorEntity.setCommunityId(0);
            doctorEntity.setCommunityName("");
        }
        doctorEntity.setBirthday(doctorForm.getBirthday());
        doctorEntity.setWorkTime(doctorForm.getWorkTime());
        doctorEntity.setType(doctorForm.getType());
        doctorEntity.setDesc(doctorForm.getDesc());
        doctorEntity.setAvatar(doctorForm.getAvatar());
        doctorEntity.setName(doctorForm.getRealName());
        doctorEntity.setSex(doctorForm.getSex());
        doctorEntity.setUserId(userId);
        doctorEntity.setSn(doctorForm.getSn());
        doctorEntity.setSchool(doctorForm.getSchool());
        doctorEntity.setWorkAt(doctorForm.getWorkAt());
        doctorEntity.setMobile(doctorForm.getMobile());
        doctorEntity.setProfessional(doctorForm.getProfessional());
        doctorEntity.setJobLevel(doctorForm.getJobLevel());
        this.insert(doctorEntity);
    }


    @Override
    public void update(DoctorForm doctorForm) {
        DoctorEntity doctorEntity = new DoctorEntity();
        if (doctorForm.getType() == Constant.DoctorType.is_community_doctor.getValue()) {
            CommunityEntity communityEntity = communityDao.selectById(doctorForm.getCommunityId());
            doctorEntity.setCommunityName(communityEntity.getName());
            doctorEntity.setCommunityId(communityEntity.getId());
            doctorEntity.setEnterpriseId(0);
            doctorEntity.setEnterpriseName(" ");
        } else {
            EnterpriseEntity entity = enterpriseDao.selectById(doctorForm.getEnterpriseId());
            doctorEntity.setEnterpriseId(entity.getId());
            doctorEntity.setEnterpriseName(entity.getName());
            doctorEntity.setCommunityId(0);
            doctorEntity.setCommunityName(" ");
        }
        doctorEntity.setUserId(doctorForm.getUserId());
        doctorEntity.setSex(doctorForm.getSex());
        doctorEntity.setName(doctorForm.getRealName());
        doctorEntity.setAvatar(doctorForm.getAvatar());
        doctorEntity.setDesc(doctorForm.getDesc());
        doctorEntity.setType(doctorForm.getType());
        doctorEntity.setWorkTime(doctorForm.getWorkTime());
        doctorEntity.setBirthday(doctorForm.getBirthday());
        doctorEntity.setId(doctorForm.getDoctorId());
        doctorEntity.setSn(doctorForm.getSn());
        doctorEntity.setSchool(doctorForm.getSchool());
        doctorEntity.setWorkAt(doctorForm.getWorkAt());
        doctorEntity.setProfessional(doctorForm.getProfessional());
        doctorEntity.setJobLevel(doctorForm.getJobLevel());
        doctorEntity.setGmtModified(new Date());
        this.updateById(doctorEntity);
    }

    @Override
    public DoctorForm info(Integer id) {
        DoctorForm doctorForm = new DoctorForm();
        DoctorEntity doctorEntity = this.selectById(id);
        doctorForm.setBirthday(doctorEntity.getBirthday());
        doctorForm.setWorkTime(doctorEntity.getWorkTime());
        doctorForm.setCommunityId(doctorEntity.getCommunityId());
        doctorForm.setCommunityName(doctorEntity.getCommunityName());
        doctorForm.setEnterpriseId(doctorEntity.getEnterpriseId());
        doctorForm.setEnterpriseName(doctorEntity.getEnterpriseName());
        doctorForm.setDesc(doctorEntity.getDesc());
        doctorForm.setAvatar(doctorEntity.getAvatar());
        doctorForm.setSex(doctorEntity.getSex());
        doctorForm.setRealName(doctorEntity.getName());
        doctorForm.setDoctorId(doctorEntity.getId());
        UserEntity user = userDao.selectById(doctorEntity.getUserId());
        doctorForm.setUsername(user.getUsername());
        doctorForm.setPassword(user.getPassword());
        doctorForm.setUserId(user.getUserId());
        doctorForm.setMobile(user.getMobile());
        doctorForm.setType(doctorEntity.getType());
        doctorForm.setSn(doctorEntity.getSn());
        doctorForm.setSchool(doctorEntity.getSchool());
        doctorForm.setWorkAt(doctorEntity.getWorkAt());
        doctorForm.setProfessional(doctorEntity.getProfessional());
        doctorForm.setJobLevel(doctorEntity.getJobLevel());
        return doctorForm;
    }

    @Override
    public void deleteDoctor(List<Integer> ids) {
        Wrapper<DoctorEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", ids);
        DoctorEntity doctorEntity = new DoctorEntity();
        this.update(doctorEntity, wrapper);

        Wrapper<DoctorEntity> doctorEntityWrapper = new EntityWrapper<>();
        doctorEntityWrapper.in("id", ids).setSqlSelect("user_id as userId");
        List<DoctorEntity> doctorEntities = this.selectList(doctorEntityWrapper);
        List<Integer> uids = doctorEntities.stream()
                .map(DoctorEntity::getUserId)
                .collect(Collectors.toList());

        Wrapper<UserEntity> userWrapper = new EntityWrapper<>();
        userWrapper.in("user_id", uids);

        UserEntity userEntity = new UserEntity();
        userDao.update(userEntity, userWrapper);


    }


}
