package com.crawler.parser;

import com.crawler.entity.Page;
import com.crawler.entity.User;

public abstract class UserInfoPageParser implements Parser {
    public abstract User parse(Page page);
}
