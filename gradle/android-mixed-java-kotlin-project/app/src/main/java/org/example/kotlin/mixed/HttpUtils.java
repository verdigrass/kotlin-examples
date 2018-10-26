package org.example.kotlin.mixed;

/**
 * Created by grasshuang on 2018/6/22.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by bradywwang on 17-8-20.
 */

public class HttpUtils {

    static final String LOGTAG = "HttpUtils";

    public static boolean isResourceAvaliAble(String url){
        try {
            int responseCode = HttpUtils.getHttpResponseCode(new URL(url));
            Log.i(LOGTAG, "isAudioAvaliAble,ur="+url+", responseCode="+responseCode);
            return responseCode < 400; //认为400以上的响应码为正常
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public static int getHttpResponseCode(URL url) {
        HttpURLConnection httpurlconnection = null;
        int responsecode = -1;
        try {
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.connect();
//            if (!(urlconnection instanceof HttpURLConnection)) {
//                return responsecode;
//            }
//
//            httpurlconnection = (HttpURLConnection) urlconnection;

            // 获取返回码,通过responsecode 就可以知道网页的状态,我们也是通过此字段用于判断请求的资源是否存在
            responsecode= urlconnection.getResponseCode();
            switch (responsecode) {
                // here valid codes!
                case HttpURLConnection.HTTP_OK:
                case HttpURLConnection.HTTP_MOVED_PERM:
                case HttpURLConnection.HTTP_MOVED_TEMP:
                    break;
                default:
                    httpurlconnection.disconnect();
            }
        } catch (Exception ioexception) {
            if (httpurlconnection != null) {
                httpurlconnection.disconnect();
            }
            return responsecode;
        }
        return responsecode;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}