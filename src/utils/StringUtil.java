package utils;

public class StringUtil {
	
	/**
	 * 验证字符串是否是null或者是空串或则全是空格
	 * @param str
	 * @return 非空字符串返回true
	 */
	public static boolean checkStringNotNullAndEmpty(String str){
		
		if(str == null || str.trim().equals(""))
			return false;
		
		return true;
	}
	
	
	
}
