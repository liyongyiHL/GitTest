package webview;

import java.lang.reflect.Method;

import android.os.Build;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewInstrumentation {

	private final static String TAG = "WebViewInstrumentation";
	public static String mClassName = "";
	
	public static void setsetWebViewClient(WebView paramWebView, WebViewClient paramWebViewClient){
		if ((paramWebView == null) || (paramWebViewClient == null))
	      return;
	    paramWebView.setWebViewClient(paramWebViewClient);
	    if (Build.VERSION.SDK_INT < 19) {
			Log.d(TAG,
					"This interface is only available for Android 4.4 or later.");
			return;
		}
	    try{
	    	webViewPageStart(paramWebView);
	    }catch(Throwable throwable){
	    	
	    }
	  }
	
	public static void webViewPageStart(WebView view) {
		Log.d(TAG, "Set Javascript exception monitor of webview.");
		if (view == null) {
			Log.d(TAG, "Webview is null.");
			return;
		}
		if (!(view.getSettings()).getJavaScriptEnabled()) {
			view.getSettings().setJavaScriptEnabled(true);
		}
		BonreeJavaScriptBridge bonreeJavaScriptBridge = new BonreeJavaScriptBridge(view);
		if (bonreeJavaScriptBridge != null) {
			Log.d(TAG, "Add a secure javascript interface to the webview.");
			Object webivewProvider = new Reflect(view).field("mProvider").type(WebView.class)
					.out(Object.class);
			new Reflect(webivewProvider).method("addJavascriptInterface",Object.class,String.class).
			invoke(Object.class,bonreeJavaScriptBridge,"bonreeJsBridge");
		}
	}

	public static void webViewPageFinished(Object className,WebView view) {
		Log.d(TAG, "Inject Bonree.js to the webview.");
		if (view == null) {
			Log.d(TAG, "Webview is null.");
			return;
		}
		if (view.getUrl() == null||className == null) {
			return;
		}
		if (Build.VERSION.SDK_INT < 19) {
			Log.d(TAG,"This interface is only available for Android 4.4 or later.");
			return;
		}
		if (!(view.getSettings()).getJavaScriptEnabled()) {
			Log.d(TAG,"JavaScriptEnabled false");
			return;
		}
		mClassName = ((Class<?>)className).getSimpleName();
		BonreeJavaScriptBridge.setWebViewName(mClassName);
		try{
			Method method = WebView.class.
					getDeclaredMethod("evaluateJavascript", String.class, ValueCallback.class);
			method.setAccessible(true);
			method.invoke(view,  "javascript:" +JS.JS_WEBPARSER_FILE +"bonreeJSClass()", null);
		}catch(Exception exception){
			
		}
		Log.d(TAG, "inject Bonree.js Successed");
	}
}
