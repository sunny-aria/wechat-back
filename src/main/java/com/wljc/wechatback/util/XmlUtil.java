package com.wljc.wechatback.util;

import com.wljc.wechatback.ConfigConstant.CommonConstant;
import com.wljc.wechatback.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;

import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * 解析xml文本，生成xml文本
 */
@Slf4j
public class XmlUtil {

	static Document document = null;
	static Element rootElm = null;

	public XmlUtil() {
		document = DocumentHelper.createDocument();
		// 创建根节点
		rootElm = document.addElement("xml");
	}

	public XmlUtil(String xmlString) {
		try {
			document = DocumentHelper.parseText(xmlString);
			rootElm = document.getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public String getXmlString() {
		return document.asXML();
	}

	public Element setElementText(String elementName, String elementText) {
		Element e = rootElm.addElement(elementName);
		e.setText(elementText);
		return e;
	}

	public Element setElementCData(String elementName, String elementText) {
		Element e = rootElm.addElement(elementName);
		e.addCDATA(elementText);
		return e;
	}

	public String getElementText(String elementName) {
		return rootElm.elementText(elementName);
	}
    
    public boolean hasElementNode(String xpathExpression){
        boolean result = false;
        Node node = rootElm.selectSingleNode(xpathExpression);
        if(null != node){
            result = true;
        }
        return result;
    }
    
	public static Message convertString2Message(String xmlString) {
		Message message = new Message();
		Class<? extends Message> messageClass = message.getClass();
		Document document = null;
		try {
			document = DocumentHelper.parseText(xmlString);
		} catch (DocumentException e1) {
			log.error(e1.getMessage());
		}
		Element rootElm = document.getRootElement();

		try {
			for (Iterator<?> it = rootElm.elementIterator(); it.hasNext();) {
				Element e = (Element) it.next();

				Method m = messageClass.getDeclaredMethod("set" + e.getName(),
						String.class);
				m.invoke(message, e.getText());

			}
		} catch (Exception e1) {
			log.error(e1.getMessage());
		}

		return message;
	}

	public static String convertMessage2String(Message messageOut) {
		if (messageOut.getMsgType().equals(CommonConstant.MSG_TYPE_TEXT)) {
			return getStringFromTextMSG(messageOut);
		} else if (messageOut.getMsgType().equals(CommonConstant.MSG_TYPE_NEWS)) {
			return getStringFromNewsMSG(messageOut);
		} else if (messageOut.getMsgType().equals(CommonConstant.MSG_TYPE_EVENT)) {
			return getStringFromNewsMSG(messageOut);
		} else if (messageOut.getMsgType().equals(CommonConstant.MSG_TYPE_LOCATION)) {
			return getStringFromLocationMSG(messageOut);
		} else if(messageOut.getMsgType().equals(CommonConstant.MSG_CUSTOMER_SERVICE)){
			return getStringFromTextMSG(messageOut);
		}
		return "success";
	}

	private static String getStringFromTextMSG(Message messageOut) {
		String content = messageOut.getContent();
		if (StringUtils.isEmpty(content)) {
			return "success";
		}
		document = DocumentHelper.createDocument();
		rootElm = document.addElement("xml");
		rootElm.addElement("ToUserName").addCDATA(messageOut.getToUserName());
		rootElm.addElement("FromUserName").addCDATA(
				messageOut.getFromUserName());
		rootElm.addElement("CreateTime").setText(messageOut.getCreateTime());
		if(messageOut.getMsgType().equals(CommonConstant.MSG_CUSTOMER_SERVICE)){
			rootElm.addElement("MsgType").addCDATA(CommonConstant.MSG_CUSTOMER_SERVICE);
		}else{
			rootElm.addElement("MsgType").addCDATA(CommonConstant.MSG_TYPE_TEXT);
		}
		rootElm.addElement("Content").addCDATA(messageOut.getContent());
		return document.asXML();
	}



	private static String getStringFromNewsMSG(Message messageOut) {
		document = DocumentHelper.createDocument();
		rootElm = document.addElement("xml");
		rootElm.addElement("ToUserName").addCDATA(messageOut.getToUserName());
		rootElm.addElement("FromUserName").addCDATA(
				messageOut.getFromUserName());
		rootElm.addElement("CreateTime").setText(messageOut.getCreateTime());
		rootElm.addElement("MsgType").addCDATA(CommonConstant.MSG_TYPE_NEWS);
		rootElm.addElement("ArticleCount").setText(
				String.valueOf(messageOut.getArticles().size()));
		Element articlesElement = rootElm.addElement("Articles");
		for (Message m : messageOut.getArticles()) {
			Element e = articlesElement.addElement("item");
			e.addElement("Title").addCDATA(m.getTitle());
			e.addElement("Description").addCDATA(m.getDescription());
			e.addElement("PicUrl").addCDATA(m.getPicUrl());
			e.addElement("Url").addCDATA(m.getUrl());
		}
		return document.asXML();
	}

	private static String getStringFromLocationMSG(Message messageOut) {
		document = DocumentHelper.createDocument();
		rootElm = document.addElement("xml");
		rootElm.addElement("ToUserName").addCDATA(messageOut.getToUserName());
		rootElm.addElement("FromUserName").addCDATA(
				messageOut.getFromUserName());
		rootElm.addElement("CreateTime").setText(messageOut.getCreateTime());
		rootElm.addElement("MsgType").addCDATA(CommonConstant.MSG_TYPE_LOCATION);
		rootElm.addElement("Location_X").setText(
				messageOut.getLocation_X());
		rootElm.addElement("Location_Y").setText(
				messageOut.getLocation_Y());
		rootElm.addElement("Scale").setText(messageOut.getScale());
		rootElm.addElement("Label").addCDATA(messageOut.getLabel());
		rootElm.addElement("MsgId").setText(messageOut.getMsgId());
		return document.asXML();
	}
}
