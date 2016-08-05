package test.httpclient;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import utils.PrintUtil;


/**
 * function:工具类，这个类有两个静态方法：GET和POST方式提交请求
 * @author LL
 *
 */
public class HttpClient {
	
	//定义日志器
	private static Logger logger = Logger.getLogger(HttpClient.class);

	/**
	 * 静态方法：根据URL发送GET请求
	 * @param url：待发送请求的链接地址
	 * @throws IOException 
	 * @throws Exception 
	 * @throws ClientProtocolException 
	 */
	public static void get(String uri) throws IOException {
		
		//创建httpClient实例
		//CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建httpClient实例并且添加 Request拦截器，每发送一个请求就拦截计数：
		CloseableHttpClient httpclient = HttpClients.custom().addInterceptorLast(new HttpRequestInterceptor() {
			/**
			 * 拦截器的处理程序接口的实现
			 */
			@Override
			public void process(HttpRequest request, HttpContext context)
					throws HttpException, IOException {
				//获取context中的count元素的值
				AtomicInteger count = (AtomicInteger) context.getAttribute("count");
				//count线程安全的自加,然后存储在context中
				context.setAttribute("count",  new AtomicInteger(count.incrementAndGet()) );
				//request.addHeader("Count", Integer.toString(count.getAndIncrement()));
			}
		}).build();
		
		//创建初始变量，初值为0，然后存入context上下文中。
		AtomicInteger count = new AtomicInteger(0);
		//创建一个httpClientContext 上下文环境
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAttribute("count", count);
		
		//创建一个GET请求
		HttpGet httpGet = new HttpGet(uri);
		
		//ConnectTimeout是连接请求超时；SocketTimeout是响应超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
		httpGet.setConfig(requestConfig);
		
		for (int i = 0; i < 10; i++) {
			logger.debug("这是第  " + i + " 次发送请求!");
			CloseableHttpResponse response = null;
			try {
				//执行发送请求，传入当前请求的参数httpGet和上下文环境localContext
				response = httpclient.execute(httpGet, localContext);
				//获取response的状态码并打印
				logger.debug("当前请求返回的状态码是："+response.getStatusLine().getStatusCode());
				//获取所有的响应头并且打印出来
				logger.debug("输出所有的响应头信息：");
				Header[] headers = response.getAllHeaders();
				for (Header header : headers) {
					logger.debug(header.toString());
				}
				
				//获取响应实体
				HttpEntity entity = response.getEntity();
				//通过httpcore的工具类将响应体装换成string
				String body = EntityUtils.toString(entity, "UTF-8");
				logger.debug("输出响应体："+body);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				//关闭response
				if (response != null) {
					try {
						EntityUtils.consume(response.getEntity());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}//end for循环
		//关闭httpclient
		if(httpclient!=null){
			httpclient.close();
		}
		//最后从context中获取 count 的值
		Object result = localContext.getAttribute("count");
		logger.debug("获取context中的count的值是：" + result);
	}// end get方法
	
	
	/**
	 * 
	 * 静态方法：根据URL发送POST请求
	 * @param uri 传入的地址
	 * @param data 传入的参数，以map形式传入
	 * @throws IOException
	 */
	public static void post(String uri, Map<String, String> data) throws IOException {
		
		//创建httpClient实例
		//CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建httpClient实例并且添加 Request拦截器，每发送一个请求就拦截计数：
		CloseableHttpClient httpclient = HttpClients.custom().addInterceptorLast(new HttpRequestInterceptor() {
			/**
			 * 拦截器的处理程序接口的实现
			 */
			@Override
			public void process(HttpRequest request, HttpContext context)
					throws HttpException, IOException {
				//获取context中的count元素的值
				AtomicInteger count = (AtomicInteger) context.getAttribute("count");
				//count线程安全的自加,然后存储在context中
				context.setAttribute("count",  new AtomicInteger(count.incrementAndGet()) );
				//request.addHeader("Count", Integer.toString(count.getAndIncrement()));
			}
		}).build();
		
		//创建初始变量，初值为1
		AtomicInteger count = new AtomicInteger(0);
		//创建一个httpClientContext 上下文环境
		HttpClientContext localContext = HttpClientContext.create();
		localContext.setAttribute("count", count);
		
		//创建一个GET请求
		HttpPost httpPost = new HttpPost(uri);
		
		//为post请求设置entity
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if (data != null) {
			for (String key : data.keySet()) {
				params.add( new BasicNameValuePair(key, data.get(key)) );
			}
		}
		httpPost.setEntity(new UrlEncodedFormEntity(params, Charset.forName("UTF-8")));
		
		//测试
//		URI httpuri = httpPost.getURI();
//		logger.debug("post-uri:" + httpuri );
//		HttpEntity postentity = httpPost.getEntity();
//		String str = EntityUtils.toString(postentity, "UTF-8"); 
//		logger.debug("post-entity:" + str ); 
		
		//为POST设置传输超时时间和请求超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
		httpPost.setConfig(requestConfig);
		
		for (int i = 0; i < 10; i++) {
			logger.debug("这是第 " + i + "次发送请求~");
			CloseableHttpResponse response = null;
			try {
				response = httpclient.execute(httpPost, localContext);
				logger.debug("当前请求返回的状态码是："+response.getStatusLine().getStatusCode());
				//获取所有的响应头并且打印出来
				Header[] headers = response.getAllHeaders();
				for (Header header : headers) {
					logger.debug(header.toString());
				}
				//获取响应实体
				HttpEntity entity = response.getEntity();
				
				String body = EntityUtils.toString(entity, "UTF-8");
				logger.debug(body);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (response != null) {
					try {
						EntityUtils.consume(response.getEntity());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}//end for循环
		
		//关闭httpClient
		if(httpclient!=null){
			httpclient.close();
		}
		//最后从context中获取 count 的值
		Object result = localContext.getAttribute("count");
		logger.debug("获取context中的count的值是：" + result);
	}// end post
	
	
	/**
	 * 主函数测试
	 */
	public static void main(String[] args) {
		
		PrintUtil.println("开始测试");
		Map<String, String> data = new HashMap<String, String>();
		data.put("username", "louyuting");
		data.put("password", "733733");
		try {
			//HttpClient.get("http://baike.baidu.com/");
			HttpClient.post("http://baike.baidu.com/", data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintUtil.println("结束测试");
	}

}













