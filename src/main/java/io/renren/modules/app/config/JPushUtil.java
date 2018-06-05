package io.renren.modules.app.config;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.AliasDeviceListResult;
import cn.jpush.api.device.TagListResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import io.renren.modules.app.utils.JPushPlayground;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

import static cn.jpush.api.push.model.notification.PlatformNotification.ALERT;

@ConfigurationProperties(prefix = "jpush")
@Component
public class JPushUtil {

    private static final Logger log = LoggerFactory.getLogger(JPushUtil.class);


    /**
     * 极光推送方法(采用java SDK)
     *
     * @param alias
     * @param alert
     * @return PushResult
     */
    public static PushResult push(String alias, String alert, String masterSecret, String appKey,String key,String value) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
        try {
            return jpushClient.sendPush(JPushUtil.buildPushObjectAndroidExtrasAndMessage(alias,alert,key,value));
        } catch (APIConnectionException e) {
            log.error("连接失败,请稍后再试!", e);
            return null;
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            log.info("Msg ID: " + e.getMsgId());
            return null;

        }
    }

    /**
     * 极光推送方法(采用java SDK)
     *
     * @param alias
     * @param alert
     * @return PushResult
     */
    public static PushResult push(List<String> alias, String alert, String masterSecret, String appKey,String key,String value) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
        try {
            return jpushClient.sendPush(JPushUtil.buildPushObjectAndroidExtrasAndMessage(alias,alert,key,value));
        } catch (APIConnectionException e) {
            log.error("连接失败,请稍后再试!", e);
            return null;
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            log.info("Msg ID: " + e.getMsgId());
            return null;
        }
    }

    public static  PushResult pushAll(String alert,  String masterSecret, String appKey,String key,String value){
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
        try {
            return jpushClient.sendPush(JPushUtil.buildPushObjectAndroidAll(alert,key,value));
        } catch (APIConnectionException e) {
            log.error("连接失败,请稍后再试!", e);
            return null;
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            log.info("Msg ID: " + e.getMsgId());
            return null;
        }
    }


    /**
     * 信息标准类
     * @param alias
     * @param message
     * @return
     */
    public static PushPayload buildPushObjectAndroidExtrasAndMessage(String alias, String message,String extraKey,String extraValue) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(message)
                                .addExtra(extraKey, extraValue)
                                .build())
                        .build())
                .setMessage(Message.content(message))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    public static PushPayload buildPushObjectAndroidExtrasAndMessage(List<String> alias, String message,String extraKey,String extraValue) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(message)
                                .addExtra(extraKey, extraValue)
                                .build())
                        .build())
                .setMessage(Message.content(message))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    public static PushPayload buildPushObjectAndroidAll( String message,String extraKey,String extraValue) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(message)
                                .addExtra(extraKey, extraValue)
                                .build())
                        .build())
                .setMessage(Message.content(message))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }




}
