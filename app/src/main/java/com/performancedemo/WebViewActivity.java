package com.performancedemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class WebViewActivity extends Activity {

    public  String  url;
    private WebView mWebview;
    private Button  mreload;
    private static final String TAG = "WebViewInstrumentation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebview = (WebView) findViewById(R.id.webview);
        mreload = (Button) findViewById(R.id.reload);

        mreload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebview.reload();
            }
        });

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d("<--", "onPageStarted");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("<--","onPageFinished");
                super.onPageFinished(view, url);
//                WebViewInstrumentation.webViewPageFinished(WebViewActivity.class, view);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        };

        WebChromeClient webChromeClient = new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.d("<--","onProgressChanged:"+newProgress);
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(WebViewActivity.this)
                        .setTitle("javaScript dialog")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {
                             public void onClick(DialogInterface dialog, int which) {
                                   result.confirm();
                             }
                        }).setCancelable(false).create().show();
                return true;
            }
        };

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.setWebChromeClient(webChromeClient);
        mWebview.setWebViewClient(webViewClient);
        mWebview.loadUrl("http://www.bonree.com");
//        mWebview.loadUrl("http://122.11.58.130:9099/sdk/");
//        mWebview.loadUrl("file:///android_asset/test.html");
//        mWebview.loadUrl("https://my.oschina.net/xxiaobian/blog/1563514");
//        mWebview.loadUrl("https://m.ctrip.com/html5/Flight/?ouid=XKD-BNM_17111727132007857671&allianceid=262691&sid=711472&sourceid=2055&popup=close&autoawaken=close");
    }

}
