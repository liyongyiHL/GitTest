package webview;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/**
 * Created by bonree-lidong on 2016/8/15.
 */

public class BonreeJavaScriptBridge {

	private static final String TAG = "<==";
	public WebView webview;
	public Thread localThread;
	public StringBuilder localStringBuilder;
	public int lengthLimit;
	public static String mWebViewName;
	public int mResonableInt;
	public String mPackageName;
	public String mStackString;
	public String mClassName;
	public static ArrayList<String> startTimeList = new ArrayList<String>();
	
	public BonreeJavaScriptBridge(WebView webView) {
		this.webview = webView;
	}

	public String getString() {
		localStringBuilder = new StringBuilder();
		localStringBuilder.append("\n");
		lengthLimit = 2;
		if (lengthLimit >= localThread.getStackTrace().length) {
			return null;
		}
		for (int j = 0; j < localThread.getStackTrace().length; j++) {
			boolean crashreport = localThread.getStackTrace()[lengthLimit]
					.toString().contains("crashreport");
			if (crashreport) {
				continue;
			}
			localStringBuilder.append(localThread.getStackTrace()[j].toString()).append("\n");
		}
		return localStringBuilder.toString();
	}

	public static void setWebViewName(String className) {
		mWebViewName = "WebView@" + className;
	}

	public void getClassName(String mStackString, String mPackageName) {
		if (mStackString == null || mPackageName==null) {
			return;
		}
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new ByteArrayInputStream(mStackString.getBytes(Charset
							.forName("utf8"))), Charset.forName("utf8")));
			String line = null;
			String ClassLine = null;
			while ((line = br.readLine()) != null) {
				if (line.startsWith(mPackageName)) {
					ClassLine = line;
					break;
				}
			}
			if (ClassLine != null) {
				String[] split = ClassLine.split("\\(");
				String string = split[split.length - 1];
				String[] split2 = string.split("\\.");
				mClassName = split2[0];
			}
		} catch (Exception e) {
			mClassName = "";
			e.printStackTrace();
		}
	}

	@JavascriptInterface
	public void javascriptError(String causedBy, String url, String lineNumber,
			String columnNumber, String errordump, String href,
			long crashStartTimeUs, String errUserAgent) {

	}

	@JavascriptInterface
	public void logView(long startTimeUs, long endTimeUs, String requestUrl,
			String firstPaintTimeUs, String unloadTimeUs,
			String redirectTimeUs, String cacheTimeUs, String dnsTimeUs,
			String connectTimeUs, String requestTimeUs, String responseTimeUs,
			String domLoadTimeUs, String browserRenderTimeUs, long endTimeBackUs, long domContentLoadedEventEndUs, String userAgent,String responseDataText) {
//		Log.d("==>", "responseDataText-->" + responseDataText);
	}

	@JavascriptInterface
	public void javascriptStream(String url, String type, String duration,
			double startTime, double endTime) {
//		Log.d(TAG, "javascriptStream-->" + url);
//		long iii = SystemClock.uptimeMillis();
//		long startTimeUS = Math.round(startTime+0.5);
//		long endTimeUS = Math.round(endTime+0.5);
//		JavascriptStreamBean javascriptStreamBean = new JavascriptStreamBean(
//				url, type, duration, startTimeUS, endTimeUS);
//		WebviewElementsPb.setWebviewElementsPbList(javascriptStreamBean);
//		Log.d(TAG, "spend-->" + (SystemClock.uptimeMillis() - iii));
	}
	
	
	@JavascriptInterface
	public void javascriptStream(String url) {
		Log.d(TAG, "javascriptStream-->" + url);


	}

	@JavascriptInterface
	public void logDebug(String debug) {
//		Log.d(TAG, "logDebug-->" + debug);
//		log.info(TAG + "logDebug-->" + debug);
	}

	@JavascriptInterface
	public void logInfo(String debug) {
		Log.d(TAG, "logInfo-->" + debug);
//		log.info(TAG + "logInfo-->" + debug);
	}

	@JavascriptInterface
	public void adrdMethod() {
//		Log.d(TAG, "js调用了Android方法,获得堆栈：" + localStringBuilder.toString());
//		Log.d(TAG, "adrdMethod-->" + localStringBuilder.toString());
//		log.info(TAG + "adrdMethod-->" + localStringBuilder.toString());
	}
}
