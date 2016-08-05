package utils;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Queue;

/**
 * 用Set集合存储已访问链接；Queue队列存储未访问链接
 * 因为set集合里面的元素是不能重复的，所以我们用set集合来记录已经访问过的uri
 * @author LL
 */
@SuppressWarnings("unchecked")
public class LinkQueue{
	//已访问的uri集合
	@SuppressWarnings("rawtypes")
	private static Set visitedUri = new HashSet();
	
	//待访问的uri集合，我们爬取网站采用图的宽度遍历的思想，所以需要使用队列模拟
	@SuppressWarnings("rawtypes")
	private static Queue unVisitedUri = new PriorityQueue();

//==静态方法================================================================
	//获取待访问的uri队列
	@SuppressWarnings("rawtypes")
	public static Queue getUnVisitedUri() {
		return unVisitedUri;
	}
	
	//获取已访问的uri集合
	@SuppressWarnings("rawtypes")
	public static Set getVisitedUri(){
		return visitedUri;
	}
	
	//获得已访问过uri的数目
	public static int getVisitedUriNum() {
		return visitedUri.size();
	}
	
	//获得未访问过uri队列的数目
	public static int getUnVistiedUriNum(){
		return unVisitedUri.size();
	}
	
	//判断未访问的uri 队列是否为空
	public static boolean unVisitedUriIsEmpty() {
		return unVisitedUri.isEmpty();
	}
	
	//将uri添加到已访问过的uri队列中
	public static void addVisitedUri(String uri) {
		//如果set中已经包含了该元素，则此调用不改变此set；并返回false
		visitedUri.add(uri);
	}
	
	//移除已访问过的uri
	public static void removeVisitedUri(String uri) {
		visitedUri.remove(uri);
	}
	
	//未访问的uri出队列
	public static Object unVisitedUriDeQueue() {
		//获取并删除队头
		return unVisitedUri.poll();
	}
	
	//将uri添加到未访问队列中
	public static void addUnvisitedUri(String uri) {
		//trim() :返回字符串的副本，忽略前导空白和尾部空白。
		//uri非空，且不为空格，visited表中不包括，未访问表中也不包括当前链接
		if (uri != null && !uri.trim().equals("") && !visitedUri.contains(uri) && !unVisitedUri.contains(uri))
			unVisitedUri.add(uri);
	}

}
