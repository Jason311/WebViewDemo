package com.jason.webviewdemo;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

/**
 * FileName: NativeCallJsActivity
 *
 * @Date 2018/12/21 15:54
 * @Author Jason jianbo
 * @org www.hopson.com
 * @Email jianbo311@163.com
 * @Version
 * @Desc Android调用javaScript中方法
 */
public class NativeCallJsActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    WebView mWebView;
    Button mCallJsBtn1, mCallJsBtn2, mCallJsBtn3;

    // Android版本变量
    final int version = Build.VERSION.SDK_INT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_call_js);

        mWebView = (WebView) findViewById(R.id.webView);
        mCallJsBtn1 = (Button) findViewById(R.id.androidCallJs_btn1);
        mCallJsBtn2 = (Button) findViewById(R.id.androidCallJs_btn2);
        mCallJsBtn3 = (Button) findViewById(R.id.androidCallJs_btn3);
        mCallJsBtn1.setOnClickListener(this);
        mCallJsBtn2.setOnClickListener(this);
        mCallJsBtn3.setOnClickListener(this);

        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
        mWebView.loadUrl("file:///android_asset/javascript1.html");

//        mWebView.loadUrl("file:///android_asset/paysuccess.html");
        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(NativeCallJsActivity.this);
                dialogBuilder.setTitle("Alert");
                dialogBuilder.setMessage(message);
                dialogBuilder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                dialogBuilder.setCancelable(false);
                dialogBuilder.create().show();
                return true;
            }
        });
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.androidCallJs_btn1:
                // 通过Handler发送消息
                mWebView.post(new Runnable() {
                    @Override
                    public void run() {
                        // 注意调用的JS方法名要对应上
                        // 调用javascript的callJS()方法
                        mWebView.loadUrl("javascript:callJS()");
                    }
                });
                break;
            case R.id.androidCallJs_btn2:
                // 只需要将第一种方法的loadUrl()换成下面该方法即可
                mWebView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //此处为 js 返回的结果
                        Log.e(TAG, "callback--->" + value);
                    }
                });
                break;
            case R.id.androidCallJs_btn3:
                // 因为该方法在 Android 4.4 版本才可使用，所以使用时需进行版本判断
                if (version < 18) {
                    mWebView.loadUrl("javascript:callJS()");
                } else {
                    mWebView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            //此处为 js 返回的结果
                            Log.e(TAG, "callback--->" + value);
                        }
                    });
                }
                break;
            default:
                break;
        }
    }
}
