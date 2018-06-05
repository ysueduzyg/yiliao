package io.renren.modules.member.service.impl;

import io.renren.common.utils.Constant;
import io.renren.modules.doctor.service.impl.BaseServiceImpl;
import io.renren.modules.member.dao.MemberChildDao;
import io.renren.modules.member.entity.MemberChildEntity;
import io.renren.modules.member.service.MemberChildService;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;


@Service("memberChildService")
public class MemberChildServiceImpl extends BaseServiceImpl<MemberChildDao, MemberChildEntity> implements MemberChildService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<MemberChildEntity> page = this.selectPage(
                new Query<MemberChildEntity>(params).getPage(),
                new EntityWrapper<MemberChildEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void save(MemberChildEntity childEntity) {
        Integer userId = this.saveUser(childEntity.getMobile());
        this.saveChild(childEntity, userId);
    }

    void saveChild(MemberChildEntity childEntity, Integer userId) {
        childEntity.setIsDeleted(Constant.DeleteType.un_deleted.getValue());
        childEntity.setUserId(userId);
        this.insert(childEntity);
    }
}
