package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.MsgContent;
import io.renren.modules.sys.entity.SysLogEntity;

import java.util.Map;

/**
 * @author xpf
 */
public interface MsgContentService extends IService<MsgContent> {
    /**
     * 获取求助推送
     * @return
     */
    String getSosMsg();

    /**
     * 获取管家推送信息
     * @return
     */
    String getHousekeeperMsg();

    /**
     * 获取咨询推送信息
     * @return
     */
    String getMessageMsg();

    /**
     * 获取接受求助信息
     * @return
     */
    String getReceiveMsg();

    /**
     * 获取接受生活服务信息
     * @return
     */
    String getReceiveHouseKeeperMsg();

    /**
     * 信息列表
     * @param params
     * @return
     */
    PageUtils queryPage(Map<String, Object> params);
}
