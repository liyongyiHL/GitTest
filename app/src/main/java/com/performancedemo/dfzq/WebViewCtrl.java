package com.performancedemo.dfzq;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.webkit.WebSettings;

public class WebViewCtrl {

    protected Activity activity;
    protected BridgeWebView webView;

    public WebViewCtrl(Activity paramBaseActivity, BridgeWebView paramBridgeWebView) {
        this.activity = paramBaseActivity;
        this.webView = paramBridgeWebView;
    }


    public void initWebView(){
        Log.d("<--", "WebViewCtrl initWebView");
        DefaultWebChromeClient localDefaultWebChromeClient = new DefaultWebChromeClient(this.activity);
        this.webView.setWebChromeClient(localDefaultWebChromeClient);
        DefaultWebViewClient localDefaultWebViewClient = new DefaultWebViewClient(this.activity);
        this.webView.setWebViewClient(localDefaultWebViewClient);
        initWebSettings();
    }


    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebSettings() {
        WebSettings localWebSettings = this.webView.getSettings();
        localWebSettings.setAllowFileAccess(true);
        localWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        localWebSettings.setUseWideViewPort(true);
        localWebSettings.setSupportMultipleWindows(false);
        localWebSettings.setAppCacheEnabled(true);
        localWebSettings.setDatabaseEnabled(true);
        localWebSettings.setDomStorageEnabled(true);
        localWebSettings.setJavaScriptEnabled(true);
        localWebSettings.setGeolocationEnabled(true);
        localWebSettings.setAppCacheMaxSize(104857600L);
        localWebSettings.setPluginState(WebSettings.PluginState.ON_DEMAND);
    }



}
