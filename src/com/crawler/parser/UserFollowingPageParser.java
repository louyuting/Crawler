package com.crawler.parser;

import com.crawler.entity.Page;

import java.util.List;

public abstract class UserFollowingPageParser implements Parser {
    /**
     * 需要传入Page对象
     * @param page
     * @return
     */
    public abstract List<String> parse(Page page);
}
