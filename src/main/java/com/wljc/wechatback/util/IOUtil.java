package com.wljc.wechatback.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * 读取消息体
 */
public class IOUtil {

	public static String getRequestPacket(HttpServletRequest req) {
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader in = req.getReader();
			String line = "";
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

}