package app.crawler;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;


public class CrawlerThread implements Runnable{
	private static Logger logger = Logger.getLogger(CrawlerThread.class);
	/** 用来计数总的线程数 */
	private static int threadCount = 1;
	/** 给每个线程指定一个固定的ID  */
	private final int threadId = threadCount++;
	/** 设置当前线程爬取的链接数量  */
	private int MAX_URI_NUM;
	
	/** 构造器，默认当前线程爬取100个网页  */
	public CrawlerThread(){
		super();
		this.MAX_URI_NUM = 100;
	}
	/** 构造器，设置当前线程爬取网页数  */
	public CrawlerThread(int maxUriNum){
		super();
		this.MAX_URI_NUM = maxUriNum;
	}
	
	/**
	 * 线程的执行函数
	 */
	@Override
	public void run() {
		System.out.println("线程"+threadId+"开始执行爬虫~~~~~~");
		//创建爬虫对象，并设置爬取网页的最大数量
		Crawler crawler = new Crawler(MAX_URI_NUM);
		//根据ID，让每个线程都睡眠一会儿
		try {
			//当前线程启动后休眠一会儿，方便让其余线程启动
			TimeUnit.MILLISECONDS.sleep(10* threadId);
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		//爬取网页
		boolean flag = crawler.crawling(threadId);
		
		if(flag == true){
			System.out.println("线程"+threadId+"爬虫执行完毕。");
		} else {
			System.out.println("线程"+threadId+"爬虫执行出现异常。");
		}
		System.out.println("线程"+threadId+"结束执行爬虫~");
		
		System.out.println("已访问Uri数量："+LinkQueue.getVisitedUriNum());
		System.out.println("未访问Uri数量："+LinkQueue.getUnVistiedUriNum());
		
	}//end of run
	
	
	//getter 方法
	public synchronized int getThreadId() {
		return threadId;
	}
	
}




























