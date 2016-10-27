package com.crawler.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

//关于打印的工具类
public class PrintUtil {
	
	public static <T> void println(T obj){
		System.out.println(obj.toString());
	}
	
	/**
	 * function：一级输出集合： 输出集合中的每一项信息，该集合的类型是基础类型，如List<String>
	 * @param collection
	 */
	public static <E> void printList(Collection<E> collection) {
		
		System.out.println("该集合的大小为：" + collection.size());
		for (E e : collection) {
			System.out.println(e);
		}
	}
	
	//测试
	@Test
	public void test_printList(){
		List<String> lists = new ArrayList<String>();
		lists.add("aaa");
		lists.add("bbb");
		lists.add("ccc");
		lists.add("ddd");
		lists.add("eee");
		lists.add("fff");
		
		printList(lists);
	}

}
