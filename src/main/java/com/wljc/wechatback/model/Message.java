package com.wljc.wechatback.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 功能描述： 用于解析文本，图文，被动回复消息内容
 * @author  dongfeng
 * @Date: 2018/12/13
 */
@Data
public class Message implements Serializable{

	private static final long serialVersionUID = -8855090349832467407L;
	private String toUserName;
	private String fromUserName;
	private String createTime;
	private String msgType;
	private String content;
	private String msgId;
	private String picUrl;
	private String url;
	private String mediaId;
	private String format;
	private String recognition;
	private String thumbMediaId;
	private String location_X;
	private String location_Y;
	private String scale;
	private String label;
	private String title;
	private String description;
	//事件类型
	private String event;
	//菜单事件
	private String eventKey;
	private String ticket;
	//经度
	private String longitude;
	//维度
	private String latitude;
	//精度
	private String precision;

	private String musicUrl;
	private String hQMusicUrl;
	private Integer articleCount;
	private List<Message> articles;

	private String status;
	private String menuId;
	// 卡卷审核或领取属性
	private String cardId;
	private String friendUserName;
	private String userCardCode;
	private String isGiveByFriend;
	private String isRestoreMemberCard;
	private String outerId;
	private String oldUserCardCode;
	// 授权ticketValue
	private String component_verify_ticket;

	

}
