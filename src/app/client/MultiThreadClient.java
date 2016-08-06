package app.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utils.CrawlerThread;
import utils.LinkQueue;

/**
 * 多线程来爬取网站
 * @author LL
 * @since 2016-8-6
 *
 */
public class MultiThreadClient {
	
	/**
	 * 使用种子初始化 URI 队列
	 * @param seeds 种子的URL数组
	 */
	private static void initCrawlerSeeds(String[] seeds) {
		for(int i=0; i<seeds.length; i++)
			LinkQueue.addUnvisitedUri(seeds[i]);
	}
	
	public static void main(String[] args) {
		//定义uri种子
		String[] seeds = {"http://www.ugirls.com/",
						  "http://www.ugirls.com/Content/",
						  "http://www.ugirls.com/Models/",
						  "http://www.ugirls.com/Video/",
						  "http://www.ugirls.com/Rank/",};
		
		/** 多线程爬取网站时候要先进行种子设置好，然后多个线程才能开始爬取 */
		initCrawlerSeeds(seeds);
		
		//创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i=0; i<5; i++){
			executor.execute(new CrawlerThread(10000));
		}
		//启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
		executor.shutdown();
	}
	
}
