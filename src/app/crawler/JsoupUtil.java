package app.crawler;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import utils.StringUtil;


/**
 * 工具类，专门用于解析HTML
 * @author LL
 * @since 2016-8-3
 */
public class JsoupUtil {
	
	private static Logger logger = Logger.getLogger(JsoupUtil.class);
	
	/**
	 * function：通过传入的HTML的字符形式，然后将HTML解析获取所有的<a/>标签中href属性的的uri链接存入set集合中
	 * @param html HTML的字符串表示形式
	 * @param baseUri 绝对路径的前缀
	 * @return 当前HTML中的所有Uri链接
	 */
	public static Set<String> parserHtmlGetUri(String html, String baseUri){
		//定义set集合用来存储不重复的uri
		Set<String> uris = new HashSet<String>();
		
		//对传入的HTML进行一次校验
		if(!StringUtil.isNotEmpty(html)){
			return uris;
		}
		
		//获取文档对象模型
		Document document = Jsoup.parse(html, baseUri);
		//获取所有<a/>标签中的href属性的值
		Elements links = document.select("a[href]");
		//logger.debug("当前HTML中的<a/>标签中href总数是：" + links.size());
		
		//迭代每个元素  <a/>
		for (Element uri : links) {
			//输出每个 uri dao控制台
			logger.debug("当前网页获取的uri："+uri.attr("abs:href"));
			
			String uriStr = uri.attr("abs:href");
			//当uri不为空且取出开头结尾空格后不为空串则添加到集合中
			if( StringUtil.isNotEmpty(uriStr) )
				uris.add(uriStr);
		}
		
		return uris;
	}
	
	
	/**
	 * function：通过传入的HTML的字符形式，然后将HTML解析获取所有的<a/>标签中href属性的的uri链接存入set集合中
	 * @param html HTML的字符串表示形式
	 * @return 当前HTML中的所有uri链接
	 */
	public static Set<String> ParserHtmlGetUri(String html){
		
		return parserHtmlGetUri(html, "");
	}
	
	/**
	 * 内部调用函数
	 * @param imgUris
	 * @param links
	 */
	private static void checkImgUriLegal(Set<String> imgUris, Elements links){
		
		for (Element element : links) {
			String imgUri = element.attr("abs:data-original");
			//logger.debug("当前图片的地址是：" + imgUri);
			//筛选图片不是以.jpg或则.png或者.gif结尾，退出当前迭代,进入下一次迭代
			if( !(imgUri.endsWith(".jpg") || imgUri.endsWith(".png") || imgUri.endsWith(".gif")) ){
				continue;
			}
			
			//检查uri是否为空
			if(!StringUtil.isNotEmpty(imgUri))
				continue;
			
			//jpg图片不是以http开头，退出当前迭代
			if( imgUri.endsWith(".jpg") && (!imgUri.startsWith("http://")) )
				continue;
			
			//如果.png图片 地址不是以 http://www.ugirls.com/ 开头的
			if( imgUri.endsWith(".png") && (!imgUri.startsWith("http://www.ugirls.com/")) )
				imgUri = imgUri+"http://www.ugirls.com";
			
			//如果.gif图片 地址不是以 http://www.ugirls.com/ 开头的
			if( imgUri.endsWith(".gif") && (!imgUri.startsWith("http://www.ugirls.com/")) )
				imgUri = imgUri+"http://www.ugirls.com";
			
			//添加到集合中
			imgUris.add(imgUri);
		}
		
	}
	
	/**
	 * <img src=""   />
	 * function:获取HTML中的img标签下的 src 属性 然后存入set集合中
	 * @param html
	 * @return
	 */
	public static Set<String> ParserHtmlGetImgUri(String html, String baseUri){
		
		//定义set集合用来存储不重复的图片地址
		Set<String> imgUris = new HashSet<String>();
		
		//对传入的HTML进行一次校验
		if(!StringUtil.isNotEmpty(html)){
			return imgUris;
		}
		
		//获取文档对象模型
		Document document = Jsoup.parse(html, baseUri);
		//获取可能包含图片链接的标签的属性
		Elements links1 = document.select("img[data-original]");
		Elements links2 = document.select("img[src]");
		Elements links3 = document.select("img[data-src]");
		Elements links4 = document.select("li[data-thumb]");
		Elements links5 = document.select("a[data-original]");
		Elements links6 = document.select("a[src]");
		
		//校验图片uri
		checkImgUriLegal(imgUris, links1);
		checkImgUriLegal(imgUris, links2);
		checkImgUriLegal(imgUris, links3);
		checkImgUriLegal(imgUris, links4);
		checkImgUriLegal(imgUris, links5);
		checkImgUriLegal(imgUris, links6);
		
		return imgUris;
		
	}
	
	
	/**
	 * function:获取HTML中的img标签下的 src 属性 然后存入set集合中
	 * @param html
	 * @return
	 */
	public static Set<String> ParserHtmlGetImgUri(String html){
		
		return ParserHtmlGetImgUri(html, "");
	}
	
}









