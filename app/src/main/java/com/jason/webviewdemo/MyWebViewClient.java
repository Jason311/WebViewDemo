package com.jason.webviewdemo;

import android.net.Uri;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.HashMap;
import java.util.Set;

public class MyWebViewClient extends WebViewClient {

    private final String TAG = getClass().getSimpleName();

    // 复写WebViewClient类的shouldOverrideUrlLoading方法
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // 步骤2：根据协议的参数，判断是否是所需要的url
        // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
        //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
        Uri uri = Uri.parse(url);
        Log.e(TAG, "-------url---->" + uri);
        // 如果url的协议 = 预先约定的 js 协议
        // 就解析往下解析参数
        if (uri.getScheme().equals("js")) {
            // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
            // 所以拦截url,下面JS开始调用Android需要的方法
            if (uri.getAuthority().equals("webview")) {
                //  步骤3：
                // 执行JS所需要调用的逻辑
                System.out.println("js调用了Android的方法");
                Log.e(TAG, "-------js调用了Android的方法2---->");
                // 可以在协议上带有参数并传递到Android上
                HashMap<String, String> params = new HashMap<>();
                Set<String> collection = uri.getQueryParameterNames();
            }
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

//    // 复写WebViewClient类的shouldOverrideUrlLoading方法
//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//        // 步骤2：根据协议的参数，判断是否是所需要的url
//        // 一般根据scheme（协议格式） & authority（协议名）判断（前两个参数）
//        //假定传入进来的 url = "js://webview?arg1=111&arg2=222"（同时也是约定好的需要拦截的）
////        Uri uri = Uri.parse(url);
//        Uri uri = request.getUrl();
//        Log.e(TAG, "-------url---->" + uri);
//        // 如果url的协议 = 预先约定的 js 协议
//        // 就解析往下解析参数
//        if (uri.getScheme().equals("js")) {
//            // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
//            // 所以拦截url,下面JS开始调用Android需要的方法
//            if (uri.getAuthority().equals("webview")) {
//                //  步骤3：
//                // 执行JS所需要调用的逻辑
//                System.out.println("js调用了Android的方法");
//                Log.e(TAG, "-------JS调用了Android的方法2");
//                // 可以在协议上带有参数并传递到Android上
//                HashMap<String, String> params = new HashMap<>();
//                Set<String> collection = uri.getQueryParameterNames();
//            }
//            return true;
//        }
//        return super.shouldOverrideUrlLoading(view, request);
//    }
}
