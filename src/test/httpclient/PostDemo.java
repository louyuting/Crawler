package test.httpclient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

import com.crawler.config.Config;
import com.crawler.httpclient.HttpRequestUtil;
import com.crawler.utils.FileUtil;
import com.crawler.utils.LogUtil;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

/***
 * 使用Apache的HTTPClient模拟Http请求
 * 
 * @author cc
 * 
 */
public class PostDemo {
	/***
	 * 模拟post请求，可以传递多个键值对
	 * 
	 * @param ipAddress
	 * @param port
	 * @param data
	 *            希望传递的参数
	 * @return
	 */
	public static String post(String url, Map<String, String> data) {
		String body = null;

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpResponse httpResponse = null;

		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("_xsrf", Config._xsrf));
        params.add(new BasicNameValuePair("email", Config.ZhiHu_email));
        params.add(new BasicNameValuePair("password", Config.ZhiHu_password));
        params.add(new BasicNameValuePair("remember_me", "true"));
        //输入：验证码；
        Scanner sc = new Scanner(System.in);
        String yzm = sc.nextLine();
        params.add(new BasicNameValuePair("captcha", yzm));
		/*if (data != null) {
			for (String key : data.keySet()) {
				params.add(new BasicNameValuePair(key, data.get((key))));
			}
		}*/

		try {
			//httpPost.addHeader("Content-Type", "application/json"); 
			httpPost.setEntity(new UrlEncodedFormEntity(params, Charset
					.forName("UTF-8")));
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000).build();//设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
            httpPost.setHeader("Cookie", Config.Cookie);
			
			//Header[] headers = httpPost.getAllHeaders();
			//System.out.println(headers.toString());
			
			httpResponse = httpClient.execute(httpPost);
			// 获取返回数据
			HttpEntity entity = httpResponse.getEntity();
			body = EntityUtils.toString(entity, "UTF-8");
			Header[] header = httpResponse.getAllHeaders();
			for(Header h : header){
				System.out.println(h.getName()+h.getValue());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpResponse != null) {
				try {
					EntityUtils.consume(httpResponse.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return body;
	}

	/***
	 * 模拟post请求，只传递一个键值对
	 * 
	 * @param ipAddress
	 * @param port
	 * @param key
	 * @param value
	 * @return
	 */
	public static String post(String url, String key, String value) {
		Map<String, String> data = new Hashtable<String, String>();
		if (key != null) {
			data.put(key, value);
		}
		return post(url, data);
	}

    @Test
    public static void main(String[] args){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        //创建GET请求对象
        String uri = "http://www.zhihu.com/captcha.gif?r=1477509171168&type=login";
        HttpGet httpget = new HttpGet(uri);
        //执行请求
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            InputStream ins = response.getEntity().getContent();
            FileUtil.copyStreamFile(ins, new File("F:\\captcha.gif"));
        } catch (Exception e){
            e.toString();
        }

        LogUtil.log_error(post("https://www.zhihu.com/people/lou-yu-ting-71/followees", null));
    }
}
