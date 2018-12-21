package com.jason.webviewdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import java.util.HashMap;
import java.util.Set;

/**
 * FileName: JsCallNativeActivity
 *
 * @Date 2018/12/21 13:52
 * @Author Jason jianbo
 * @org www.hopson.com
 * @Email jianbo311@163.com
 * @Version
 * @Desc JavaScript调用Android代码
 */
public class JsCallNativeActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    WebView mWebView;
    Button mCallAmdroidBtn1, mCallAmdroidBtn2, mCallAmdroidBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_native);

        mWebView = (WebView) findViewById(R.id.webView);
        mCallAmdroidBtn1 = (Button) findViewById(R.id.jsCallAndroid_btn1);
        mCallAmdroidBtn2 = (Button) findViewById(R.id.jsCallAndroid_btn2);
        mCallAmdroidBtn3 = (Button) findViewById(R.id.jsCallAndroid_btn3);
        mCallAmdroidBtn1.setOnClickListener(this);
        mCallAmdroidBtn2.setOnClickListener(this);
        mCallAmdroidBtn3.setOnClickListener(this);

        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.jsCallAndroid_btn1:
                // 通过addJavascriptInterface()将Java对象映射到JS对象
                //参数1：Javascript对象名
                //参数2：Java对象名
                mWebView.addJavascriptInterface(new AndroidtoJs(), "test");//AndroidtoJS类对象映射到js的test对象
                // 加载JS代码
                // 格式规定为:file:///android_asset/文件名.html
                mWebView.loadUrl("file:///android_asset/javascript2.html");
                break;
            case R.id.jsCallAndroid_btn2:
                // 步骤1：加载JS代码
                // 格式规定为:file:///android_asset/文件名.html
                mWebView.loadUrl("file:///android_asset/javascript3.html");
                mWebView.setWebViewClient(new MyWebViewClient());
//                mWebView.loadUrl("javascript:returnResult(" + result + ")");
                break;
            case R.id.jsCallAndroid_btn3:
                // 加载JS代码
                // 格式规定为:file:///android_asset/文件名.html
                mWebView.loadUrl("file:///android_asset/javascript4.html");
                mWebView.setWebChromeClient(new MyWebChromeClient());
                break;
            default:
                break;
        }
    }
}
