package com.karson.klibrary.gof.proxy;

public class Modern implements Internet {

    public Modern(String password) {
        if (!"123456".equals(password)) {
            System.out.println("password is error");
            return;
        }
        System.out.println("连接网络成功");
    }


    @Override
    public void httpAccess(String url) {
        System.out.println("请求链接成功 " + url);
    }
}
