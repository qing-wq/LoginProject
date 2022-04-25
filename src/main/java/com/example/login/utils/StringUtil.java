package com.example.login.utils;

/*
* 字符串工具类
* 判断字符串是否为为空*/

public class StringUtil {
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str.trim())) {
            return true;
        }
        return false;
    }
}
