package com.performancedemo.jgj;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by bonree-lidong on 2017/11/29.
 */
public class ObservableWebView extends WebView {
    public ObservableWebView(Context paramContext)
    {
        super(paramContext);
    }

    public ObservableWebView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public ObservableWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
    }

    @TargetApi(21)
    public ObservableWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
    {
        super(paramContext, paramAttributeSet, paramInt1, paramInt2);
    }


}
