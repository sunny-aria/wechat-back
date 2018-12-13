package com.wljc.wechatback.service;

import com.wljc.wechatback.ConfigConstant.CommonConstant;
import com.wljc.wechatback.model.Message;

/**
 * 功能描述：业务处理
 *
 * @Auther: hd
 * @Date: 2018/12/13
 */
public interface MessageService {
    /**
     * 处理方法定义
     * @param reqMsg
     * @return
     */
    String handler(Message reqMsg);


    default Message defaultHandler(Message reqMsg){
        Message out = new Message();
        out.setToUserName(reqMsg.getFromUserName());
        out.setFromUserName(reqMsg.getToUserName());
        out.setCreateTime(reqMsg.getCreateTime());
        out.setMsgType(CommonConstant.MSG_TYPE_TEXT);
        out.setContent("亲，感谢您的支持！\n" +
                "xx堂药店 \n" +
                "送药时间：9:00-17:00 \n" +
                "送药电话：13688665588 \n" +
                "地址：三河市燕郊开发区xx东大街188号\n" +
                "\n" +
                "欢迎来电或进店咨询！^_^");
        //默认回复文本消息，可以改成图文消息
        return out;
    }
}
