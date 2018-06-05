package io.renren.modules.app.utils;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;
import com.alibaba.fastjson.JSON;

import java.util.List;
import java.util.Map;

public class JPushPlayground {
    /**
     * 所有平台，所有设备，内容为 content 的通知
     *
     * @param content
     * @return
     */
    public static PushPayload buildPushObject_all_all_alert(String content) {
        return PushPayload.alertAll(content);
    }

    /**
     * 根据 设备终端ID 推送消息
     *
     * @param regesterIds 设备终端ID集合
     * @param content     内容
     * @return
     */
    public static PushPayload buildPushObject_all_all_regesterIds(List<String> regesterIds, String content) {
        Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).build());
        return PushPayload
                .newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(regesterIds))
                .setNotification(Notification.alert(content))
                .setMessage(Message.content(content))
                .build();
//        return PushPayload.newBuilder()
//                .setPlatform(Platform.android_ios())
//                .setAudience(Audience.registrationId(regesterIds))
//                .setNotification(Notification.alert(content))
//                .setMessage(Message.newBuilder()
//                        .setMsgContent(content)
//                        .addExtra("from", "JPush")
//                        .build())
//                .build();

    }

    /**
     * 根据 设备终端ID 推送消息
     *
     * @param regesterId 设备终端ID
     * @param content     内容
     * @return
     */
    public static PushPayload buildPushObject_all_all_regesterId(String regesterId, String content) {
        Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder().setAlert(content).build());
//        return PushPayload
//                .newBuilder()
//                .setPlatform(Platform.all())
//                .setAudience(Audience.registrationId(regesterId))
//                .setNotification(Notification.alert(content))
//                .setMessage(Message.content(content))
//                .build();
        Map<String,String> map = JSON.parseObject(content,Map.class);
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId(regesterId))
                .setNotification(Notification.alert(map.get("content")))
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("from", map.get("content"))
                        .build())
                .build();

    }

    /**
     * 所有平台，推送目标是别名为 "alias"，通知内容为 TEST
     *
     * @param alias
     * @param content
     * @return
     */
    public static PushPayload buildPushObject_all_alias_alert(String alias, String content) {
        return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(content)).build();
    }
}
