package com.crawler.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.junit.Test;


/**
 * Properties 配置文件的工具类
 * @author LL
 * 2016-9-14
 *
 */
public class PropertiesUtil {
	
	
	/**
	 * 根据配置文件的路径获取配置文件对象
	 * @param path
	 * @return
	 */
	public static Properties getProperties(String path){
		//创建配置文件对象
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			//加载配置文件
			fis = new FileInputStream(path);
			properties.load(fis);
			fis.close();
		} catch (Exception e) {
			LogUtil.log_debug("读取配置文件失败。");
			e.printStackTrace();
		}
		return properties;
	}
	
	/**
	 * 根据配置文件的路径，获取Properties配置文件中的所有的值。
	 * @param path
	 * @return value的列表
	 */
	public static List<String> getValues(String path){
		Properties properties = getProperties(path);
		
		List<String> list = new ArrayList<String>();
		
		Collection<Object> collection =properties.values();
		
		for (Object object : collection) {
			String value = (String) object;
			list.add(value);
		}
		return list;
	}
	
	
	/**
	 * 获取key的前缀是 prefix的键值对的values
	 * @param filePath
	 * @param prefix key的前缀
	 */
	public static List<String> getValues(String filePath, String prefix){
		//获取配置文件对象
		Properties properties = PropertiesUtil.getProperties(filePath);
		//创建结果集合对象
		List<String> anonUrlsList = new ArrayList<String>();
		// 获取key的set集合
		Collection<Object> keys = properties.keySet();
		for (Object object : keys) {
			String key = (String) object;
			if(key.startsWith(prefix)){
				String value = properties.getProperty(key);
				anonUrlsList.add(value);
			}
		}
		return anonUrlsList;
	}
	
	
	/**
	 * 写入Properties信息
	 * @param filePath
	 * @param Key
	 * @param Value
	 * @throws Exception
	 */
	public static void WriteProperties(String filePath, String Key, String Value) throws Exception {
		Properties properties = getProperties(filePath);
		//调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
		//强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		OutputStream out = new FileOutputStream(filePath);
		properties.setProperty(Key, Value);
		//以适合使用 load 方法加载到 Properties 表中的格式，  
		//将此 Properties 表中的属性列表（键和元素对）写入输出流  
		properties.store(out, "设置属性值。");
	}
	
//================================================================================================	
//测试
//================================================================================================	
	@Test
	public void test(){
		List<String> values = getValues("C:/Users/Administrator/git/Purplecollar/config/urls.properties");
		LogUtil.printList(values);
	}
	
	@Test
	public void get(){
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("C:/Users/Administrator/git/purplecollar/config/urls.properties");
			properties.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String name = properties.getProperty("name");
		String age = properties.getProperty("age");
		LogUtil.log_debug(name + "-"+ age);
	}
	
	@Test
	public void write(){
		try {
			WriteProperties("C:/Users/Administrator/git/purplecollar/config/urls.properties", "name", "loulou");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}













