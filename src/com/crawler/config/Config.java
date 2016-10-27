package com.crawler.config;

public class Config {
	
	/** 定义爬虫的种子,也就是最开始爬的链接,必须是某个用户关注的人的 followees 页面 **/
	public static final String StartUrl = "https://www.zhihu.com/people/lou-yu-ting-71/followees";
    /** 爬取网页的最大数量 */
    public static final Integer Max_Page_Num = 10000;

    /** 数据库相关的定义 */
    public static final String DB_Url="jdbc:mysql://120.76.242.204:3306/crawler";
    public static final String DB_Username="root";
    public static final String DB_Password="vstar123";

    /** 知乎的用户名和密码 */
    //_xsrf
    public static final String _xsrf = "59555f20dea157ea18a37d079df63883";
    //email
    public static final String ZhiHu_email="1849491904@qq.com";
    //password
    public static final String ZhiHu_password="lw199394,.";
    //remember_me=true
    //captcha 验证码

    public static final String Cookie = "d_c0=\"AGAAm3ZhtAqPTpEWwM5eslgb4-KY0DAQzRU=|1476683836\"; q_c1=4f6c4bcf4e634acfb84b2b5228b14d05|1477320541000|1477320541000; _zap=74e4fa81-1f07-431b-8707-ad1e735653ec; _xsrf=59555f20dea157ea18a37d079df63883; l_n_c=1; login=\"YzAxMzEzNzFhZjlmNDVkMGE1OWQxZDAwZjFmZjkxYWM=|1477506857|0c084fddb98b218e7202207599aad3e8daf3def3\"; l_cap_id=\"MzVlNTVkN2Q1ZGMxNDU3MGEyNjg1MzRjNzM4MzYzOTU=|1477506857|449207e328bd97cb1cdb5afaf778e45cd2aa826d\"; cap_id=\"MDhjN2Q3NDg2N2FkNDg4OGFhYTQxN2U2MzY1MjI3OWY=|1477506857|c20838c424dbb6b573917385aeb1c2b6d9ce55fe\"; __utmt=1; a_t=\"2.0AACAeagnAAAXAAAA-oQ4WAAAgHmoJwAAAGAAm3ZhtAoXAAAAYQJVTS2EOFgANEsvv7dHWYQ31SQ4IQTf1ZovYhqVMzHrmJpDBUYllIUGN99mOKrtPw==\"; z_c0=Mi4wQUFDQWVhZ25BQUFBWUFDYmRtRzBDaGNBQUFCaEFsVk5MWVE0V0FBMFN5LV90MGRaaERmVkpEZ2hCTl9WbWk5aUdn|1477507066|c9499cb3e30bfb8f7fc30c1b05164e4b89746856; __utma=51854390.1304687913.1477444795.1477506104.1477506481.7; __utmb=51854390.22.10.1477506481; __utmc=51854390; __utmz=51854390.1477506481.7.7.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); __utmv=51854390.100-1|2=registration_date=20140306=1^3=entry_date=20140306=1";



}
