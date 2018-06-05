package io.renren.modules.member.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.member.entity.MemberTagEntity;

import java.util.Map;

/**
 * @author xpf
 */
public interface MemberTagService  extends IService<MemberTagEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
