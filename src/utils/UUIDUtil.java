package utils;

import java.util.UUID;

/**
 * 随机字符串工具类
 * @author LL
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
