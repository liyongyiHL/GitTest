package webview;

public class JS {
	public static final String JS_WEBPARSER_FILE = "function bonreeJSClass() {" +
			"var isEnd = 0 ;" +
			"var elmentEnd = 0 ;" +
			"var i=0;" +
			"var perf ;" +
			"var userAgent = \"\";" +
			"var startTimeUs = 0; " +
			"var endTimeUs = 0;" +
			"var endTimeBackUs = 0;" +
			"var domContentLoadedEventEndUs = 0;" +
			"var requestUrl = \"\";" +
			"var firstPaintTimeUs = \"\";" +
			"var unloadTimeUs = \"\";" +
			"var redirectTimeUs = \"\";" +
			"var cacheTimeUs = \"\";" +
			"var dnsTimeUs = \"\";" +
			"var connectTimeUs = \"\";" +
			"var requestTimeUs = \"\";" +
			"var responseTimeUs = \"\";" +
			"var domLoadTimeUs =\"\"; " +
			"var browserRenderTimeUs = \"\";" +
			"var responseDataText=\"\"; " +
			"var elementStr = \"\";" +
			"var elementC1 = \"@\";" +
			"var elementC2 = \"####\";" +
			"var result = [];" +

			"" +
			"bonreeInstrument = function instrumentOnError() {" +
			"function jserrorcatch(errorEvent) {" +
			"var mydate = new Date();" +
			"var crashStartTimeUs = mydate.valueOf();" +
			"if(typeof(errorEvent.filename) == \"undefined\") {" +
			"var url = \"\"" +
			"} else {" +
			"var url = errorEvent.filename" +
			"}" +
			"if(typeof(errorEvent.lineno) == \"undefined\") {" +
			"var lineNumber = -1" +
			"} else {" +
			"var lineNumber = errorEvent.lineno" +
			"}" +
			"if(typeof(errorEvent.colno == \"undefined\")) {" +
			"var col = -1" +
			"} else {" +
			"var col = errorEvent.colno" +
			"}" +
			"if(typeof(errorEvent.message) == \"undefined\") {" +
			"var errorMsg = \"\"" +
			"} else {" +
			"var errorMsg = errorEvent.message" +
			"}" +
			"if(typeof(errorEvent.error.stack) == \"undefined\") {" +
			"var allStack = \"\"" +
			"} else {" +
			"var allStack = errorEvent.error.stack" +
			"}" +
			"var href = document.location.href;" +
			"var errUserAgent = navigator.userAgent;" +
			"try{" +
			"if(elmentEnd == 0){" +
			"for(i=0,perf;i<result.length;i++){" +
			"perf=result[i];" +
			"bonreeJsBridge.javascriptStream(perf.name, perf.initiatorType, perf.duration,perf.startTime,perf.responseEnd);" +
			"}" +
			"}" +
			"}catch(e){" +
			"" +
			"}" +
			"if(isEnd == 0){" +
			"try{" +
			"bonreeJsBridge.logView(startTimeUs, endTimeUs, requestUrl, firstPaintTimeUs, unloadTimeUs, redirectTimeUs, cacheTimeUs, dnsTimeUs, connectTimeUs, requestTimeUs, responseTimeUs, domLoadTimeUs, browserRenderTimeUs, endTimeBackUs,domContentLoadedEventEndUs,userAgent,responseDataText);" +
			"}catch(e){" +
			"" +
			"}" +
			"}" +
			"bonreeJsBridge.javascriptError(errorMsg, url, lineNumber, col, allStack, href, crashStartTimeUs,errUserAgent);" +
			"}" +
			"window.addEventListener(\"error\", jserrorcatch, false);" +
			"};" +
			"bonreeInstrument();" +
			"viewMonitor = function() {" +
			"var currentView = window.location.pathname," +
			"host = window.location.host;" +
			"window.location.protocol;" +
			"result = window.performance.getEntries();" +
			"elmentEnd=0;" +
			"try{" +
			"var crashStartTimeUs1 = new Date().valueOf();" +
			"for(i=0,perf;i<result.length;i++){" +
			"perf=result[i];" +
			"elementStr = elementStr+elementC1+perf.name+elementC1+perf.initiatorType+elementC1+perf.duration+elementC1+perf.startTime+elementC1+perf.responseEnd+elementC2;" +
			//	            "bonreeJsBridge.javascriptStream(perf.name, perf.initiatorType, perf.duration,perf.startTime,perf.responseEnd);" +
			"}" +
			"}catch(e){" +
			"bonreeJsBridge.logInfo(\"\"+e.toString())" +
			"}" +
			"bonreeJsBridge.javascriptStream(\"\"+elementStr);" +
			"elmentEnd=1;" +
			"var crashStartTimeUs2 = new Date().valueOf();" +
			"bonreeJsBridge.logInfo(\"crashStartTimeUs1\"+crashStartTimeUs1);" +
			"bonreeJsBridge.logInfo(\"crashStartTimeUs2\"+crashStartTimeUs2);" +
			"setTimeout(function() {" +
			"try {" +
			"var crashStartTimeUs3 = new Date().valueOf();" +
			"bonreeJsBridge.logInfo(\"crashStartTimeUs3\"+crashStartTimeUs3);" +
			"isEnd = 0 ;" +
			"userAgent = navigator.userAgent;" +
			"startTimeUs = window.performance.timing.navigationStart;" +
			"endTimeUs = window.performance.timing.loadEventEnd;" +
			"endTimeBackUs = window.performance.timing.domComplete;" +
			"domContentLoadedEventEndUs = window.performance.timing.domContentLoadedEventEnd;" +
			"requestUrl = window.location.href;" +
			"firstPaintTimeUs = window.performance.timing.domContentLoadedEventEnd - window.performance.timing.navigationStart;" +
			"unloadTimeUs = window.performance.timing.unloadEventEnd - window.performance.timing.unloadEventStart;" +
			"redirectTimeUs = window.performance.timing.redirectEnd - window.performance.timing.redirectStart;" +
			"cacheTimeUs = window.performance.timing.responseEnd - window.performance.timing.fetchStart;" +
			"dnsTimeUs = window.performance.timing.domainLookupEnd - window.performance.timing.domainLookupStart;" +
			"connectTimeUs = window.performance.timing.connectEnd - window.performance.timing.connectStart;" +
			"requestTimeUs = window.performance.timing.responseStart - window.performance.timing.requestStart;" +
			"responseTimeUs = window.performance.timing.responseEnd - window.performance.timing.responseStart;" +
			"domLoadTimeUs = window.performance.timing.domContentLoadedEventEnd - window.performance.timing.responseStart;" +
			"browserRenderTimeUs = window.performance.timing.loadEventEnd - window.performance.timing.domContentLoadedEventEnd;" +
			"responseDataText=document.documentElement.outerHTML; " +
			"bonreeJsBridge.logView(startTimeUs, endTimeUs, requestUrl, firstPaintTimeUs, unloadTimeUs, redirectTimeUs, cacheTimeUs, dnsTimeUs, connectTimeUs, requestTimeUs, responseTimeUs, domLoadTimeUs, browserRenderTimeUs, endTimeBackUs,domContentLoadedEventEndUs,userAgent,responseDataText);" +
			"isEnd = 1 ;" +
			"var crashStartTimeUs4 = new Date().valueOf();" +
			"bonreeJsBridge.logInfo(\"crashStartTimeUs4\"+crashStartTimeUs4);" +
			"} catch(e) {" +
			"" +
			"}" +
			"}, 50)" +
			"};" +
			"viewMonitor();" +
			"};";
}
