package com.tencent.tbs.sdk.extension.partner.precheck;

/**
 * Created by grasshuang on 2018/6/27.
 */

import android.content.Context;

import java.io.File;

public class TbsCrashHandler {

    private static final String LOGTAG = "CrashHandler";

    private static final String LIBRARY_NAME = "libtbs_main.so";


    public TbsCrashHandler( String libraryPath, Context ctx) {
//        System.load(libraryPath + File.separator + LIBRARY_NAME);

        System.loadLibrary("tbs_main");
    }

    /**
     * @attention
     * 		  Remember to "#define CRASH_TEST" in tbs_crash_hander.cc before use this API
     * @param index
     */
    public native static void nativeStartCrashTest(int index);


}
