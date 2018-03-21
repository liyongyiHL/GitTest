package com.performancedemo.dfzq;

/**
 * Created by bonree-lidong on 2017/11/3.
 */
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;

class BridgeWebChromeClient extends WebChromeClient {
    private WebChromeClient webChromeClient = new WebChromeClient();

    public Bitmap getDefaultVideoPoster()
    {
        return this.webChromeClient.getDefaultVideoPoster();
    }

    public View getVideoLoadingProgressView()
    {
        return this.webChromeClient.getVideoLoadingProgressView();
    }

    public void getVisitedHistory(ValueCallback<String[]> paramValueCallback)
    {
        this.webChromeClient.getVisitedHistory(paramValueCallback);
    }

    public void onCloseWindow(WebView paramWebView)
    {
        this.webChromeClient.onCloseWindow(paramWebView);
    }

    public void onConsoleMessage(String paramString1, int paramInt, String paramString2)
    {
        this.webChromeClient.onConsoleMessage(paramString1, paramInt, paramString2);
    }

    public boolean onConsoleMessage(ConsoleMessage paramConsoleMessage)
    {
        return this.webChromeClient.onConsoleMessage(paramConsoleMessage);
    }

    public boolean onCreateWindow(WebView paramWebView, boolean paramBoolean1, boolean paramBoolean2, Message paramMessage)
    {
        return this.webChromeClient.onCreateWindow(paramWebView, paramBoolean1, paramBoolean2, paramMessage);
    }

    public void onExceededDatabaseQuota(String paramString1, String paramString2, long paramLong1, long paramLong2, long paramLong3, WebStorage.QuotaUpdater paramQuotaUpdater)
    {
        this.webChromeClient.onExceededDatabaseQuota(paramString1, paramString2, paramLong1, paramLong2, paramLong3, paramQuotaUpdater);
    }

    public void onGeolocationPermissionsHidePrompt()
    {
        this.webChromeClient.onGeolocationPermissionsHidePrompt();
    }

    public void onGeolocationPermissionsShowPrompt(String paramString, GeolocationPermissions.Callback paramCallback)
    {
        this.webChromeClient.onGeolocationPermissionsShowPrompt(paramString, paramCallback);
    }

    public void onHideCustomView()
    {
        this.webChromeClient.onHideCustomView();
    }

    public boolean onJsAlert(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
        return this.webChromeClient.onJsAlert(paramWebView, paramString1, paramString2, paramJsResult);
    }

    public boolean onJsBeforeUnload(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
        return this.webChromeClient.onJsBeforeUnload(paramWebView, paramString1, paramString2, paramJsResult);
    }

    public boolean onJsConfirm(WebView paramWebView, String paramString1, String paramString2, JsResult paramJsResult)
    {
        return this.webChromeClient.onJsConfirm(paramWebView, paramString1, paramString2, paramJsResult);
    }

    public boolean onJsPrompt(WebView paramWebView, String paramString1, String paramString2, String paramString3, JsPromptResult paramJsPromptResult)
    {
        return this.webChromeClient.onJsPrompt(paramWebView, paramString1, paramString2, paramString3, paramJsPromptResult);
    }

    public boolean onJsTimeout()
    {
        return this.webChromeClient.onJsTimeout();
    }

    @RequiresApi(api=21)
    public void onPermissionRequest(PermissionRequest paramPermissionRequest)
    {
        this.webChromeClient.onPermissionRequest(paramPermissionRequest);
    }

    @RequiresApi(api=21)
    public void onPermissionRequestCanceled(PermissionRequest paramPermissionRequest)
    {
        this.webChromeClient.onPermissionRequestCanceled(paramPermissionRequest);
    }

    public void onProgressChanged(WebView paramWebView, int paramInt)
    {
        Log.d("-->","onProgressChanged");
        this.webChromeClient.onProgressChanged(paramWebView, paramInt);
    }

    public void onReachedMaxAppCacheSize(long paramLong1, long paramLong2, WebStorage.QuotaUpdater paramQuotaUpdater)
    {
        this.webChromeClient.onReachedMaxAppCacheSize(paramLong1, paramLong2, paramQuotaUpdater);
    }

    public void onReceivedIcon(WebView paramWebView, Bitmap paramBitmap)
    {
        this.webChromeClient.onReceivedIcon(paramWebView, paramBitmap);
    }

    public void onReceivedTitle(WebView paramWebView, String paramString)
    {
        this.webChromeClient.onReceivedTitle(paramWebView, paramString);
    }

    public void onReceivedTouchIconUrl(WebView paramWebView, String paramString, boolean paramBoolean)
    {
        this.webChromeClient.onReceivedTouchIconUrl(paramWebView, paramString, paramBoolean);
    }

    public void onRequestFocus(WebView paramWebView)
    {
        this.webChromeClient.onRequestFocus(paramWebView);
    }

    public void onShowCustomView(View paramView, int paramInt, WebChromeClient.CustomViewCallback paramCustomViewCallback)
    {
        this.webChromeClient.onShowCustomView(paramView, paramInt, paramCustomViewCallback);
    }

    public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback paramCustomViewCallback)
    {
        this.webChromeClient.onShowCustomView(paramView, paramCustomViewCallback);
    }

    @RequiresApi(api=21)
    public boolean onShowFileChooser(WebView paramWebView, ValueCallback<Uri[]> paramValueCallback, WebChromeClient.FileChooserParams paramFileChooserParams)
    {
        return this.webChromeClient.onShowFileChooser(paramWebView, paramValueCallback, paramFileChooserParams);
    }

    public void setWebChromeClient(WebChromeClient paramWebChromeClient)
    {
        this.webChromeClient = paramWebChromeClient;
    }
}
