package com.wljc.wechatback.ConfigConstant;

/**
 * 功能描述： 公共静态变量
 *
 * @Auther: hd
 * @Date: 2018/12/13
 */
public interface CommonConstant {

    String TOKEN = "hd";
    // 事件类型
    String MSG_TYPE_TEXT = "text";
    String MSG_TYPE_IMAGE = "image";
    String MSG_TYPE_LOCATION = "location";
    String MSG_TYPE_LINK = "link";
    String MSG_TYPE_EVENT = "event";
    String MSG_TYPE_MUSIC = "music";
    String MSG_TYPE_NEWS = "news";
    String MSG_TYPE_VOICE = "voice";

    // 事件字典
    String MSG_EVENT_UNSUBSCRIBE = "unsubscribe";
    String MSG_EVENT_SUBSCRIBE = "subscribe";
    String MSG_EVENT_SCAN = "SCAN";
    String MSG_EVENT_CLICK = "CLICK";
    String MSG_EVENT_LOCATION = "LOCATION";
    String MSG_EVENT_TEMPLATE = "TEMPLATESENDJOBFINISH";
    String MSG_EVENT_CARD_PASS = "card_pass_check";
    String MSG_EVENT_CARD_NO_PASS = "card_not_pass_check";
    String MSG_EVENT_GET_CARD = "user_get_card";
    String MSG_EVENT_DEL_CARD = "user_del_card";
    String MSG_EVENT_VIEW_CARD = "user_view_card";

    String MSG_CUSTOMER_SERVICE = "transfer_customer_service";
}
