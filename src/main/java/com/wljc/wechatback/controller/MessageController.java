package com.wljc.wechatback.controller;

import com.alibaba.fastjson.JSONObject;
import com.wljc.wechatback.ConfigConstant.CommonConstant;
import com.wljc.wechatback.model.Message;
import com.wljc.wechatback.service.MessageService;
import com.wljc.wechatback.service.MessageServiceEventImpl;
import com.wljc.wechatback.service.MessageServiceTextImpl;
import com.wljc.wechatback.util.IOUtil;
import com.wljc.wechatback.util.SHA1;
import com.wljc.wechatback.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 功能描述：消息控制类
 *
 * @Auther: hd
 * @Date: 2018/11/26
 */
@Slf4j
@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource(name="messageServiceTextImpl")
    private MessageService messageServiceTextImpl;

    @Resource(name="messageServiceEventImpl")
    private MessageService messageServiceEventImpl;

    @RequestMapping("/index")
    public String index(){
        return "项目运行中...";
    }

    @RequestMapping("/handle")
    public String handle(HttpServletRequest request){
        String echostr = request.getParameter("echostr");

        log.info("消息服务接收到参数:" + request.getParameter("signature") + "---" + request.getParameter("timestamp") + "---"
                + request.getParameter("nonce") );
        if (StringUtils.isNotEmpty(echostr)) {
            if (checkUser(request, CommonConstant.TOKEN)) {
                return echostr;
            }
        }
        String message = IOUtil.getRequestPacket(request);
        log.info("消息服务接收到消息内容：{}",message);
        Message reqMsg = XmlUtil.convertString2Message(message);
        log.info("消息格式化：{}", JSONObject.toJSON(reqMsg));
        log.info("msgType：{},event:{},key:{}", reqMsg.getMsgType(),reqMsg.getEvent(),reqMsg.getEventKey());
        MessageService messageService = null;
        if(CommonConstant.MSG_TYPE_EVENT.equals(reqMsg.getMsgType())){
            messageService = messageServiceEventImpl;
        }else{
            messageService = messageServiceTextImpl;
        }
        String out = messageService.handler(reqMsg);
        log.info("消息服务返回消息：{}",out);
        return out;
    }



    private boolean checkUser(HttpServletRequest request, String token) {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String[] sList = new String[] { token, timestamp, nonce };
        Arrays.sort(sList);
        String s = sList[0] + sList[1] + sList[2];
        SHA1 sha1 = new SHA1();
        String code = sha1.getDigestOfString(s.getBytes());
        if (signature.equals(code)) {
            return true;
        }
        return false;
    }
}
