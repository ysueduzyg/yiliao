package io.renren.modules.member.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员卡
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-05 16:40:37
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

