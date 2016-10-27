package com.crawler.parser.zhihu;

import com.crawler.entity.Page;
import com.crawler.entity.User;
import com.crawler.parser.UserInfoPageParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * Created by yangwang on 16-8-24.
 * 知乎用户首页解析获取当前用户的信息
 */
public class ZhiHuUserInfoPageParser extends UserInfoPageParser {
    /**
     * 单例获取解析器解析网页
     */
    private static ZhiHuUserInfoPageParser zhiHuUserInfoPageParser;
    public static ZhiHuUserInfoPageParser getInstance(){
        if(zhiHuUserInfoPageParser == null){
            zhiHuUserInfoPageParser = new ZhiHuUserInfoPageParser();
        }
        return zhiHuUserInfoPageParser;
    }

    /**
     * 传入一个page页面的信息，获取当前用户的信息
     * @param page
     * @return User 当前用户的信息
     */
    @Override
    public User parse(Page page) {
        Document document = Jsoup.parse(page.getHtml());
        return parseUserInfo(document);
    }
    /**
     * 解析个人资料
     * @param document
     * @return
     */
    private User parseUserInfo(Document document){
        User user = new User();
        user.setLocation(getUserinfo(document,"location"));//位置
        user.setBusiness(getUserinfo(document,"business"));//行业
        user.setEmployment(getUserinfo(document,"employment"));//企业
        user.setEducation(getUserinfo(document,"education"));//教育
        user.setUsername(document.select(".title-section a").first().text());//用户名
        user.setUrl("https://www.zhihu.com" + document.select(".title-section a").first().attr("href"));//用户首页链接
        user.setAgrees(Integer.valueOf(document.select(".zm-profile-header-user-agree strong").first().text()));//赞同数
        user.setThanks(Integer.valueOf(document.select(".zm-profile-header-user-thanks strong").first().text()));//感谢数
        user.setFollowees(Integer.valueOf(document.select(".zm-profile-side-following strong").first().text()));//关注人数
        user.setFollowers(Integer.valueOf(document.select(".zm-profile-side-following strong").get(1).text()));//被关注人数
        try {
            user.setHashId(document.select(".zm-profile-header-op-btns.clearfix button").first().attr("data-id"));
        }catch (NullPointerException e){
            //解析我的主页时，会出现空指针
            user.setHashId("843df56056dc14b8dd36ace99be09337");
        }
        return user;
    }

    /**
     * 获取用户个人资料
     * @param document 传入文档对象
     * @param infoName 获取信息的类名
     * @return 返回
     */
    private String getUserinfo(Document document,String infoName){
        Element element = document.select(".zm-profile-header-user-describe ." + infoName + " .item").first();
        if(element == null){
            return "";
        } else{
            return element.attr("title");
        }
    }
}
