package com.jason.webviewdemo;

import android.util.Log;
import android.webkit.JavascriptInterface;

// 继承自Object类
public class AndroidtoJs {

    private final String TAG = getClass().getSimpleName();

    // 定义JS需要调用的方法
    // 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void hello(String msg) {
        System.out.println("JS调用了Android的hello方法");
        Log.e(TAG, "-------JS调用了Android的hello方法");
    }
}