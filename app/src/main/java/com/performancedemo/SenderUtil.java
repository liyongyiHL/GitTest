package com.performancedemo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SenderUtil {
	
	public static String[] netKeyArr = {"ru","si","li","lp","ti","tp","st","dt","ct","sti","rt","rti","dti","et","rh","rd","rhe","rds","ei","se","ib","mt","dsi","rg","rgu","iw","lc","am","s","nt"};
	public static String[] webKeyArr = {"st","et","ru","wn","fp","ut","rt","ct","dt","cti","rti","rtim","dti","brt","rds","cc"};
	public static String[] threadKeyArr ={"et","st","ti","t","n"};
	public static String[] methodKeyArr = {"st","et","n","t","ru","rg","rgu"};
	public static String[] memoryKeyArr = {"st","am","ac"};
	
	public static String parseGson(String jsonString){
		try {
			//将SDKRequestBean json String 转为 JSONObject
			JSONObject SDKRequestJsonObject = new JSONObject(jsonString);
			//获取SDKRequestBean 中的 UploadDataRequest
			JSONObject udrJsonObject = SDKRequestJsonObject.getJSONObject("udr");
			//获取UploadDataRequest中的UploadData
			JSONArray uploadsJsonArray = udrJsonObject.getJSONArray("d");
			//获取UploadData的个数
			int length = uploadsJsonArray.length();
			//循环遍历，获得upload
			for(int i = 0 ; i<length; i++ ){
				//得到每一个upload
				JSONObject uploadJsonObject = uploadsJsonArray.getJSONObject(i);
				
		/**----------------------替换upload中的netResult----------------------**/
				
				//得到每一个upload中的netResult
				JSONArray netJsonArray = uploadJsonObject.getJSONArray("nr");
				//创建新的netResult，存放修改后的netResult数据
				JSONArray newNetjsonArray = new JSONArray();
				//先将netResult的key存放进去
				newNetjsonArray.put(netKeyArr);

				//循环遍历，获得netResult
				for(int j = 0;j<netJsonArray.length();j++){
					//得到具体的netResult JSONObject
					JSONObject netResultJsonObject = netJsonArray.getJSONObject(j);
					//以下为取得每一个value，放入到集合中,返回集合
					Object[] netResultValue = getNetResultValue(netResultJsonObject);
					newNetjsonArray.put(netResultValue);
				}
				//将upload中的netResult移除，换成新的netResult
				uploadJsonObject.remove("nr");
				uploadJsonObject.put("nr", newNetjsonArray);
				
				Log.d("==>", "" + newNetjsonArray);
				Log.d("==>", ""+uploadJsonObject);
	   /**-----------------------替换upload中的webviewResult----------------------**/
				
				//得到每一个upload中的webviewResult
				JSONArray webviewJsonArray = uploadJsonObject.getJSONArray("wr");
				//创建新的webviewResult，存放修改后的webviewResult数据
				JSONArray newwebviewjsonArray = new JSONArray();
				//先将webviewResult的key存放进去
				newwebviewjsonArray.put(webKeyArr);
				//循环遍历，获得webviewResult
				for(int m = 0;m<webviewJsonArray.length();m++){
					//得到具体的netResult JSONObject
					JSONObject webviewJsonObject = webviewJsonArray.getJSONObject(m);
					//以下为取得每一个value，放入到集合中,返回集合
					Object[] webviewResultValue = getWebViewResultValue(webviewJsonObject);
					newwebviewjsonArray.put(webviewResultValue);
				}
				//将upload中的webviewResult移除，换成新的webviewResult
				uploadJsonObject.remove("wr");
				uploadJsonObject.put("wr", newwebviewjsonArray);
				
				
	  /**----------------------替换upload中的InteractResult------------------------------**/
				//得到每一个upload中的InteractResult
				try{
					JSONObject interactJsonObject = uploadJsonObject.getJSONObject("i");
					/**------替换InteractResult中的ThreadInfo--------**/
					//获得InteractResult中的ThreadInfo
					JSONArray threadJsonArray = interactJsonObject.getJSONArray("ti");
					JSONArray newThreadjsonArray = new JSONArray();
					newThreadjsonArray.put(threadKeyArr);
					for(int n = 0;n<threadJsonArray.length();n++){
						JSONObject threadJsonobject = threadJsonArray.getJSONObject(n);
						Object[] threadInfoValue = getThreadInfoValue(threadJsonobject);
						newThreadjsonArray.put(threadInfoValue);
					}
					interactJsonObject.remove("ti");
					interactJsonObject.put("ti",newThreadjsonArray);
					
					/**------替换InteractResult中的MethodInfo--------**/
					JSONArray methodjsonArray = interactJsonObject.getJSONArray("mi");
					JSONArray newMethodjsonArray = new JSONArray();
					newMethodjsonArray.put(methodKeyArr);
					for(int n = 0;n<methodjsonArray.length();n++){
						JSONObject methodJsonObject = methodjsonArray.getJSONObject(n);
						Object[] methodInfoValue = getMethodInfoValue(methodJsonObject);
						newMethodjsonArray.put(methodInfoValue);
					}
					interactJsonObject.remove("mi");
					interactJsonObject.put("mi", newMethodjsonArray);
					
					/**------替换InteractResult中的MemoryCpuInfo--------**/
					JSONArray memoryCpuJsonArray = interactJsonObject.getJSONArray("mc");
					JSONArray newMemoryjsonArray = new JSONArray();
					newMemoryjsonArray.put(memoryKeyArr);
					for(int n = 0;n<memoryCpuJsonArray.length();n++){
						JSONObject memoryCpuJsonObject = memoryCpuJsonArray.getJSONObject(n);
						Object[] memoryCpuInfoValue = getMemoryCpuInfoValue(memoryCpuJsonObject);
						newMemoryjsonArray.put(memoryCpuInfoValue);
					}
					interactJsonObject.remove("mc");
					interactJsonObject.put("mc", newMemoryjsonArray);
					
					uploadJsonObject.remove("i"); //这里的i是字符串
					uploadJsonObject.put("i", interactJsonObject);
				}catch(Exception e){
					e.printStackTrace();
				}
				//替换upload
				uploadsJsonArray.remove(i);//这里的i是索引
				uploadsJsonArray.put(uploadJsonObject);
			}
			
			udrJsonObject.remove("d");
			udrJsonObject.put("d", uploadsJsonArray);
			
			SDKRequestJsonObject.remove("udr");
			SDKRequestJsonObject.put("udr", udrJsonObject);
			
			Log.d("<==", SDKRequestJsonObject.toString());
			return SDKRequestJsonObject.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object[] getThreadInfoValue(JSONObject jSONObject){
		try{
			Object[] newThreadvalueList = new Object[5];
			
			long et = getElementLong(jSONObject, "et");
			long st = getElementLong(jSONObject, "st");
			long ti = getElementLong(jSONObject, "ti");
			int t = getElementInt(jSONObject, "t");
			String n = getElementString(jSONObject, "n");
			
			newThreadvalueList[0]=et;
			newThreadvalueList[1]=st;
			newThreadvalueList[2]=ti;
			newThreadvalueList[3]=t;
			newThreadvalueList[4]=n;
			
			return newThreadvalueList;
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Object[] getMethodInfoValue(JSONObject jSONObject){
		try{
			Object[] newMethodvalueList = new Object[7];
			
			long st = getElementLong(jSONObject, "st");
			long et = getElementLong(jSONObject, "et");
			String n = getElementString(jSONObject, "n");
			long t = getElementLong(jSONObject, "t");
			String ru = getElementString(jSONObject, "ru");
			String rg = getElementString(jSONObject, "rg");
			String rgu = getElementString(jSONObject, "rgu");
			
			newMethodvalueList[0]=st;
			newMethodvalueList[1]=et;
			newMethodvalueList[2]=n;
			newMethodvalueList[3]=t;
			newMethodvalueList[4]=ru;
			newMethodvalueList[5]=rg;
			newMethodvalueList[6]=rgu;
			
			return newMethodvalueList;
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
		
	}
	
	public static Object[] getMemoryCpuInfoValue(JSONObject jSONObject){
		try{
			Object[] newMemoryCpuvalueList = new Object[3];
			
			long st = getElementLong(jSONObject, "st");
			float am = getElementFloat(jSONObject, "am");
			float ac = getElementFloat(jSONObject, "ac");
			newMemoryCpuvalueList[0]=st;
			newMemoryCpuvalueList[1]=am;
			newMemoryCpuvalueList[2]=ac;
			
			return newMemoryCpuvalueList;
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	
	public static Object[] getWebViewResultValue(JSONObject jSONObject){
		try{
			Object[] newWebvalueList = new Object[16];
			
			long st = getElementLong(jSONObject, "st");
			long et = getElementLong(jSONObject, "et");
			String ru = getElementString(jSONObject, "ru");
			String wn = getElementString(jSONObject, "wn");
			int fp = getElementInt(jSONObject, "fp");
			int ut = getElementInt(jSONObject, "ut");
			int rt = getElementInt(jSONObject, "rt");
			int ct = getElementInt(jSONObject, "ct");
			int dt = getElementInt(jSONObject, "dt");
			int cti =getElementInt(jSONObject, "cti");
			int rti = getElementInt(jSONObject, "rti");
			int rtim = getElementInt(jSONObject, "rtim");
			int dti = getElementInt(jSONObject, "dti");
			int brt = getElementInt(jSONObject, "brt");
			int rds = getElementInt(jSONObject, "rds");
			int cc =  getElementInt(jSONObject, "cc");
			
			newWebvalueList[0]=st;
			newWebvalueList[1]=et;
			newWebvalueList[2]=ru;
			newWebvalueList[3]=wn;
			newWebvalueList[4]=fp;
			newWebvalueList[5]=ut;
			newWebvalueList[6]=rt;
			newWebvalueList[7]=ct;
			newWebvalueList[8]=dt;
			newWebvalueList[9]=cti;
			newWebvalueList[10]=rti;
			newWebvalueList[11]=rtim;
			newWebvalueList[12]=dti;
			newWebvalueList[13]=brt;
			newWebvalueList[14]=rds;
			newWebvalueList[15]=cc;
			return newWebvalueList;
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Object[] getNetResultValue(JSONObject jSONObject){
		try {
			//集合存放每一个netResult中的value数据
			Object[] newNetvalueList = new Object[30];
			JSONObject nsiJSONObject = jSONObject.getJSONObject("nsi");
			String ru = getElementString(jSONObject, "ru");
			int si = getElementInt(jSONObject, "si");
			long li = getElementLong(jSONObject, "li");
			int lp = getElementInt(jSONObject, "lp");
			long ti = getElementLong(jSONObject, "ti");
			int tp = getElementInt(jSONObject, "tp");
			long st = getElementLong(jSONObject, "st");
			int dt = getElementInt(jSONObject, "dt");
			int ct = getElementInt(jSONObject, "ct");
			int sti = getElementInt(jSONObject, "sti");
			int rt = getElementInt(jSONObject, "rt");
			int rti = getElementInt(jSONObject, "rti");
			int dti = getElementInt(jSONObject, "dti");
			long et = getElementLong(jSONObject, "et");
			String rh = getElementString(jSONObject, "rh");
			int rd = getElementInt(jSONObject, "rd");
			String rhe = getElementString(jSONObject, "rhe");
			int rds = getElementInt(jSONObject, "rds");
			int ei = getElementInt(jSONObject, "ei");
			int se = getElementInt(jSONObject, "se");
			boolean ib = getElementBoolean(jSONObject, "ib");
			String mt = getElementString(jSONObject, "mt");
			long dsi = getElementLong(jSONObject, "dsi");
			String rg = getElementString(jSONObject, "rg");
			String rgu = getElementString(jSONObject, "rgu");
			boolean iw = getElementBoolean(jSONObject, "iw");
			String lc = getElementString(jSONObject, "lc");
			
			
			Object am = getElementObject(nsiJSONObject, "am");
			int s = getElementInt(nsiJSONObject, "s");
			int nt = getElementInt(nsiJSONObject, "nt");
			
			newNetvalueList[0]=ru;
			newNetvalueList[1]=si;
			newNetvalueList[2]=li;
			newNetvalueList[3]=lp;
			newNetvalueList[4]=ti;
			newNetvalueList[5]=tp;
			newNetvalueList[6]=st;
			newNetvalueList[7]=dt;
			newNetvalueList[8]=ct;
			newNetvalueList[9]=sti;
			newNetvalueList[10]=rt;
			newNetvalueList[11]=rti;
			newNetvalueList[12]=dti;
			newNetvalueList[13]=et;
			newNetvalueList[14]=rh;
			newNetvalueList[15]=rd;
			newNetvalueList[16]=rhe;
			newNetvalueList[17]=rds;
			newNetvalueList[18]=ei;
			newNetvalueList[19]=se;
			newNetvalueList[20]=ib;
			newNetvalueList[21]=mt;
			newNetvalueList[22]=dsi;
			newNetvalueList[23]=rg;
			newNetvalueList[24]=rgu;
			newNetvalueList[25]=iw;
			newNetvalueList[26]=lc;
			newNetvalueList[27]=am;
			newNetvalueList[28]=s;
			newNetvalueList[29]=nt;
			return newNetvalueList;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	public static String getElementString(JSONObject jSONObject,String key){
		try {
			String value = jSONObject.getString(key);
			return value;
		} catch (JSONException e) {
			return "";
		}
	}
	
	public static long getElementLong(JSONObject jSONObject,String key){
		try {
			long value = jSONObject.getLong(key);
			return value;
		} catch (JSONException e) {
			return 0;
		}
	}
	
	public static int getElementInt(JSONObject jSONObject,String key){
		try {
			int value = jSONObject.getInt(key);
			return value;
		} catch (JSONException e) {
			return 0;
		}
	}
	
	public static boolean getElementBoolean(JSONObject jSONObject,String key){
		try {
			boolean value = jSONObject.getBoolean(key);
			return value;
		} catch (JSONException e) {
			return false;
		}
	}
	
	public static float getElementFloat(JSONObject jSONObject,String key){
		try {
			float value = (Float) jSONObject.get(key);
			return value;
		} catch (JSONException e) {
			return 0;
		}
	}
	
	public static Object getElementObject(JSONObject jSONObject,String key){
		try {
			Object value = jSONObject.get(key);
			return value;
		} catch (JSONException e) {
			return "";
		}
	}
}
