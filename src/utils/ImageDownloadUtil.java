package utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * 这个是根据uri或者response的实体来下载图片到本地的工具类
 * @author LL
 * @since 2016-8-4
 *
 */
public class ImageDownloadUtil {
	
	/**
	 * function 根据输入流
	 * @param uri 图片的地址
	 * @param savePath 图片下载在本地的路径和文件名全称
	 * @return
	 * @throws Exception
	 */
	public static boolean downloadImg(String uri, String savePath) throws Exception{
		
		//模拟Http的Get请求
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(uri);
		RequestConfig requestConfig = RequestConfig.custom().
				setSocketTimeout(20000).setConnectTimeout(20000).build();
		httpGet.setConfig(requestConfig);
		//执行请求
		CloseableHttpResponse  response = httpclient.execute(httpGet);
		
		//回去响应体的entity中的图片
		InputStream is = response.getEntity().getContent();
		if (is == null) {
            return false;
        }
		
		OutputStream os = null;
		try {
			os = new FileOutputStream(savePath);
			//将输入流中的数据复制到输出流中
			//缓冲数组
			byte[] b = new byte[1024 * 10];
			int len;
			while ((len = is.read(b)) != -1) {
				os.write(b, 0, len);
			}
			// 刷新此缓冲的输出流
			os.flush();
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
		}
		return true;
	}
	
	
	/**
	 * function 根据输入流
	 * @param uri 图片的地址 内部可以根据路径自动生成图片在本地硬盘的地址
	 * @return
	 * @throws Exception
	 */
	public static boolean downloadImg(String uri) throws Exception{
		//获取保存在本地的地址
		String savePath = DirectoryUtil.getDirectoryByUri(uri);
		boolean flag = downloadImg(uri, savePath);
		
		return flag;
	}
	
	
	/**测试
	 * @throws Exception 
	 */
//	@Test
//	public void test() throws Exception{
//		String uri = "http://img.youguoquan.com/uploads/activity/banner/e7f668fc55eafe5c3e84ec14abc25759.jpg";
//		//获取保存在本地的地址
//		String savePath = DirectoryUtil.getDirectoryByUri(uri);
//		boolean flag = downloadImg(uri, savePath);
//		
//		System.out.println(flag);
//	}
	
}
