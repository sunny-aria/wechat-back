package com.wljc.wechatback.service;

import com.wljc.wechatback.ConfigConstant.MyConfig;
import com.wljc.wechatback.model.Message;
import com.wljc.wechatback.util.XmlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.wljc.wechatback.ConfigConstant.CommonConstant.*;
import static com.wljc.wechatback.ConfigConstant.MenuEventKeyConstant.*;

/**
 * 功能描述：处理事件消息类型，关注，取关，菜单点击事件
 *
 * @Auther: hd
 * @Date: 2018/12/13
 */
@Slf4j
@Service("messageServiceEventImpl")
public class MessageServiceEventImpl implements MessageService {
    @Autowired
    private MyConfig myConfig;
    @Override
    public String handler(Message reqMsg) {
        Message out = new Message();
        String event = reqMsg.getEvent();
        if(MSG_EVENT_SUBSCRIBE.equals(event)){
            out = defaultHandler(reqMsg);
            //todo 扫描并关注
        }
        if(MSG_EVENT_UNSUBSCRIBE.equals(event)){
            return "success";
        }
        //已关注后扫描事件
        if(MSG_EVENT_SCAN.equals(event)){
            //todo EventKey解析场景值
            return "success";
        }
        //自定义菜单事件，要处理eventKey的菜单
        if(MSG_EVENT_CLICK.equals(event)){
            out = executeEventKey(reqMsg);
        }

        if(MSG_EVENT_LOCATION.equals(event)){
            out = defaultHandler(reqMsg);
        }
        return  XmlUtil.convertMessage2String(out);
    }

    private Message executeEventKey(Message reqMsg){
        Message out = new Message();
        String eventKey = reqMsg.getEventKey();
        if(STORE_ADDR.equals(eventKey)){
            out = storeAddr(reqMsg);
        }

        // 客服在线
        if(CUST_ONLINE.equals(eventKey)){
            out = defaultHandler(reqMsg);
            out.setContent("我们的电话是031-888888，请电话联系");
        }

        // 坐诊医生
        if(DOCTOR_INFO.equals(eventKey)){
            out = doctorInfo(reqMsg);
        }

        // 送药电话
        if(MEDICINE_PHONE.equals(eventKey)){
            out = defaultHandler(reqMsg);
            out.setContent("您好，[太阳] 送药时间是：9：00-17：00\n" +
                    "请拨打031-888888，告知送药地址和清单，\n" +
                    "我们将送药到家！[奋斗]");
        }

        // 招聘信息
        if(EMPLOY_INFO.equals(eventKey)){
            out = employInfo(reqMsg);
        }


        return out;
    }

    private Message storeAddr(Message messageIn){
        Message newsMessageOut = new Message();
        List<Message> articles = new ArrayList<Message>();
        Message item = new Message();
        item.setTitle("门店地址");
        item.setDescription("各区门店地址");
        item.setPicUrl(myConfig.getPicPath()+"store.jpg");
        item.setUrl(myConfig.getSysPath()+"/biz/storeInfo");
        articles.add(item);

        newsMessageOut.setToUserName(messageIn.getFromUserName());
        newsMessageOut.setFromUserName(messageIn.getToUserName());
        newsMessageOut.setCreateTime(messageIn.getCreateTime());
        newsMessageOut.setMsgType(MSG_TYPE_NEWS);
        newsMessageOut.setArticleCount(articles.size());
        newsMessageOut.setArticles(articles);
        return newsMessageOut;
    }

    private Message employInfo(Message messageIn){
        Message newsMessageOut = new Message();
        List<Message> articles = new ArrayList<Message>();
        Message item = new Message();
        item.setTitle("招聘信息");
        item.setDescription("招聘信息");
        item.setPicUrl(myConfig.getPicPath()+"employ.jpg");
        item.setUrl(myConfig.getSysPath()+"/biz/employInfo");
        articles.add(item);

        newsMessageOut.setToUserName(messageIn.getFromUserName());
        newsMessageOut.setFromUserName(messageIn.getToUserName());
        newsMessageOut.setCreateTime(messageIn.getCreateTime());
        newsMessageOut.setMsgType(MSG_TYPE_NEWS);
        newsMessageOut.setArticleCount(articles.size());
        newsMessageOut.setArticles(articles);
        return newsMessageOut;
    }

    private Message doctorInfo(Message messageIn){
        Message newsMessageOut = new Message();
        List<Message> articles = new ArrayList<Message>();
        Message item = new Message();
        item.setTitle("坐诊医师");
        item.setDescription("坐诊医师简介");
        item.setPicUrl(myConfig.getPicPath()+"doctor.jpg");
        item.setUrl(myConfig.getSysPath()+"/biz/doctorInfo");
        articles.add(item);

        newsMessageOut.setToUserName(messageIn.getFromUserName());
        newsMessageOut.setFromUserName(messageIn.getToUserName());
        newsMessageOut.setCreateTime(messageIn.getCreateTime());
        newsMessageOut.setMsgType(MSG_TYPE_NEWS);
        newsMessageOut.setArticleCount(articles.size());
        newsMessageOut.setArticles(articles);
        return newsMessageOut;
    }
}
