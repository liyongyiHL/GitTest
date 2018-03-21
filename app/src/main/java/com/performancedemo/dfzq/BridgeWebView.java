package com.performancedemo.dfzq;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bonree.agent.android.instrumentation.WebViewInstrumentation;

public class BridgeWebView extends WebView {

    private BridgeWebChromeClient bridgeWebChromeClient;
    private BridgeWebViewClient bridgeWebViewClient;

    public BridgeWebView(Context paramContext) {
        super(paramContext);
        init();
    }

    public BridgeWebView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init();
    }

    public BridgeWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init();
    }

    @RequiresApi(api=21)
    public BridgeWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
        super(paramContext, paramAttributeSet, paramInt1, paramInt2);
        init();
    }

    @Deprecated
    public BridgeWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt, boolean paramBoolean)
    {
        super(paramContext, paramAttributeSet, paramInt, paramBoolean);
        init();
    }

    private void init() {
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 19)
            WebView.setWebContentsDebuggingEnabled(true);
        Log.d("<--","BridgeWebView init");
        this.bridgeWebViewClient = new BridgeWebViewClient();
        this.bridgeWebChromeClient = new BridgeWebChromeClient();
        super.setWebViewClient(this.bridgeWebViewClient);
        super.setWebChromeClient(this.bridgeWebChromeClient);
    }


    public void setWebChromeClient(WebChromeClient paramWebChromeClient) {
        this.bridgeWebChromeClient.setWebChromeClient(paramWebChromeClient);
    }

    public void setWebViewClient(WebViewClient paramWebViewClient) {
        Log.d("<--","BridgeWebView setWebViewClient");
        this.bridgeWebViewClient.setWebViewClient(paramWebViewClient);
    }
}
