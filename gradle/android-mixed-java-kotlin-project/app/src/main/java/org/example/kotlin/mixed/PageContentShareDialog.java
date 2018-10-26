package org.example.kotlin.mixed;

import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

/**
 * Created by grasshuang on 2018/5/24.
 */

public class PageContentShareDialog extends Dialog {

    Context				mContext			= null;

    boolean 			mAttached			= false;

    private static final int		FLAG_TRANSLUCENT_STATUS		= 0x04000000;

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mAttached = true ;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAttached = false ;
    }

    public boolean isAttached() {
        return mAttached ;
    }

    public PageContentShareDialog(Context context)
    {
        super(context);
        mContext = context;
    }

    public PageContentShareDialog(Context context, int theme)
    {
        super(context, theme);
        mContext = context;
        if (Build.VERSION.SDK_INT >= 19)
            getWindow().clearFlags(FLAG_TRANSLUCENT_STATUS);

    }

    // @Override
    // public void onWindowFocusChanged(boolean hasFocus)
    // {
    // if (!hasFocus)
    // {
    // if (!isActivityForeground(mContext))
    // {
    // // 已压后台
    // close();
    // }
    // return;
    // }
    // super.onWindowFocusChanged(hasFocus);
    // }

    private boolean isActivityForeground(Context context)
    {
        String pkgName = context.getPackageName();
        if (TextUtils.isEmpty(pkgName))
        {
            return false;
        }

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null)
        {
            List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
            if (appProcesses != null)
            {
                for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses)
                {
                    if (appProcess == null)
                    {
                        continue;
                    }

                    if (pkgName.equals(appProcess.processName))
                    {
                        if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND)
                        {
                            // 前台
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void onBackPressed()
    {
        Log.d("destoryMini", "onBackPressed");

    }

    @Override
    public void show()
    {
        super.show();
//			Log.e("startminiqb" , "midpageview show ");
        // init package receiver
//			if (mContext != null)
//			{
//				QBDownloadManager.getInstance().registerQBDownloadListener(mQbDownloadListener);
//			}
    }

    @Override
    public void dismiss()
    {
        super.dismiss();
    }


//    @Override
//    public String getCurrentUrl()
//    {
//        String url = webview == null ? null : webview.getUrl();
//        if (TextUtils.isEmpty(url) || (url != null && url.equalsIgnoreCase(MidPageController.QB_HOME_PAGE)))
//            url = "qb://home";
//        return url;
//    }

//		@Override
//		public boolean dispatchTouchEvent(MotionEvent ev)
//		{

//			boolean ret = super.dispatchTouchEvent(ev);
//			Log.d("MFLOG-WINDOW", "dialog dispatchTouchEvent: " + ret + ", " + System.currentTimeMillis());
//			return ret;
//		}

}