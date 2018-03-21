package com.performancedemo.jgj;


import android.content.Context;
import android.webkit.WebView;
import android.widget.RelativeLayout;

/**
 * Created by bonree-lidong on 2017/11/29.
 */
public abstract interface IPAWebView {

    public abstract Context getContext();

    public abstract RelativeLayout getExceptionLayout();

    public abstract RelativeLayout getFootLayout();

    public abstract RelativeLayout getHeadLayout();

    public abstract RelativeLayout getLoadingHeadLayout();

    public abstract RelativeLayout getLoadingLayout();

    public abstract WebView getWebView();

    public abstract RelativeLayout getWebViewBg();
}
