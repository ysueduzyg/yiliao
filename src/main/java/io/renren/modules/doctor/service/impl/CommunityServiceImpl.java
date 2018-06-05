package io.renren.modules.doctor.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.doctor.dao.CommunityDao;
import io.renren.modules.doctor.entity.CommunityEntity;
import io.renren.modules.doctor.service.CommunityService;
import io.renren.modules.user.dao.UserDao;
import io.renren.modules.user.entity.UserEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xpf
 */
@Service("CommunityService")
public class CommunityServiceImpl extends BaseServiceImpl<CommunityDao, CommunityEntity> implements CommunityService {


    private Logger logger = LoggerFactory.getLogger(CommunityServiceImpl.class);

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<CommunityEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        if (StringUtils.isNotBlank((String) params.get("key"))) {
            wrapper.eq("community_code", params.get("key")).or().like("name", (String) params.get("key"));
        }
        Page<CommunityEntity> page = this.selectPage(
                new Query<CommunityEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(CommunityEntity communityEntity) {
        String mobile = communityEntity.getCommunityManagerMobile();
        /*添加普通用户表*/
        Integer userId = this.saveUser(mobile);
        /*添加到社区*/
        this.saveCommunity(userId, communityEntity);
    }

    private void saveCommunity(Integer userId, CommunityEntity community) {
        community.setCommunityManagerId(userId);
        community.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        community.setStatus(Constant.CommunityStatus.is_un_active.getValue());
        this.insert(community);
    }

}
