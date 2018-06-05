package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.JPushSet;
import io.renren.modules.sys.entity.SysCaptchaEntity;

import java.util.Map;

public interface JPushService  extends IService<JPushSet> {
    JPushSet getDoctorJPushConfig();
    JPushSet getMemberJPushConfig();
    JPushSet getHouseKeeperConfig();
    JPushSet getChildJPushConfig();
    JPushSet getManagerJPushConfig();
    PageUtils queryPage(Map<String, Object> params);
}
