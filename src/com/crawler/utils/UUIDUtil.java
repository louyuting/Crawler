package com.crawler.utils;

import java.util.UUID;

/**
 * 生成激活码
 * @author 传智.郭嘉
 *
 */
public class UUIDUtil {
	
	/**
	 * 获取32位长度的随机字符串
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
