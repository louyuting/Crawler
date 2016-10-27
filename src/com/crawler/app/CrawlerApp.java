package com.crawler.app;

import com.crawler.config.Config;
import com.crawler.crawler.Crawler;
import com.crawler.imgParser.ImageLinkQueue;
import com.crawler.crawler.LinkQueue;

import java.util.Set;

/**
 * 这个是单线程爬虫的客户端
 * @author LL
 * @since 2016-8-1
 */
public class CrawlerApp {

	public static void main(String[] args) {
		//设置爬取网页的最大数量
		Crawler crawler = new Crawler(Config.Max_Page_Num);
		try {
			//开始爬数据
			System.out.println("-------------------------开始爬数据--------------------------");
			//传入开始的种子url
            crawler.crawling(Config.StartUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("爬取完之后获取数据");
		/**爬取完之后获取数据*/
		Set<String> visiteduris =  LinkQueue.getVisitedUri();
		for (String uri : visiteduris) {
			System.out.println(uri);
		}
		
		Set<String> visitedImageuris =  ImageLinkQueue.getVisitedUri();
		for (String uri : visitedImageuris) {
			System.out.println(uri);
		}
		
		System.out.println("已访问的uri的数量是：" + LinkQueue.getVisitedUriNum());
		System.out.println("未访问的uri的数量是：" + LinkQueue.getUnVistiedUriNum());
		System.out.println("已爬取的图片数量："    + ImageLinkQueue.getVisitedUriNum());
		
		System.out.println("-----------------ending---------------------------");
	}
}
