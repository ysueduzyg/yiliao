package io.renren.modules.member.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.member.entity.MemberCommentsEntity;

import java.util.Map;

/**
 * 
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
public interface MemberCommentsService extends IService<MemberCommentsEntity> {

    /**
     * 所有的评价记录
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);

    /**
     * 查询与某个医生相关的所有评价记录
     * @param params
     * @return
     */
    PageUtils queryDoctorPage(Map<String, Object> params);

    /**
     * 会员端查询自己的评价记录
     * @param params
     * @return
     */
    PageUtils queryMemberPage(Map<String, Object> params);


    /**
     * 查询与某个管家相关的所有评价记录
     * @param params
     * @return
     */
    PageUtils queryHouseKeeperPage(Map<String, Object> params);
}

