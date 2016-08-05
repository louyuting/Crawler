package test.httpclient;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import utils.PrintUtil;
import utils.UUIDUtil;

/**
 * 测试
 * @author LL
 *
 */
public class GetDemo {
	@SuppressWarnings("unused")
	private static String SAVE_PATH = "E:/images/" + UUIDUtil.getUUID()+".jpg";
	
	public static void main(String[] args) throws Exception, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//创建GET请求对象
		String uri = "http://img.youguoquan.com/uploads/activity/banner/e7f668fc55eafe5c3e84ec14abc25759.jpg";
		HttpGet httpget = new HttpGet(uri);
		//执行请求
		CloseableHttpResponse  response = httpclient.execute(httpget);
		
		//HttpEntity body = response.getEntity();
		InputStream is = null;
		OutputStream os = null;
		
		try {
			//获取响应并打印
			String strResponse = response.toString();
			PrintUtil.println(strResponse);
			
			PrintUtil.println("=====分割线===============================================");
			//获取response的实体
			@SuppressWarnings("unused")
			HttpEntity entity = response.getEntity();
			//获取实体的内容流,即一个输入流。用于读取里面的文件
			//is = entity.getContent();
			//SAVE_PATH = DirectoryUtil.getDirectoryByUri(uri, "E:/images/");
			//ImageDownloadUtil.downloadImg(is, SAVE_PATH);
			
			//String body = EntityUtils.toString(entity, "UTF-8");
			//PrintUtils.println(body);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(is!=null){
				is.close();
				is = null;
			}
			if(os!=null)
			{
				os.close();
				os = null;
			}
			if(response!=null)
				response.close();
			if(httpclient!=null)
				httpclient.close();
		}
		
		
	}//end main

}
