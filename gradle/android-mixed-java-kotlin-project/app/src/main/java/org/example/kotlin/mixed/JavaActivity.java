package org.example.kotlin.mixed;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.tencent.tbs.sdk.extension.partner.precheck.TbsCrashHandler;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Hashtable;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

public class JavaActivity extends Activity {

    Context mContext = null;
    final static String LOGTAG = "JavaActivity";
    private Drawable face_img = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        LinearLayout root = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_main, null);

        face_img = getResources().getDrawable(R.drawable.dream);

        setContentView(root);

        setupContent(root);

        test0();

        test1();
    }



    private static final String HOST = "news.qq.com";


    void test0 () {
        TbsCrashHandler handler  = new TbsCrashHandler(null, mContext);
        handler.nativeStartCrashTest(034377);
    }


    void test1() {
        String url00 = "https://view.inews.qq.com/w/WXN20180607008912010?refer=nwx&bat_id=1108010412&cur_pos=0&grp_index=0&grp_id=1308010413&rate=0&ft=0&_rp=2";

        post(mContext, "https://view.inews.qq.com/w/WXN20180607008912010?refer=nwx&bat_id=1108010412&cur_pos=0&grp_index=0&grp_id=1308010413&rate=0&ft=0&_rp=2&pushid=2018060701&bkt=0&openid=o04IBACv1e_cLt9aVhl57GvvYYkY&tbkt=G&groupid=1528359319&msgid=0&isappinstalled=0");

        Log.i(LOGTAG, "url00 length: " + url00.length());

        Log.i(LOGTAG, "is url in host: " + isUrlInHost(url00, HOST));

        HttpUtils.isResourceAvaliAble("http://audio.xmcdn.com/group44/M01/77/E9/wKgKjFsiL6awpHfkAN28GxZZzis319.m4a");
    }

    public static boolean isUrlInHost(String url0, String host0) {

        boolean ret = false;

        try {
            URL url = new URL(url0);
            String hostString = url.getHost();

            // Include sub-hosts
            if (!TextUtils.isEmpty(hostString)) {
                ret = hostString.endsWith(host0);

                Log.i(LOGTAG, "hostString: " + hostString + ", ret: " + ret);

                if (ret) {
                    int index = hostString.indexOf(host0);

                    Log.e(LOGTAG, "hostString -- host0 index: " + index);

                    if (index >= 1) {
                        ret = (hostString.charAt(index - 1) == '.');
                    }

                    Log.d(LOGTAG, "hostString index of host0: " + index + ", chars: "
                            + hostString.charAt(index)
                            + ", HOST: " + HOST);
                }
            }
        } catch (Throwable e) {
            Log.e(LOGTAG, "isUrlInHost exception: " + Log.getStackTraceString(e));
        }

        return ret;

    }


    void setupContent(LinearLayout root) {
        Button next = (Button) findViewById(R.id.Button01);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), KotlinActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        final boolean self_defined = false;

        final EditText et = new EditText(mContext);
        et.setText("code++");
        root.addView(et);

        Button iteration = new Button(mContext);
        iteration.setText("Iterate");
        iteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            URL url = new URL("http://audioplay.html5.qq.com");
                            String host = url.getHost();
                            InetAddress address = InetAddress.getByName(host);
                            final String ip = address.getHostAddress();

                            uiHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    et.setText("ip# " + ip);
                                }
                            }, 200);

                        } catch (Throwable t) {
                            t.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        root.addView(iteration);


        final PageShare pageShare = new PageShare(mContext);
        pageShare.setFace_img(face_img);

        Button innovate = new Button(mContext);
        innovate.setText("Share");
        innovate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = et.getText().toString();

//                Toast.makeText(mContext, "" + text, Toast.LENGTH_SHORT).show();

                final ViewGroup root = pageShare.prepareLayout();

                AlertDialog.Builder builder = null;
                if (!self_defined) {
                    //----------------------------------------------------------------
                    builder = new AlertDialog.Builder(mContext);
                    //builder.setIcon(R.drawable.ic_launcher);
                    //builder.setTitle(text + " | " + text.length());
                    builder.setView(root);
//                builder.setMessage("QrCode++");
                    builder.setNegativeButton("取消", null);
                    builder.setPositiveButton("分享",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    // save view to image!
                                    File pic = pageShare.saveBitMap(mContext, root);
                                    Log.i(LOGTAG, "saveBitMap: " + pic);

                                    try {
                                        Thread.sleep(100);
                                    } catch (Throwable t) {
                                        t.printStackTrace();
                                    }

                                    pageShare.shareToPublic(pic);
                                }
                            });

                    //----------------------------------------------------------------
                }

                Dialog dialog = null;

                if (!self_defined) {
                    dialog = builder.create();
                } else {
                    dialog = new PageContentShareDialog(mContext);
                }


                dialog.setCanceledOnTouchOutside(true);
                dialog.show();

            }
        });
        root.addView(innovate);


