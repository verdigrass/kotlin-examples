package org.example.kotlin.mixed;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by grasshuang on 2018/6/4.
 */

public class PageShare {

    Context mContext = null;


    //    private static final int image_halfwidth = 0;// 18;//宽度值，影响中间图片大小

    // 512
    // 128
    private static final int QR_CODE_SIZE = 128;//宽度值，影响中间图片大小
    final int MAX_TEXT = 1000;

    final String URL_002 = "https://mp.weixin.qq.com/s/99dE6AfaekTG67Kp46laSw";
    final String URL_001 = "https://view.inews.qq.com/w/WXN20180607008912010?refer=nwx&bat_id=1108010412&cur_pos=0&grp_index=0&grp_id=1308010413&rate=0&ft=0&_rp=2";
    final String SUB_TITLE = "可长按识别二维码阅读原文";
    final String SELECTED_TEXT =
            "习近平表示，见到大家感到十分亲切，因为我也是一名军转干部。习近平指出，" +
            "军转干部是党和国家的宝贵财富，我们要倍加关心、倍加爱护。\n" +
            "\n" +
            "军转干部是党和国家的宝贵财富，我们要倍加关心、倍加爱护。军转安置工作十分重要，关系改革发展稳定全局和国防军队建设。" +
            "各级党委和政府、军队各级组织要高度重视并满腔热情做好军转安置工作。我们要大力宣传这些先进事迹，使广大军转干部都能从中吸收力量。";
//
//            + "5月31日，习近平总书记主持召开中央政治局会议，审议《关于打赢脱贫攻坚战三年行动的指导意见》，" +
//            "对未来三年打赢脱贫攻坚战作出全面部署，明确要求确保坚决打赢脱贫这场对如期全面建成小康社会、实现第一个百年奋斗目标具有决定性意义的攻坚战。" +
//            "离2020年还有不到三年时间，时不我待，必须凝聚共识，进而凝心聚力，坚决打赢脱贫攻坚战。\n" +
//            "\n" +
//            "强化政治责任是基础。打赢脱贫攻坚战是我们党的庄严承诺，是对中华民族和整个人类都有重大意义的伟业。" +
//            "脱贫攻坚是习近平总书记亲自带领省市县乡村五级书记一起抓的一把手工程，" +
//            "是一项极其重大、极为严肃的政治任务，更是各级党政干部不可推卸的重大政治责任。\n" +
//            "\n" +
//            "这就需要各级党委和政府切实把打赢脱贫攻坚战作为重大政治任务，始终加强党对脱贫攻坚的领导，把全面从严治党要求贯彻脱贫攻坚全过程，" +
//            "进一步落实脱贫攻坚责任制，强化使命担当。只有这样，才能不断夯实打赢脱贫攻坚战的政治基础。";

    final String TITLE = "有的人竟然感到涉军安置是额外负担，军报刊文批评";
    final String AUTHOR = "人民日报";

    private Drawable face_img = null;

    final String LOGTAG = "PageShare";

    int TITLE_TEXT_SIZE = 13;
    int TITLE_COLOR = Color.parseColor("#b4b4b4");

    int AUTHOR_TEXT_SIZE = 12;
    int AUTHOR_COLOR = Color.parseColor("#666666");

    int SELECTED_TEXT_SIZE = 16;
    int SELECTED_TEXT_COLOR = Color.parseColor("#333333");

    int SUBTITLE_TEXT_SIZE = 12;
    int SUBTITLE_TEXT_COLOR = Color.parseColor("#3e3e3e");


    PageShare(Context ctx) {
        mContext = ctx;
    }

    public Drawable getFace_img() {
        return face_img;
    }

    public void setFace_img(Drawable fi) {
        this.face_img = fi;
    }


    public void shareToPublic(File pic) {

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        // intent.setType("text/plain"); //纯文本
            /*
             * 图片分享 it.setType("image/png"); 　//添加图片 File f = new
             * File(Environment.getExternalStorageDirectory()+"/name.png");
             *
             * Uri uri = Uri.fromFile(f); intent.putExtra(Intent.EXTRA_STREAM, uri); 　
             */

        Uri uri = Uri.fromFile(pic);
        intent.putExtra(Intent.EXTRA_STREAM, uri);

        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
//        intent.putExtra(Intent.EXTRA_TEXT, "I have successfully share my message through my app");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(Intent.createChooser(intent, "Share"));
    }



