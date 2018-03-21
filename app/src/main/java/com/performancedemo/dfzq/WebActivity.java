package com.performancedemo.dfzq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.performancedemo.R;

public class WebActivity extends AppCompatActivity {

    protected BridgeWebView webView;
    private WebViewCtrl webViewCtrl;

    private void loadWeb(String url) {
        this.webView.loadUrl(url);
    }


    protected WebViewCtrl buildWebViewCtrl() {
        return new WebViewCtrl(this, this.webView);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (BridgeWebView) findViewById(R.id.bridgeWebView);
        this.webViewCtrl=buildWebViewCtrl();
        this.webViewCtrl.initWebView();
        loadWeb("http://itest.dfzq.com.cn/html5/financing-index/index.html");
    }

    protected void onDestroy() {
        super.onDestroy();
        this.webView.loadUrl("about:blank");
        this.webView.destroy();
    }

    protected void onRestart() {
        super.onRestart();
    }

    protected void onStart() {
        super.onStart();
        this.webView.onResume();
    }

    protected void onStop() {
        super.onStop();
        this.webView.onPause();
    }
}