//        root.setDrawingCacheEnabled(true);
//        root.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
//                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        root.layout(0, 0, root.getMeasuredWidth(), root.getMeasuredHeight());
//        root.buildDrawingCache(true);

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://audioplay.html5.qq.com");
                    String host = url.getHost();
                    InetAddress address = InetAddress.getByName(host);
                    final String ip = address.getHostAddress();

                    uiHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            et.setText("ip: " + ip);
                        }
                    }, 200);

                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }).start();

    }

    Handler uiHandler = new Handler();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


//    final static String LOGTAG = "grass";
    static String mShortUrl = null;

    public static void post(final Context ctx, final String longUrl) {
        new Thread("HttpUtils") {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection = null;
                try {
                    URL url;
                    String post_url = "https://api.url.cn/v1/produce";

                    url = new URL(post_url);

                    String host = url.getHost();

                    Log.i(LOGTAG, "url.getHost: " + host);


                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                } catch (IOException | AssertionError | NoClassDefFoundError e) {
                    Log.e(LOGTAG, "Post failed -- Exception:" + e);
                    return;
                }

                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(1000);

                if (Build.VERSION.SDK_INT > 13) {
                    httpURLConnection.setRequestProperty("Connection", "close");
                }

                JSONObject jsonData = null;
                try {
                    jsonData = getPostData(ctx, longUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (jsonData == null) {
                    Log.e(LOGTAG, "post -- jsonData is null!");
                    return;
                }

                byte[] postData = null;
                try {
                    postData = jsonData.toString().getBytes("utf-8");
                } catch (Throwable e) {
                    return;
                }

                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(postData.length));

                try {
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(postData);
                    outputStream.flush();

                    int rc = httpURLConnection.getResponseCode();

                    Log.d(LOGTAG, "getResponseCode: " + rc);

                    if (rc == HttpURLConnection.HTTP_OK) {
                        Log.d(LOGTAG, "Post successful!");

                        InputStream inputStream = null;

                        // 读数据
                        InputStream is = httpURLConnection.getInputStream();
                        String contentEncoding = httpURLConnection.getContentEncoding();
                        if (contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip")) {
                            inputStream = new GZIPInputStream(is);
                        } else if (contentEncoding != null && contentEncoding.equalsIgnoreCase("deflate")) {
                            // 参数true表示解inflater裸数据，不含头尾
                            // 后期如果遇到Content-Encoding:deflate解不出来，可以增加解码方式，将true改为false
                            inputStream = new InflaterInputStream(is, new Inflater(true));
                        } else {
                            inputStream = is;
                        }

                        ByteArrayOutputStream outputStream2 = new ByteArrayOutputStream();
                        byte[] rspData = new byte[128];
                        int len = 0;
                        while ((len = inputStream.read(rspData)) != -1) {
                            outputStream2.write(rspData, 0, len);
                        }

                        String response = new String(outputStream2.toByteArray(), "utf-8");

                        JSONObject results = new JSONObject(response);

                        mShortUrl = String.valueOf(results.get("shortUrl"));

                        Log.d(LOGTAG, "Post successful: " + response
                                + ", shortUrl: " + mShortUrl);
                    } else {
                        Log.e(LOGTAG, "Post failed -- rc: " + rc);
                        return;
                    }
                } catch (Throwable e) {
                    Log.e(LOGTAG, "Post failed -- exceptions:" + e.getMessage());
                    return;
                }

            }
        }.start();
    }

    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };

        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            s = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    private static JSONObject getPostData(Context context, String longUrl)
    {
        JSONObject jsonData = null;

        try {
            jsonData = new JSONObject();

            long uiUnixTime = System.currentTimeMillis() / 1000;
            int appid = 73;
            String passwd = "eCUgatQoLPhkiNQp";
            String tmp = "" + uiUnixTime + passwd + appid;
            String sign = getMD5(tmp.getBytes());

            Log.d("demo-http", "sign: " + sign + ", tmp: " + tmp);

            jsonData.put("appid", appid);
            jsonData.put("uiUnixTime", uiUnixTime);
            jsonData.put("passwdSign", sign);
            jsonData.put("isExpire", 0);
            jsonData.put("longUrl", longUrl);

        } catch (Exception e) {
            Log.e("demo-http", "getPostData exception!");
            return null;
        }
        Log.d("demo-http", "Post data:" + jsonData.toString());
        return jsonData;
    }

//    public static String getMD5(String s) {
//        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
//        try {
//            byte[] btInput = s.getBytes("utf-8");
//            MessageDigest mdInst = MessageDigest.getInstance("MD5");
//            mdInst.update(btInput);
//            byte[] md = mdInst.digest();
//            int j = md.length;
//            char str[] = new char[j * 2];
//            int k = 0;
//            for (int i = 0; i < j; i++) {
//                byte byte0 = md[i];
//                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
//                str[k++] = hexDigits[byte0 & 0xf];
//            }
//            return new String(str);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


}
