package utils;

import java.io.File;
import java.util.Date;

import org.junit.Test;

/**
 * function: 目录工具类
 * @author LL
 * @date 2016-8-4
 *
 */
public class DirectoryUtil {
	
	private static final String DEFAULT_BASEDIR = "E:/httpclient/images/";
	
	/**
	 * function：根据传入的uri的地址获取存在本地的目录
	 * 比如这样一个地址：http://img.youguoquan.com/uploads/activity/banner/e7f668fc55eafe5c3e84ec14abc25759.jpg
	 * @param uri
	 * 没有指定基地址，就使用默认的基地址
	 * @return 本地完整文件名
	 */
	public static String getDirectoryByUri(String uri){
		
		return getDirectoryByUri(uri, DEFAULT_BASEDIR);
	}
	
	
	/**
	 * function：根据传入的uri的地址和baseDir获取存在本地的目录
	 * @param uri 图片链接
	 * @param baseDir 本地磁盘的基地址
	 * @return 本地完整文件名
	 */
	public static String getDirectoryByUri(String uri, String baseDir){
		
		//判断baseDir是否以 /结尾
		if(!baseDir.endsWith("/"))
			baseDir = baseDir+"/";
		
		StringBuilder sb = new StringBuilder();
		//拼接字符串，在BASEDIR目录下添加当前日期的目录,这时应该变成 E:/images/2016-8-4/
		sb.append(baseDir).append(DateUtil.fmtDate(new Date())).append("/");
		
		//如果该目录不存在就创建该目录
		File filepath = new File(sb.toString());
		if(!filepath.exists())
			filepath.mkdirs();
		
		//添加文件名
		sb.append(UUIDUtil.getUUID());
		//根据获取文件后缀名
		int lastindex = uri.lastIndexOf(".");
		String filename = uri.substring(lastindex, uri.length());
		sb.append(filename);
		return sb.toString();
	}
	
	/**
	 * 获取随机的保存在本地硬盘上的HTML路径
	 * @param baseDir
	 * @return
	 */
	public static String getRandomFileName(String baseDir){
		//判断baseDir是否以 /结尾
		if(!baseDir.endsWith("/"))
			baseDir = baseDir+"/";
		
		StringBuilder sb = new StringBuilder();
		//拼接字符串，在BASEDIR目录下添加当前日期的目录,这时应该变成 E:/images/2016-8-4/
		sb.append(baseDir).append(DateUtil.fmtDate(new Date())).append("/");
		
		//如果该目录不存在就创建该目录
		File filepath = new File(sb.toString());
		if(!filepath.exists())
			filepath.mkdirs();
		
		//添加文件名
		sb.append(UUIDUtil.getUUID()).append(".html");
		return sb.toString();
	}
	
	
	public static String getLogsFileName(String baseDir, String fileName){
		//判断baseDir是否以 /结尾
		if(!baseDir.endsWith("/"))
			baseDir = baseDir+"/";
		
		StringBuilder sb = new StringBuilder();
		//拼接字符串，在BASEDIR目录下添加当前日期的目录,这时应该变成 E:/images/2016-8-4/
		sb.append(baseDir).append(DateUtil.fmtDate(new Date())).append("/");
		
		//如果该目录不存在就创建该目录
		File filepath = new File(sb.toString());
		if(!filepath.exists())
			filepath.mkdirs();
		
		//添加文件名
		sb.append(fileName);
		
		return sb.toString();
	}
	
	
	/** 测试   */
	@Test
	public void test(){
		String uri = "http://img.youguoquan.com/uploads/activity/banner/e7f668fc55eafe5c3e84ec14abc25759.jpg";
		String res = getDirectoryByUri(uri);
		System.out.println(res);
		
		String res2 = getDirectoryByUri(uri, "E:/httpclient/images");
		System.out.println(res2);
	}
	
	
}








