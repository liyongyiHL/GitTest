package com.performancedemo.dfzq;

import android.app.Activity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by bonree-lidong on 2017/11/6.
 */
public class DefaultWebChromeClient extends WebChromeClient {


    public DefaultWebChromeClient(Activity activity) {

    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        Log.d("-->", "DefaultWebChromeClient onProgressChanged");
        super.onProgressChanged(view, newProgress);
    }
}
