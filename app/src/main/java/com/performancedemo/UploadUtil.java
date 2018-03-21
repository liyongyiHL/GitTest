package com.performancedemo;

import android.util.Log;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by bonree-lidong on 2017/10/20.
 */
public class UploadUtil {

    public static void trustAllHosts() {
        final String TAG = "trustAllHosts";
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            String peerHost = session.getPeerHost();
            if (!peerHost.contains("317844B0CDB0A832"))
                return true;
            return false;
        }
    };


    public static byte[] sendDataFromHTTP(byte[] stream ,String serversUrl) throws IOException{
        URLConnection urlConnection = null;
        URL url = null;
        OutputStream os = null;
        InputStream is = null;
        byte resultArray[] = null;
        url = new URL(serversUrl);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setConnectTimeout(10 * 1000);
        urlConnection.setReadTimeout(30 * 1000);
        urlConnection.connect();
        is = urlConnection.getInputStream();
        resultArray = InputStreamToByte(is);
        if (os != null)
            os.close();
        if (is != null)
            is.close();
        if (urlConnection != null) {
            ((HttpURLConnection) urlConnection).disconnect();
        }
        return resultArray;
    }



    public static byte[] sendDataFromQueue(byte[] stream, String serversUrl) throws IOException {
        URLConnection urlConnection = null;
        URL url = null;
        OutputStream os = null;
        InputStream is = null;
        byte resultArray[] = null;
        trustAllHosts();
        url = new URL(serversUrl);
        String host = "";
        host = url.getHost();
        urlConnection = (HttpsURLConnection) url.openConnection();
        ((HttpsURLConnection) urlConnection).setHostnameVerifier(DO_NOT_VERIFY);

        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        ((HttpsURLConnection) urlConnection).setRequestMethod("POST");
        urlConnection.setRequestProperty("ProtoType", "json");
        urlConnection.setRequestProperty("Content-Type",
                                         "application/x-www-form-urlencoded;charset=UTF-8");
        urlConnection.setRequestProperty("Host", host);
        urlConnection.setRequestProperty("brkey", "4:2:78e06299-4c74-418e-a9a0-b10c21ab5e5d_1508477992188");
        urlConnection.setConnectTimeout(10 * 1000);
        urlConnection.setReadTimeout(30 * 1000);

        urlConnection.connect();
        os = urlConnection.getOutputStream();
        os.write(stream);
        os.flush();
        is = urlConnection.getInputStream();
        long sendBeginTime = System.currentTimeMillis();
        resultArray = InputStreamToByte(is);
        long getByteArrayTime = System.currentTimeMillis();
        if (os != null)
            os.close();
        if (is != null)
            is.close();
        if (urlConnection != null) {
            ((HttpsURLConnection) urlConnection).disconnect();
        }
        return resultArray;
    }

    /* Convert hex string to byte[]
	 * @param hexString the hex string
	 * @return byte[]
	 */
    public  byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }

    public String bytetoHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder();
        if(src == null || src.length<=0){
            return null;
        }
        for(int i=0;i<src.length;i++){
            int iv = src[i]&0xFf;
            String hv = Integer.toHexString(iv);
            if(hv.length()<2){
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static byte[] intToByteArray(int byteLength, int intValue) {
        return ByteBuffer.allocate(byteLength).putInt(intValue).array();
    }

    public static byte[] InputStreamToByte(InputStream iStrm) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        int ch;
        while ((ch = iStrm.read()) != -1)
        {
            bytestream.write(ch);
        }
        byte imgdata[]=bytestream.toByteArray();
        bytestream.close();
        if(iStrm!=null){
            iStrm.close();
        }
        return imgdata;
    }

    public static byte[] copyOfRange(byte[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException();
        byte[] copy = new byte[newLength];
        System.arraycopy(original, from, copy, 0, Math.min(original.length - from, newLength));
        return copy;
    }



    public static void httpUrlConnetionGet(String serversUrl , byte[] data) {
        try {
            //创建URL对象
            URL url = new URL(serversUrl);//Get请求可以在Url中带参数： ①url + "?bookname=" + name;②url="http://www.baidu.com?name=zhang&pwd=123";
            //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //在这里设置一些属性，详细见UrlConnection文档，HttpURLConnection是UrlConnection的子类
            //设置连接超时为5秒
            httpURLConnection.setConnectTimeout(5000);
            //设定请求方式(默认为get)
            httpURLConnection.setRequestMethod("POST");
            //建立到远程对象的实际连接
            // http正文内，因此需要设为true, 默认情况下是false;
            httpURLConnection.setDoOutput(true);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpURLConnection.setDoInput(true);
            // Post 请求不能使用缓存
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Content-Type",
                                                 "application/x-www-form-urlencoded;charset=UTF-8");
            httpURLConnection.setRequestProperty("ProtoType", "json");
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
            httpURLConnection.setRequestProperty("Host", url.getHost());
            httpURLConnection.setRequestProperty("brkey", "4:2:a95529ce-86e3-4956-ad66-ce20791d81ba_1508494710564");
            httpURLConnection.connect();

            OutputStream outputStream = httpURLConnection.getOutputStream();

            outputStream.write(data, 0, data.length);
            outputStream.close();

            //读取返回的数据
            //返回打开连接读取的输入流，输入流转化为StringBuffer类型，这一套流程要记住，常用
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//            String line = null;
//            StringBuffer stringBuffer = new StringBuffer();
//            while ((line = bufferedReader.readLine()) != null) {
//                line = new String(line.getBytes("UTF-8"));
//                stringBuffer.append(line);
//            }
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bytes = InputStreamToByte(inputStream);
            byte[] decompress = MessageGZip.decompress(bytes);
            Log.d("BRSDK-Agent", new String(decompress));
//            bufferedReader.close();
            httpURLConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void httpUrlConnetionGetPB(String serversUrl , byte[] data) {
        try {
            //创建URL对象
            URL url = new URL(serversUrl);//Get请求可以在Url中带参数： ①url + "?bookname=" + name;②url="http://www.baidu.com?name=zhang&pwd=123";
            //返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
            HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
            httpURLConnection.setHostnameVerifier(DO_NOT_VERIFY);
            //在这里设置一些属性，详细见UrlConnection文档，HttpURLConnection是UrlConnection的子类
            //设置连接超时为5秒
            httpURLConnection.setConnectTimeout(5000);
            //设定请求方式(默认为get)
            httpURLConnection.setRequestMethod("POST");
            //建立到远程对象的实际连接
            // http正文内，因此需要设为true, 默认情况下是false;
            httpURLConnection.setDoOutput(true);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpURLConnection.setDoInput(true);
            // Post 请求不能使用缓存
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Content-Type",
                                                 "application/x-www-form-urlencoded;charset=UTF-8");
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
            httpURLConnection.setRequestProperty("Host", url.getHost());
            httpURLConnection.setRequestProperty("brkey",
                                                 "4:2:a95529ce-86e3-4956-ad66-ce20791d81ba_1508494710564");
            httpURLConnection.connect();
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data, 0, data.length);
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            Log.d("BRSDK-Agent", "length:"+InputStreamToByte(inputStream).length);
            httpURLConnection.disconnect();
            Log.d("BRSDK-Agent", "disconnect");
        } catch (Exception e) {
            Log.d("BRSDK-Agent", e.toString());
            e.printStackTrace();
        }

    }
}
