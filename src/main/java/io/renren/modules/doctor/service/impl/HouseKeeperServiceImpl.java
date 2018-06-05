package io.renren.modules.doctor.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.app.form.DoctorForm;
import io.renren.modules.app.form.HouseKeeperForm;
import io.renren.modules.doctor.dao.CommunityDao;
import io.renren.modules.doctor.dao.DoctorDao;
import io.renren.modules.doctor.dao.EnterpriseDao;
import io.renren.modules.doctor.dao.HouseKeeperDao;
import io.renren.modules.doctor.entity.CommunityEntity;
import io.renren.modules.doctor.entity.DoctorEntity;
import io.renren.modules.doctor.entity.EnterpriseEntity;
import io.renren.modules.doctor.entity.HouseKeeperEntity;
import io.renren.modules.doctor.service.DoctorService;
import io.renren.modules.doctor.service.HouseKeeperService;
import io.renren.modules.user.dao.UserDao;
import io.renren.modules.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("houseKeeperService")
public class HouseKeeperServiceImpl extends BaseServiceImpl<HouseKeeperDao, HouseKeeperEntity> implements HouseKeeperService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CommunityDao communityDao;
    @Autowired
    private EnterpriseDao enterpriseDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<HouseKeeperEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        Page<HouseKeeperEntity> page = this.selectPage(
                new Query<HouseKeeperEntity>(params).getPage(),
                wrapper
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(HouseKeeperForm form) {
        Integer userId = this.saveUser(form.getMobile(), form.getUsername(), form.getPassword());
        this.saveHouseKeeper(form, userId);
    }

    private void saveHouseKeeper(HouseKeeperForm form, Integer userId) {
        HouseKeeperEntity entity = new HouseKeeperEntity();

        if (form.getType() == Constant.HouseKeeperType.is_community_house_keeper.getValue()) {
            CommunityEntity communityEntity = communityDao.selectById(form.getCommunityId());
            entity.setCommunityName(communityEntity.getName());
            entity.setCommunityId(form.getCommunityId());
            entity.setEnterpriseId(0);
            entity.setEnterpriseName("");
        } else {
            EnterpriseEntity enterpriseEntity = enterpriseDao.selectById(form.getEnterpriseId());
            entity.setEnterpriseName(enterpriseEntity.getName());
            entity.setEnterpriseId(enterpriseEntity.getId());
            entity.setCommunityId(0);
            entity.setCommunityName("");
        }
        entity.setBirthday(form.getBirthday());
        entity.setSn(form.getSn());
        entity.setWorkTime(form.getWorkTime());
        entity.setType(form.getType());
        entity.setDesc(form.getDesc());
        entity.setMobile(form.getMobile());
        entity.setAvatar(form.getAvatar());
        entity.setName(form.getRealName());
        entity.setSex(form.getSex());
        entity.setUserId(userId);
        this.insert(entity);
    }


    @Override
    public void update(HouseKeeperForm form) {
        HouseKeeperEntity houseKeeperEntity = new HouseKeeperEntity();
        if (form.getType() == Constant.DoctorType.is_community_doctor.getValue()) {
            CommunityEntity communityEntity = communityDao.selectById(form.getCommunityId());
            houseKeeperEntity.setCommunityName(communityEntity.getName());
            houseKeeperEntity.setCommunityId(communityEntity.getId());
            houseKeeperEntity.setEnterpriseId(0);
            houseKeeperEntity.setEnterpriseName(" ");
        } else {
            EnterpriseEntity entity = enterpriseDao.selectById(form.getEnterpriseId());
            houseKeeperEntity.setEnterpriseId(entity.getId());
            houseKeeperEntity.setEnterpriseName(entity.getName());
            houseKeeperEntity.setCommunityId(0);
            houseKeeperEntity.setCommunityName(" ");
        }
        houseKeeperEntity.setUserId(form.getUserId());
        houseKeeperEntity.setSex(form.getSex());
        houseKeeperEntity.setName(form.getRealName());
        houseKeeperEntity.setAvatar(form.getAvatar());
        houseKeeperEntity.setDesc(form.getDesc());
        houseKeeperEntity.setType(form.getType());

        houseKeeperEntity.setWorkTime(form.getWorkTime());
        houseKeeperEntity.setBirthday(form.getBirthday());
        houseKeeperEntity.setId(form.getHouseKeeperId());
        houseKeeperEntity.setGmtModified(new Date());
        this.updateById(houseKeeperEntity);
    }

    @Override
    public HouseKeeperForm info(Integer id) {
        HouseKeeperForm form = new HouseKeeperForm();
        HouseKeeperEntity entity = this.selectById(id);
        form.setBirthday(entity.getBirthday());
        form.setWorkTime(entity.getWorkTime());
        form.setCommunityId(entity.getCommunityId());
        form.setCommunityName(entity.getCommunityName());
        form.setEnterpriseId(entity.getEnterpriseId());
        form.setEnterpriseName(entity.getEnterpriseName());
        form.setDesc(entity.getDesc());
        form.setAvatar(entity.getAvatar());
        form.setSex(entity.getSex());
        form.setRealName(entity.getName());
        form.setHouseKeeperId(entity.getId());
        UserEntity user = userDao.selectById(entity.getUserId());
        form.setUsername(user.getUsername());
        form.setPassword(user.getPassword());
        form.setUserId(user.getUserId());
        form.setMobile(user.getMobile());
        form.setType(entity.getType());
        form.setSn(entity.getSn());

        return form;
    }

    @Override
    public void deleteHouseKeeper(List<Integer> ids) {
        Wrapper<HouseKeeperEntity> wrapper = new EntityWrapper<>();
        wrapper.in("id", ids);
        HouseKeeperEntity entity = new HouseKeeperEntity();
        entity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        this.update(entity, wrapper);

        Wrapper<HouseKeeperEntity> houseKeeperEntityWrapper = new EntityWrapper<>();
        houseKeeperEntityWrapper.in("id", ids).setSqlSelect("user_id as userId");
        List<HouseKeeperEntity> doctorEntities = this.selectList(houseKeeperEntityWrapper);
        List<Integer> uids = doctorEntities.stream()
                .map(HouseKeeperEntity::getUserId)
                .collect(Collectors.toList());

        Wrapper<UserEntity> userWrapper = new EntityWrapper<>();
        userWrapper.in("user_id", uids);

        UserEntity userEntity = new UserEntity();
        userEntity.setIsDeleted(Constant.DeleteType.is_deleted.getValue());
        userDao.update(userEntity, userWrapper);
    }
}
