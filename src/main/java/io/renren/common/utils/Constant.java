package io.renren.common.utils;

import io.swagger.models.auth.In;
import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 常量
 *
 * @author xpf
 * @email xiaodown2013@163.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;

    /**
     * 菜单类型
     *
     * @author xpf
     * @email xiaodown2013@163.com
     * @date 2016年11月15日 下午1:24:29
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     *
     * @author xpf
     * @email xiaodown2013@163.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
        NORMAL(0),
        /**
         * 暂停
         */
        PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public enum DeleteType {

        is_deleted(1),

        un_deleted(0);

        private int value;

        DeleteType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum TaskDoctorType {
        is_default(1),
        is_redirect(2);
        private int value;

        TaskDoctorType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 任务类型
     */
    public enum TaskType {
        is_sos(1),
        is_message(2),
        is_doctor_received(3),
        is_call_housekeeper(4),
        is_housekeeper_received(5);
        private int value;

        TaskType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum TaskListType {
        is_child(1),
        is_manager(2);
        private int value;

        TaskListType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum TaskListStatus {
        is_get_order(1),
        is_not_get_order(0);
        private int value;

        TaskListStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public enum SosUsedType {
        is_can_use(0),
        is_used(1);
        private int value;

        SosUsedType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public  enum  LifeUsedType {
        is_can_use(0),
        is_used(1);
        private int value;

        LifeUsedType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    public enum TaskStatus {
        is_received(1),
        is_not_receive(0),
        is_close(-1);
        private int value;

        TaskStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 任务类型
     */
    public enum LevelType {
        is_sos(1),
        is_not_sos(0);
        private int value;

        LevelType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum LevelLifeType {
        is_life(1),
        is_not_life(0);
        private int value;

        LevelLifeType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum SexType {
        is_male(1),
        is_female(2);

        private int value;

        SexType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public enum UserStatus {
        is_locked(2),
        is_default(1);
        private int value;

        UserStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum CommunityStatus {
        is_un_active(0),
        is_active(1);
        private int value;

        CommunityStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum DoctorType {
        is_enterprise_doctor(2),
        is_community_doctor(1);
        private int value;

        DoctorType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum HouseKeeperType {
        is_enterprise_house_keeper(2),
        is_community_house_keeper(1);
        private int value;

        HouseKeeperType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public enum CommentType {
        is_member(1),
        is_child(2);
        private Integer value;

        CommentType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum CommentFocusType {
        is_doctor(1),
        is_houseKeeper(2);
        private Integer value;

        CommentFocusType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum CommentStatus {
        is_not_comment(0),
        is_commented(1);
        private Integer value;

        CommentStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum ServiceOrderStatus {
        is_not_pick_up_order(0),
        is_pick_up_order(1),
        is_doctor_disposal(2),
        is_doctor_finish(3),
        is_order_finish(4),
        is_order_close(-1);
        private Integer value;

        ServiceOrderStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum ServiceOrderRemark {
        is_not_pick_up_order("发布求助"),
        is_pick_up_order("接受求助"),
        is_doctor_disposal("处理完成"),
        is_doctor_finish("提交日志"),
        is_order_finish("评价完成"),
        is_doctor_additional("追加日志");
        private String value;

        ServiceOrderRemark(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum ActiveCommentType {
        is_active(1),
        is_not_active(0);
        private Integer value;

        ActiveCommentType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    public enum JPushType {
        is_member(1),
        is_doctor(2),
        is_child(3),
        is_manager(4),
        is_housekeeper(5);
        private Integer value;

        JPushType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }


    }


    public enum msgRemind {
        is_strong(1),
        is_weak(2);
        private Integer value;

        msgRemind(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum ContentHtmlStatus {
        is_publish(1),
        un_publish(0);
        private Integer value;

        ContentHtmlStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static String SOS = "SOS_";
    public static String MSG = "MSG_";
    public static String HK = "HK_";
}
