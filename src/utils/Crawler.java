package utils;


import java.util.Date;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 爬虫主类
 * @author LL
 *
 */
public class Crawler {
	//获取日志器
	private static Logger logger = Logger.getLogger(Crawler.class);
	
	//定义当前爬虫爬取的 uri 的最大数，
	private int MAX_URI_NUM;
	
	//记录当前爬取网页的个数
	private int pageCount = 0;
	//记录下载图片数量
	private int imgCount = 0;

	//定义默认构造器
	public Crawler(){
		super();
		//默认是10000条
		this.MAX_URI_NUM=10000;
	}
	
	//构造器，指定当前爬虫爬取的最大uri数量
	public Crawler(int maxUriNum) {
		super();
		this.MAX_URI_NUM = maxUriNum;
	}
	
	//过去当前爬虫爬取uri的最大
	public synchronized int getMaxUriNum() {
		return MAX_URI_NUM;
	}
	//设置当前爬虫爬取uri的最大数量，注意：只能在开始爬虫前设置
	public synchronized void setMaxUriNum(int maxUriNum) {
		this.MAX_URI_NUM = maxUriNum;
	}
	
	/**
	 * 使用种子初始化 URI 队列
	 * @param seeds 种子的URL数组
	 */
	private void initCrawlerSeeds(String[] seeds) {
		for(int i=0; i<seeds.length; i++)
			LinkQueue.addUnvisitedUri(seeds[i]);
	}
	
	
	/**
	 * function：抓取网页方法
	 * @param seeds：uri种子
	 * @throws Exception 
	 */
	public void crawling(String[] seeds) throws Exception {
		
		logger.debug("添加URI初始种子");
		System.out.println("添加URI初始种子");
		//1. 初始化未访问的 uri队列
		initCrawlerSeeds(seeds);
		
		//2. 循环爬取网页直至不满足循环条件：未访问的uri队列非空 且 已访问的uri数量小于限定值
		System.out.println("开始循环爬取网页,爬的网页最多为：" + this.MAX_URI_NUM);
		while((!LinkQueue.unVisitedUriIsEmpty()) && (LinkQueue.getVisitedUriNum() < this.MAX_URI_NUM)){
			pageCount++;
			System.out.println("这是爬取的第 "+pageCount+" 个网页");
			
			/** 2.1 从未访问的Uri队列中取出对头的uri */
			String visitUri = (String) LinkQueue.unVisitedUriDeQueue();
			//校验uri,如果检验不通过，则直接跳出此次循环，进入下一次循环
			if(visitUri == null || (visitUri.trim().equals(""))){
				continue;
			}
			
			/** 2.2 根据uri 模拟http请求,传回response的的实体的字符串形式 */
			String body = HttpRequestUtil.get(visitUri);
			
			//请求不成功，跳出当前请求，进行下一次循环
			if(body==null || body.equals("error") || body.trim().equals(""))
				continue;
			
			/** 把返回的HTML写入到磁盘本地，并给一个随机名称 */
			FileUtil.write(DirectoryUtil.getRandomFileName("E:/httpclient/html/"), body);
			/** 把已访问的uri链接写回本地磁盘，方便分析  */
			FileUtil.write(DirectoryUtil.getLogsFileName("E:/httpclient/logs/", "visitedUris"), 
					DateUtil.fmtDateToMillisecond(new Date())+"-第"+pageCount+"个链接："+visitUri+"\n");
			
			/** 2.3 把刚才已访问的uri添加到已访问的集合中  */
			LinkQueue.addVisitedUri(visitUri);
			
			/** 2.4 根据实体body 对HTML进行解析，返回当前uri中的未爬取的uri集合 */
			Set<String> links = JsoupUtil.ParserHtmlGetUri(body);
			
			/** 2.5 把links添加到未访问uri队列中 */
			for (String link : links) {
				if(StringUtil.checkStringNotNullAndEmpty(link))
					LinkQueue.addUnvisitedUri(link);
			}
			
			/** 获取当前 visitUri界面中的图片链接  */
			Set<String> imglinks = JsoupUtil.ParserHtmlGetImgUri(body);
			/** 爬取当前集合中的左右图片 */
			for (String imglink : imglinks) {
				imgCount++;
				//将链接添加到已爬取的链接集合里面
				ImageLinkQueue.addVisitedUri(imglink);
				//把下载地址保存在本地log
				FileUtil.write(DirectoryUtil.getLogsFileName("E:/httpclient/logs/", "imgUris"), 
						DateUtil.fmtDateToMillisecond(new Date())+"-第"+imgCount+"个图片："+imglink+"\n");
				//下载图片
				ImageDownloadUtil.downloadImg(imglink);
			}
			
			
		}//end while
	}//end method
	
	
}//end class










