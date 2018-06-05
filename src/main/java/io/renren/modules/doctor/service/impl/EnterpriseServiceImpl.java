package io.renren.modules.doctor.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import io.renren.common.utils.Constant;
import io.renren.modules.doctor.dao.EnterpriseDao;
import io.renren.modules.doctor.entity.EnterpriseEntity;
import io.renren.modules.doctor.service.EnterpriseService;
import io.renren.modules.user.dao.UserDao;
import io.renren.modules.user.entity.UserEntity;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
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
@Service("enterpriseService")
public class EnterpriseServiceImpl extends BaseServiceImpl<EnterpriseDao, EnterpriseEntity> implements EnterpriseService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Wrapper<EnterpriseEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", Constant.DeleteType.un_deleted.getValue());
        if (StringUtils.isNotBlank((String) params.get("key"))) {
            wrapper.eq("enterprise_code", params.get("key")).or().like("name", (String) params.get("key"));
        }
        Page<EnterpriseEntity> page = this.selectPage(
                new Query<EnterpriseEntity>(params).getPage(),
                wrapper
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(EnterpriseEntity enterpriseEntity) {
        String mobile = enterpriseEntity.getEnterpriseManagerMobile();
        /*添加普通用户表*/
        Integer userId = this.saveUser(mobile);
        /*添加到社区*/
        this.saveEnterprise(userId, enterpriseEntity);
    }


    private void saveEnterprise(Integer userId, EnterpriseEntity enterpriseEntity) {
        enterpriseEntity.setEnterpriseManagerId(userId);
        enterpriseEntity.setStatus(Constant.CommunityStatus.is_un_active.getValue());
        this.insert(enterpriseEntity);
    }
}
