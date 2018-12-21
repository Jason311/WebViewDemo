package com.jason.webviewdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private ConstraintLayout rootLayout;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        initView();
        initData();
//        webViewStatus();
//        goForwardAndBack();
        clearWebViewCache();
    }

    private void initView() {
        rootLayout = findViewById(R.id.container);
        mWebView = findViewById(R.id.webView);
    }

    private void initData() {
        //方式1. 加载一个网页：
//        mWebView.loadUrl("http://www.baidu.com/");

        //方式2：加载apk包中的html页面
//        mWebView.loadUrl("file:///android_asset/test.html");
//        String url = "file:///android_asset/test.html";
        String url = "file:///android_asset/haha.html";
        Log.e(TAG, "----url----->" + url);
        mWebView.loadUrl(url);
//        mWebView.loadUrl("file:////android_asset/test.html");

//        //方式3：加载手机本地的html页面
//        mWebView.loadUrl("content://com.android.htmlfileprovider/sdcard/test.html");

//        // 方式4： 加载 HTML 页面的一小段内容
//        WebView.loadData(String data, String mimeType, String encoding);
//        // 参数说明：
//        // 参数1：需要截取展示的内容
//        // 内容里不能出现 ’#’, ‘%’, ‘\’ , ‘?’ 这四个字符，若出现了需用 %23, %25, %27, %3f 对应来替代，否则会出现异常
//        // 参数2：展示内容的类型
//        // 参数3：字节码
    }

    /**
     * WebView的状态
     */
    private void webViewStatus() {
        //激活WebView为活跃状态，能正常执行网页的响应
        mWebView.onResume();

        //当页面被失去焦点被切换到后台不可见状态，需要执行onPause
        //通过onPause动作通知内核暂停所有的动作，比如DOM的解析、plugin的执行、JavaScript执行。
        mWebView.onPause();

        //当应用程序(存在webview)被切换到后台时，这个方法不仅仅针对当前的webview
        //而是全局的全应用程序的webview 它会暂停所有webview的layout，parsing，javascripttimer。降低CPU功耗。
        mWebView.pauseTimers();

        //恢复pauseTimers状态
        mWebView.resumeTimers();

        rootLayout.removeView(mWebView);
        //销毁Webview
        //在关闭了Activity时，如果Webview的音乐或视频，还在播放。就必须销毁Webview
        //但是注意：webview调用destory时,webview仍绑定在Activity上
        //这是由于自定义webview构建时传入了该Activity的context对象
        //因此需要先从父容器中移除webview,然后再销毁webview:
        rootLayout.removeView(mWebView);
        mWebView.destroy();
    }

    /**
     * 关于前进 / 后退网页
     */
    private void goForwardAndBack() {
        //是否可以后退
        mWebView.canGoBack();
        //后退网页
        mWebView.goBack();

        //是否可以前进
        mWebView.canGoForward();
        //前进网页
        mWebView.goForward();
        //以当前的index为起始点前进或者后退到历史记录中指定的steps
        //如果steps为负数则为后退，正数则为前进
        mWebView.goBackOrForward(10);
    }

    /**
     * 清除缓存相关
     */
    private void clearWebViewCache() {
        //清除网页访问留下的缓存
        //由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序.
        mWebView.clearCache(true);

        //清除当前webview访问的历史记录
        //只会webview访问历史记录里的所有记录除了当前访问记录
        mWebView.clearHistory();

        //这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
        mWebView.clearFormData();
    }
}
