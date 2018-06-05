package io.renren.modules.member.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.member.entity.MemberTaskEntity;

import java.util.Map;

/**
 * 会员求助或者咨询
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2018-05-03 14:39:23
 */
public interface MemberTaskService extends IService<MemberTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPageSosByDoctor (Map<String, Object> params,Integer doctorId);
    PageUtils queryPageMsgByDoctor (Map<String, Object> params,Integer doctorId);
    PageUtils queryPageMsgByHouseKeeper (Map<String, Object> params,Integer doctorId);
    void saveSos(MemberTaskEntity taskEntity,String optionName);
    void saveMsg(MemberTaskEntity taskEntity,String optionName);
    void saveHouseKeeper(MemberTaskEntity taskEntity,String optionName);
}

