package com.wljc.wechatback.service;

import static com.wljc.wechatback.ConfigConstant.CommonConstant.*;

import com.wljc.wechatback.model.Message;
import com.wljc.wechatback.util.XmlUtil;
import org.springframework.stereotype.Service;

/**
 * 功能描述：非事件消息解析
 *
 * @Auther: hd
 * @Date: 2018/12/13
 */
@Service("messageServiceTextImpl")
public class MessageServiceTextImpl implements MessageService {
    /**
     * 处理消息
     * @param reqMsg
     * @return
     */
    @Override
    public String handler(Message reqMsg) {
        String msgType=reqMsg.getMsgType();
        Message out = new Message();

        if(MSG_TYPE_TEXT.equals(msgType)){
            out = defaultHandler(reqMsg);
            //TODO 关键字回复
        }else if(MSG_TYPE_IMAGE.equals(msgType)){
            out = defaultHandler(reqMsg);
        }else if(MSG_TYPE_LOCATION.equals(msgType)){
            out = defaultHandler(reqMsg);
        }else if(MSG_TYPE_LINK.equals(msgType)){
            out = defaultHandler(reqMsg);
        }
        return XmlUtil.convertMessage2String(out);

    }
}
