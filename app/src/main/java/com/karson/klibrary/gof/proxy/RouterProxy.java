package com.karson.klibrary.gof.proxy;

import java.util.Arrays;
import java.util.List;

/**
 * 路由器
 */
public class RouterProxy implements Internet {

    public Internet mModern;

//    private final List<String> blackList = Arrays.asList("twitter", "facebook");

    public RouterProxy() {
        mModern = new Modern("123456");
    }

    @Override
    public void httpAccess(String url) {
//        for(String keyword: blackList) {
//            if (url.contains(keyword)) {
//                System.out.println("禁止访问 " + url);
//                return;
//            }
//        }
        mModern.httpAccess(url);
    }
}