    ViewGroup getBottomViewLayout2(Context context) {

        RelativeLayout relativeLayout_509 = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams layout_37 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout_509.setLayoutParams(layout_37);

        // index-0
        ImageView qr_code = new ImageView(mContext);
        qr_code.setId(R.id.qr_code);
//        qr_code.setImageResource(R.drawable.ic_launcher);
        RelativeLayout.LayoutParams layout_341 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layout_341.rightMargin  = 100;
        layout_341.bottomMargin = 20;
        layout_341.topMargin    = 100;
        layout_341.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        qr_code.setLayoutParams(layout_341);
        relativeLayout_509.addView(qr_code);

        TextView bottom_qr_title = new TextView(mContext);
//        bottom_qr_title.setId(R.id.bottom_qr_title);
//        bottom_qr_title.setText("test");
        bottom_qr_title.setTextSize((16 / mContext.getApplicationContext().getResources().getDisplayMetrics().scaledDensity));
        RelativeLayout.LayoutParams layout_93 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layout_93.addRule(RelativeLayout.ALIGN_RIGHT, qr_code.getId());
        layout_93.addRule(RelativeLayout.BELOW, qr_code.getId());
//        layout_93.right = 13;
        layout_93.bottomMargin = 20;


        bottom_qr_title.setLayoutParams(layout_93);

        relativeLayout_509.addView(bottom_qr_title);

        return relativeLayout_509;
    }


