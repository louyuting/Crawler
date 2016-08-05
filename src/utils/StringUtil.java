package utils;

import java.text.DecimalFormat;

public class StringUtil {
	
	/**
	 * 验证字符串是否是null或者是空串或则全是空格
	 * @param str
	 * @return 为空返回true
	 */
	public static boolean isEmpty(String str){
		
		if(str == null || str.trim().equals(""))
			return true;
		
		return false;
	}
	
	/**
	 * 验证字符串是否是null或者是空串或则全是空格
	 * @param str
	 * @return 非空字符串返回true
	 */
	public static boolean isNotEmpty(String str){
		
		if(str == null || str.trim().equals(""))
			return false;
		
		return true;
	}
	
	/**
     * 验证Email地址是否有效
     * @param Email
     * @return 验证结果
     */
    public static boolean validEmail(String sEmail) {
        String pattern = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return sEmail.matches(pattern);
    }
    
    
    /**
     * 验证字符串是否小于设定值
     * @param str 待校验的字符串
     * @param length 字符串的最大教研长度
     * @return 字符串长度满足条件返回true 否则返回false
     */
    public static boolean validMaxLen(String str, int length) {
        if (str == null || str.equals("")) {
            return true;
        }
        if (str.length() > length) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 验证字符最小长度
     * @param str
     * @return
     */
    public static boolean validMinLen(String str, int length) {
        if (str == null || str.equals("")) {
            return false;
        }
        if (str.length() < length) {
            return false;
        } else {
            return true;
        }
    }
    
    
    /**
     * 验证两个字符串是否相等且不能为空
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsAndNotNull(String str1, String str2) {
        if (str1 == null || str1.equals("") || str2 == null || str2.equals("")) {
            return false;
        }
        return str1.equals(str2);
    }
    
    
    /**
     * 得到WEB-INF的绝对路径
     *
     * @return
     */
    public static String getWebInfPath() {
        String filePath = Thread.currentThread().getContextClassLoader()
                .getResource("").toString();
        if (filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }
        if (filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }
        if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }
        if (!filePath.endsWith("/"))
            filePath += "/";
        return filePath;
    }
    
    
    /**
     * 得到根目录绝对路径(不包含WEB-INF)
     *
     * @return
     */
    public static String getRootPath() {
        // return "E:/javatool/apache-tomcat-6.0.18/webapps/user_test";
        String filePath = Thread.currentThread().getContextClassLoader()
                .getResource("").toString();
        if (filePath.toLowerCase().indexOf("file:") > -1) {
            filePath = filePath.substring(6, filePath.length());
        }
        if (filePath.toLowerCase().indexOf("classes") > -1) {
            filePath = filePath.replaceAll("/classes", "");
        }
        if (filePath.toLowerCase().indexOf("web-inf") > -1) {
            filePath = filePath.substring(0, filePath.length() - 9);
        }
        if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
            filePath = "/" + filePath;
        }

        if (filePath.endsWith("/"))
            filePath = filePath.substring(0, filePath.length() - 1);

        return filePath;
    }
    
    
    /**
     * 将计量单位字节转换为相应单位
     *
     * @param size
     * @return
     */
    public static String getFileSize(String fileSize) {
        String temp = "";
        DecimalFormat df = new DecimalFormat("0.00");
        double dbFileSize = Double.parseDouble(fileSize);
        if (dbFileSize >= 1024) {
            if (dbFileSize >= 1048576) {
                if (dbFileSize >= 1073741824) {
                    temp = df.format(dbFileSize / 1024 / 1024 / 1024) + " GB";
                } else {
                    temp = df.format(dbFileSize / 1024 / 1024) + " MB";
                }
            } else {
                temp = df.format(dbFileSize / 1024) + " KB";
            }
        } else {
            temp = df.format(dbFileSize / 1024) + " KB";
        }
        return temp;
    }
    
    
    /**
     * 将中文汉字转成UTF8编码
     *
     * @param str
     * @return
     */
    public static String toUTF8(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        try {
            return new String(str.getBytes("ISO8859-1"), "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static String to(String str, String charset) {
        if (str == null || str.equals("")) {
            return "";
        }
        try {
            return new String(str.getBytes("ISO8859-1"), charset);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    
    
    
    
    
    
    
    
	
}
