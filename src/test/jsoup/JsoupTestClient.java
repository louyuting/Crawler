package test.jsoup;

import java.util.Set;

import org.apache.log4j.Logger;

import utils.HttpRequestUtil;
import utils.JsoupUtil;

//
public class JsoupTestClient {
	private static Logger logger = Logger.getLogger(JsoupTestClient.class);
	
	public static void main(String[] args) throws Exception {
		String uri = "http://baike.baidu.com/";
		String body = HttpRequestUtil.get(uri);
		logger.debug(body);
		Set<String> uris =  JsoupUtil.ParserHtmlGetUri(body);
		
		//输出链接的个数
		logger.debug("当前网址的uri总数"+uris.size());
		
		for (String uri_ : uris) {
			logger.debug(uri_);
		}
		
	}

}






