    ViewGroup prepareLayout() {

//      Drawable logo_dr = getResources().getDrawable(R.drawable.ic_launcher);
//      Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        Bitmap code_plus = null;
        try {
            code_plus = createCode(URL_001, null, 0, BarcodeFormat.QR_CODE);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        // @row-1
        ImageView face_view = new ImageView(mContext);
        face_view.setImageDrawable(face_img);
        face_view.setAdjustViewBounds(true);

        int fh = face_img.getIntrinsicHeight();
        int fw = face_img.getIntrinsicWidth();
        Log.i(LOGTAG, "face_img size(" + fw + ", " + fh + ")");

        LinearLayout.LayoutParams face_view_lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        face_view_lp.gravity = Gravity.CENTER_HORIZONTAL;
        face_view_lp.setMargins(0, 0, 0, 0);


//        // row-3
//        TextView author = new TextView(mContext);
//        author.setText(AUTHOR);
//        author.setTextSize(AUTHOR_TEXT_SIZE);
//        author.setTextColor(AUTHOR_COLOR);
//
//        LinearLayout.LayoutParams author_lp = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//        author_lp.gravity = Gravity.CENTER_HORIZONTAL;


        // @row-2 ~ upper
        LinearLayout upper = new LinearLayout(mContext);


        String text0 = SELECTED_TEXT;
        if (SELECTED_TEXT.length() > MAX_TEXT) {
            text0 = SELECTED_TEXT.substring(0, MAX_TEXT - 1);
            text0 += "...";
        }

        StringBuilder main_text = new StringBuilder();
        main_text.append(" “ ");
        main_text.append(text0);
        main_text.append(" ” ");


        TextView selected_text = new TextView(mContext);
        selected_text.setText(text0);
        selected_text.setTextSize(SELECTED_TEXT_SIZE);
        selected_text.setTextColor(SELECTED_TEXT_COLOR);

        LinearLayout.LayoutParams lp02 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp02.leftMargin   = 100;
        lp02.rightMargin  = 100;
        lp02.topMargin    = 100;
        lp02.bottomMargin = 25;

        upper.addView(selected_text);
        upper.setLayoutParams(lp02);


        // @row-3
        TextView main_title = new TextView(mContext);
        main_title.setText(AUTHOR + " • 《" + TITLE + "》");
        main_title.setTextSize(TITLE_TEXT_SIZE);
        main_title.setTextColor(TITLE_COLOR);

        LinearLayout.LayoutParams main_title_lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        main_title_lp.gravity = Gravity.CENTER_HORIZONTAL;
        main_title_lp.setMargins(100, 25, 100, 25);


        // @row-4 ~ bottom
        ViewGroup bottom = getBottomViewLayout2(mContext);
        // (RelativeLayout) getLayoutInflater().inflate(R.layout.bottom_view, null);
        // bottom.setOrientation(LinearLayout.HORIZONTAL);

        // @@row-5 ~ qr-code
        ImageView im = (ImageView) bottom.getChildAt(0);//bottom.findViewById(R.id.qr_code);
        im.setImageBitmap(code_plus);
        im.setMinimumWidth(QR_CODE_SIZE);
        im.setMinimumHeight(QR_CODE_SIZE);

        // @@row-5 ~title2
        TextView title2 = (TextView) bottom.getChildAt(1);//bottom.findViewById(R.id.bottom_qr_title);
        title2.setText(SUB_TITLE);
        title2.setTextSize(SUBTITLE_TEXT_SIZE);
        title2.setTextColor(SUBTITLE_TEXT_COLOR);
        //title2.setLayoutDirection(1);

//        LinearLayout.LayoutParams bottom_lp = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        bottom_lp.rightMargin = 100;
//        bottom_lp.topMargin   = 100;
//        bottom_lp.gravity     = Gravity.RIGHT;

        // whole
        ScrollView sv_root = new ScrollView(mContext);

        LinearLayout root = new LinearLayout(mContext);
        root.setOrientation(LinearLayout.VERTICAL);

        // merge all
        root.addView(face_view, face_view_lp);
//        root.addView(author, author_lp);
        root.addView(upper);

        root.addView(main_title, main_title_lp);

        root.addView(bottom);

        sv_root.addView(root);
//        root.setBackgroundColor(0xC7EDCC);

        return sv_root;
    }


    public File saveBitMap(Context context, ViewGroup drawView){
        File pictureFileDir = new File(Environment.getExternalStorageDirectory(),"tbs");

//        String pictureFileDir = context.getExternalFilesDir("tbs").getAbsolutePath();

//        if (!pictureFileDir.exists()) {
//            boolean isDirectoryCreated = pictureFileDir.mkdirs();
//            if(!isDirectoryCreated)
//                Log.i("TAG", "Can't create directory to save the image");
//            return null;
//        }

        String filename = pictureFileDir +File.separator+ System.currentTimeMillis()+".jpg";
        File pictureFile = new File(filename);
        Bitmap bitmap = getBitmapFromView(drawView);//getBitmapFromView(drawView);

        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
//        scanGallery( context,pictureFile.getAbsolutePath());
        return pictureFile;
    }


//    Bitmap getBitmapFromLayout(View view) {
//        Bitmap b = Bitmap.createBitmap(view.getDrawingCache());
//        view.setDrawingCacheEnabled(false);
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//
//        return b;
//    }

    //create bitmap from view and returns it
    private Bitmap getBitmapFromView(ViewGroup view) {

        int real_full_height = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < view.getChildCount(); i++) {
            real_full_height += view.getChildAt(i).getHeight();
        }
//        bitmap = Bitmap.createBitmap(view.getWidth(), h, Bitmap.Config.RGB_565);


        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(),
                real_full_height,//view.getHeight(),
                Bitmap.Config.ARGB_8888);

        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
            Log.i("TAG", "bgDrawable.draw: " + bgDrawable);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
            Log.i("TAG", "canvas.drawColor white");
        }
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }



    /**
     * 生成二维码
     *
     * @param url     二维码中包含的文本信息
     * @param mBitmap logo图片
     * @param format  编码格式
     * @return Bitmap 位图
     * @throws WriterException
     */
    public Bitmap createCode(String url, Bitmap mBitmap, int image_halfwidth, BarcodeFormat format)
            throws WriterException {

        if (image_halfwidth > 0) {
            Matrix m = new Matrix();
            float sx = (float) 2 * image_halfwidth / mBitmap.getWidth();
            float sy = (float) 2 * image_halfwidth / mBitmap.getHeight();
            m.setScale(sx, sy);//设置缩放信息

            //将logo图片按martix设置的信息缩放
            mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
                    mBitmap.getWidth(), mBitmap.getHeight(), m, false);
        }

        MultiFormatWriter writer = new MultiFormatWriter();

        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);

        //生成二维码矩阵信息
        BitMatrix matrix = writer.encode(url, format, QR_CODE_SIZE, QR_CODE_SIZE, hints);

        int width = matrix.getWidth();//矩阵高度
        int height = matrix.getHeight();//矩阵宽度
        int halfW = width / 2;
        int halfH = height / 2;
        int[] pixels = new int[width * height];//定义数组长度为矩阵高度*矩阵宽度，用于记录矩阵中像素信息

        for (int y = 0; y < height; y++) {//从行开始迭代矩阵
            for (int x = 0; x < width; x++) {//迭代列
                if (x > halfW - image_halfwidth && x < halfW + image_halfwidth
                        && y > halfH - image_halfwidth
                        && y < halfH + image_halfwidth) {//该位置用于存放图片信息

                    //记录图片每个像素信息
                    pixels[y * width + x] = mBitmap.getPixel(x - halfW
                            + image_halfwidth, y - halfH + image_halfwidth);
                } else {
                    if (matrix.get(x, y)) {//如果有黑块点，记录信息
                        pixels[y * width + x] = 0xff000000;//记录黑块信息
                    }
                }

            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);

        // 通过像素数组生成bitmap
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);

        return bitmap;
    }



    RelativeLayout getBottomViewLayout1(Context context) {

        RelativeLayout relativeLayout_509 = new RelativeLayout(mContext);
        RelativeLayout.LayoutParams layout_37 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout_509.setLayoutParams(layout_37);

        // index-0
        ImageView qr_code = new ImageView(mContext);
        qr_code.setId(R.id.qr_code);
        qr_code.setImageResource(R.drawable.ic_launcher);
        RelativeLayout.LayoutParams layout_341 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layout_341.leftMargin = 20;
        layout_341.bottomMargin = 20;

        qr_code.setLayoutParams(layout_341);
        relativeLayout_509.addView(qr_code);

        TextView bottom_qr_title = new TextView(mContext);
//        bottom_qr_title.setId(R.id.bottom_qr_title);
//        bottom_qr_title.setText("test");
//        bottom_qr_title.setTextSize((16 / mContext.getApplicationContext().getResources().getDisplayMetrics().scaledDensity));
        RelativeLayout.LayoutParams layout_93 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layout_93.addRule(RelativeLayout.ALIGN_BOTTOM, qr_code.getId());
        layout_93.addRule(RelativeLayout.RIGHT_OF, qr_code.getId());
        layout_93.leftMargin = 13;
        layout_93.bottomMargin = 20;


        bottom_qr_title.setLayoutParams(layout_93);

        relativeLayout_509.addView(bottom_qr_title);

        return relativeLayout_509;
    }


}
