package io.renren.modules.member.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.member.entity.MemberCurrentEntity;

import java.util.Map;

/**
 * 会员当前病史
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
public interface MemberCurrentService extends IService<MemberCurrentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

