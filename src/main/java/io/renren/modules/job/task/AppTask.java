package io.renren.modules.job.task;

import com.alibaba.fastjson.JSON;
import io.renren.common.utils.Constant;
import io.renren.modules.app.config.JPushUtil;
import io.renren.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("appTask")
public class AppTask {


    void jPushManager(String json){
        Map<String,String> map = JSON.parseObject(json,Map.class);
        String alias =  map.get("alias");
        String alert =  map.get("alert");
        String memberId =  map.get("memberId");
        String doctorId =  map.get("doctorId");
        String appKey =  map.get("appKey");
        String secret =  map.get("secret");
        Map<String,String> obj = new HashMap<>();
        obj.put("memberId",memberId);
        obj.put("doctorId",doctorId);
        obj.put("type",String.valueOf(Constant.msgRemind.is_strong.getValue()));
        JPushUtil.push(alias,alert,appKey,secret,"json",JSON.toJSONString(obj));
    }

}
