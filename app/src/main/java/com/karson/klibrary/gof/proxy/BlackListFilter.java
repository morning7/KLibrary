package com.karson.klibrary.gof.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class BlackListFilter implements InvocationHandler  {

    private final List<String> blackList = Arrays.asList("twitter", "facebook");

    private Object mOrigin;

    public BlackListFilter(Object origin) {
        mOrigin = origin;
        System.out.println("开启黑名单过滤机制");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String arg = args[0].toString();
        for (String keyword : blackList) {
            if (arg.contains(keyword)) {
                System.out.println("禁止访问 " + arg);
                return null;
            }
        }
        System.out.println("校验通过，转向实际业务");
        return method.invoke(mOrigin, arg);
    }
}
