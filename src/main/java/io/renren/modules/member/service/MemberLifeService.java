package io.renren.modules.member.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.member.entity.MemberLifeEntity;

import java.util.Map;

/**
 * 会员生活爱好
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
public interface MemberLifeService extends IService<MemberLifeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

