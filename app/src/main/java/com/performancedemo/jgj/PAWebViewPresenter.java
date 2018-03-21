package com.performancedemo.jgj;

/**
 * Created by bonree-lidong on 2017/11/29.
 *
 */
public class PAWebViewPresenter {

    private IPAWebView iWebView;
    private PAWebViewClient paWebViewClient;

    public PAWebViewPresenter(IPAWebView paramIPAWebView)
    {
        this.iWebView = paramIPAWebView;
        this.paWebViewClient = new PAWebViewClient();
        if ((paramIPAWebView != null) && (paramIPAWebView.getWebView() != null))
        {
            paramIPAWebView.getWebView().getSettings().setCacheMode(2);
            paramIPAWebView.getWebView().getSettings().setJavaScriptEnabled(true);
            paramIPAWebView.getWebView().setWebViewClient(this.paWebViewClient);
        }
    }

}
