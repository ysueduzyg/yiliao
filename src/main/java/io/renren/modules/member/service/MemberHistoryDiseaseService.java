package io.renren.modules.member.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.member.entity.MemberHistoryDiseaseEntity;

import java.util.Map;

/**
 * 过往病史
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:32
 */
public interface MemberHistoryDiseaseService extends IService<MemberHistoryDiseaseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

