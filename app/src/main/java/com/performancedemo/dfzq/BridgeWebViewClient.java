package com.performancedemo.dfzq;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by bonree-lidong on 2017/11/3.
 */
public class BridgeWebViewClient extends WebViewClient {

    private WebViewClient webViewClient = new WebViewClient();

    public void doUpdateVisitedHistory(WebView paramWebView, String paramString, boolean paramBoolean)
    {
        this.webViewClient.doUpdateVisitedHistory(paramWebView, paramString, paramBoolean);
    }

    public void onFormResubmission(WebView paramWebView, Message paramMessage1, Message paramMessage2)
    {
        this.webViewClient.onFormResubmission(paramWebView, paramMessage1, paramMessage2);
    }

    public void onLoadResource(WebView paramWebView, String paramString)
    {
        this.webViewClient.onLoadResource(paramWebView, paramString);
    }

    @RequiresApi(api=23)
    public void onPageCommitVisible(WebView paramWebView, String paramString)
    {
        this.webViewClient.onPageCommitVisible(paramWebView, paramString);
    }

    public void onPageFinished(WebView paramWebView, String paramString)
    {
        Log.d("-->","onPageFinished");
        this.webViewClient.onPageFinished(paramWebView, paramString);
    }

    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
        Log.d("-->","onPageStarted");
        this.webViewClient.onPageStarted(paramWebView, paramString, paramBitmap);
    }

    @RequiresApi(api=21)
    public void onReceivedClientCertRequest(WebView paramWebView, ClientCertRequest paramClientCertRequest)
    {
        this.webViewClient.onReceivedClientCertRequest(paramWebView, paramClientCertRequest);
    }

    public void onReceivedError(WebView paramWebView, int paramInt, String paramString1, String paramString2)
    {
        this.webViewClient.onReceivedError(paramWebView, paramInt, paramString1, paramString2);
    }

    @RequiresApi(api=23)
    public void onReceivedError(WebView paramWebView, WebResourceRequest paramWebResourceRequest, WebResourceError paramWebResourceError)
    {
        this.webViewClient.onReceivedError(paramWebView, paramWebResourceRequest,
                                           paramWebResourceError);
    }

    public void onReceivedHttpAuthRequest(WebView paramWebView, HttpAuthHandler paramHttpAuthHandler, String paramString1, String paramString2)
    {
        this.webViewClient.onReceivedHttpAuthRequest(paramWebView, paramHttpAuthHandler,
                                                     paramString1, paramString2);
    }

    @RequiresApi(api=23)
    public void onReceivedHttpError(WebView paramWebView, WebResourceRequest paramWebResourceRequest, WebResourceResponse paramWebResourceResponse)
    {
        this.webViewClient.onReceivedHttpError(paramWebView, paramWebResourceRequest,
                                               paramWebResourceResponse);
    }

    public void onReceivedLoginRequest(WebView paramWebView, String paramString1, String paramString2, String paramString3)
    {
        this.webViewClient.onReceivedLoginRequest(paramWebView, paramString1, paramString2,
                                                  paramString3);
    }

    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
        this.webViewClient.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
    }

    @RequiresApi(api=26)
    public boolean onRenderProcessGone(WebView paramWebView, RenderProcessGoneDetail paramRenderProcessGoneDetail)
    {
        return this.webViewClient.onRenderProcessGone(paramWebView, paramRenderProcessGoneDetail);
    }

    public void onScaleChanged(WebView paramWebView, float paramFloat1, float paramFloat2)
    {
        this.webViewClient.onScaleChanged(paramWebView, paramFloat1, paramFloat2);
    }

    public void onTooManyRedirects(WebView paramWebView, Message paramMessage1, Message paramMessage2)
    {
        this.webViewClient.onTooManyRedirects(paramWebView, paramMessage1, paramMessage2);
    }

    public void onUnhandledKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent)
    {
        this.webViewClient.onUnhandledKeyEvent(paramWebView, paramKeyEvent);
    }

    public void setWebViewClient(WebViewClient paramWebViewClient)
    {

        this.webViewClient = paramWebViewClient;
    }

    @RequiresApi(api=21)
    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, WebResourceRequest paramWebResourceRequest)
    {
        return this.webViewClient.shouldInterceptRequest(paramWebView, paramWebResourceRequest);
    }

    public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString)
    {
        return this.webViewClient.shouldInterceptRequest(paramWebView, paramString);
    }

    public boolean shouldOverrideKeyEvent(WebView paramWebView, KeyEvent paramKeyEvent)
    {
        return this.webViewClient.shouldOverrideKeyEvent(paramWebView, paramKeyEvent);
    }

    @RequiresApi(api=24)
    public boolean shouldOverrideUrlLoading(WebView paramWebView, WebResourceRequest paramWebResourceRequest)
    {
        return this.webViewClient.shouldOverrideUrlLoading(paramWebView, paramWebResourceRequest);
    }

    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
        return this.webViewClient.shouldOverrideUrlLoading(paramWebView, paramString);
    }

}


