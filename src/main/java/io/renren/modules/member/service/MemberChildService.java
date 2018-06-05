package io.renren.modules.member.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.member.entity.MemberChildEntity;

import java.util.Map;

/**
 * 副卡关联表
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
public interface MemberChildService extends IService<MemberChildEntity> {

    PageUtils queryPage(Map<String, Object> params);
    void  save(MemberChildEntity childEntity);
}

