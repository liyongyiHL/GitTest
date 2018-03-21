package com.performancedemo.jgj;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.performancedemo.R;

/**
 * Created by bonree-lidong on 2017/11/29.
 */
public class PAWebView extends RelativeLayout implements IPAWebView {

    private Context context;
    private View view;
    private ObservableWebView webView;

    public PAWebView(Context paramContext)
    {
        super(paramContext);
        this.context = paramContext;
        init();
    }

    public PAWebView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        this.context = paramContext;
        init();
    }

    public PAWebView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        this.context = paramContext;
        init();
    }

    private void init()
    {
        this.view = View.inflate(this.context, R.layout.palife_webview, this);
        initView();
    }

    private void initView()
    {
        this.webView = ((ObservableWebView)this.view.findViewById(R.id.webView));
    }

    @Override
    public WebView getWebView()
    {
        return this.webView;
    }

    @Override
    public RelativeLayout getExceptionLayout() {
        return null;
    }

    @Override
    public RelativeLayout getFootLayout() {
        return null;
    }

    @Override
    public RelativeLayout getHeadLayout() {
        return null;
    }

    @Override
    public RelativeLayout getLoadingHeadLayout() {
        return null;
    }

    @Override
    public RelativeLayout getLoadingLayout() {
        return null;
    }

    @Override
    public RelativeLayout getWebViewBg() {
        return null;
    }
}
