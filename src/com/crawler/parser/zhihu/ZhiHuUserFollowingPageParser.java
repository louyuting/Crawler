package com.crawler.parser.zhihu;

import com.crawler.entity.Page;
import com.crawler.parser.UserFollowingPageParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 知乎“关注的人”列表页面
 */
public class ZhiHuUserFollowingPageParser extends UserFollowingPageParser {
    /**
     * 当前类的实例，单例模式
     */
    private static ZhiHuUserFollowingPageParser zhiHuUserFollowingListPageParser;
    /**
     * 获取当前类的实例
     * @return
     */
    public static ZhiHuUserFollowingPageParser getInstance(){
        if(zhiHuUserFollowingListPageParser == null){
            zhiHuUserFollowingListPageParser = new ZhiHuUserFollowingPageParser();
        }
        return zhiHuUserFollowingListPageParser;
    }

    /**
     * 解析页面，获取到我关注的人的页面的人所关注的人的地址
     * @param page 待爬取页面的数据信息
     * @return 当前用户的关注的人的url地址的集合
     */
    @Override
    public List<String> parse(Page page) {
        //创建一个list用户存储获取到的
        List<String> followees = new ArrayList<String>(20);
        //解析html页面，获取document对象
        Document document = Jsoup.parse(page.getHtml());
        //select组合选择器，获取关注的人的<a>标签
        Elements elements = document.select(".zm-list-content-medium .zm-list-content-title a");
        for(Element element:elements){
            //通过标签，获取地址
            String userUrl = element.attr("href") + "/followees";
            followees.add(userUrl);
        }
        return followees;
    }
}
