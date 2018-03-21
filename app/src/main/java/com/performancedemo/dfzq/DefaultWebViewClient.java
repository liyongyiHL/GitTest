package com.performancedemo.dfzq;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DefaultWebViewClient extends WebViewClient {

    public DefaultWebViewClient(Activity activity) {

    }

    public void onLoadResource(WebView paramWebView, String paramString) {
        super.onLoadResource(paramWebView, paramString);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap) {
        Log.d("<--","DefaultWebViewClient onPageStarted");
        super.onPageStarted(paramWebView, paramString, paramBitmap);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        Log.d("<--","DefaultWebViewClient onPageFinished");
        super.onPageFinished(view, url);
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
        super.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }

    @RequiresApi(api=23)
    public void onReceivedError(WebView paramWebView, WebResourceRequest paramWebResourceRequest, WebResourceError paramWebResourceError)
    {
        super.onReceivedError(paramWebView, paramWebResourceRequest, paramWebResourceError);
    }

    @RequiresApi(api=21)
    public void onReceivedHttpError(WebView paramWebView, WebResourceRequest paramWebResourceRequest, WebResourceResponse paramWebResourceResponse)
    {
        super.onReceivedHttpError(paramWebView, paramWebResourceRequest, paramWebResourceResponse);
        int i = paramWebResourceResponse.getStatusCode();
    }

    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, WebResourceRequest paramWebResourceRequest)
    {
        return super.shouldInterceptRequest(paramWebView, paramWebResourceRequest);
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
        return super.shouldOverrideUrlLoading(paramWebView, paramString);
    }

}
