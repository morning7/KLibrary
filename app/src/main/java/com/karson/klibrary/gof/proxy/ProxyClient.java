package com.karson.klibrary.gof.proxy;

import java.lang.reflect.Proxy;

public class ProxyClient {

    public static void main(String[] args) {
        //静态代理
//        Internet router = new RouterProxy();
//        router.httpAccess("www.facebook.com");
//        router.httpAccess("www.baidu.com");

        //动态代理
//        Internet internet = (Internet) Proxy.newProxyInstance(RouterProxy.class.getClassLoader(),
//                RouterProxy.class.getInterfaces(),
//                new BlackListFilter(new RouterProxy()));
//        internet.httpAccess("www.facebook.com");
//        internet.httpAccess("www.baidu.com");

        Intranet intranet = (Intranet) Proxy.newProxyInstance(SwitchProxy.class.getClassLoader(),
                SwitchProxy.class.getInterfaces(),
                new BlackListFilter(new SwitchProxy()));
        intranet.fileAccess("192.168");
    }
}
