package org.example.kotlin.mixed;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

public class JavaActivity extends Activity {

    Context mContext = null;

    final String URL_001 = "http://news.sina.com.cn/w/2018-04-22/doc-ifznefkh5015818.shtml";
    final String TITLE2 = "为了与更多人同行，环保也可以跨界";
    final String SELECTED_TEXT = "在格鲁吉亚高加索山半山腰上，有一家独特的集装箱酒店，这家生态酒店房屋不用一砖一瓦，只用集装箱打造而成，一个个独立的箱子在钢架的支撑下叠加在一起，组成形似金字塔的酒店。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        LinearLayout root = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_main, null);

        setContentView(root);

        setupContent(root);

    }



    void setupContent(LinearLayout root) {
        Button next = (Button) findViewById(R.id.Button01);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), KotlinActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        final EditText et = new EditText(mContext);
        et.setText("code++");
        root.addView(et);


        Button innovate = new Button(mContext);
        innovate.setText("Innovate");
        innovate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = et.getText().toString();

                Toast.makeText(mContext, "" + text, Toast.LENGTH_SHORT).show();

//                Drawable logo_dr = getResources().getDrawable(R.drawable.ic_launcher);
                Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

                Bitmap code_plus = null;
                try {
                    code_plus = createCode(URL_001, logo, BarcodeFormat.QR_CODE);
                } catch (Throwable t) {
                    t.printStackTrace();
                }


                // bottom
                RelativeLayout bottom = (RelativeLayout) getLayoutInflater().inflate(R.layout.bottom_view, null);
//                bottom.setOrientation(LinearLayout.HORIZONTAL);

                // title2
                TextView title2 = (TextView) bottom.findViewById(R.id.bottom_qr_title);
                title2.setText(TITLE2);
                title2.setTextSize(20);
//                title2.setLayoutDirection();

                // code++
                ImageView im = (ImageView) bottom.findViewById(R.id.qr_code);
                im.setImageBitmap(code_plus);


                RelativeLayout.LayoutParams lp01 = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp01.leftMargin = 25;
                lp01.rightMargin = 25;
                lp01.bottomMargin = 30;

//                // merge
//                bottom.addView(im);
//                bottom.addView(title2);
                bottom.setLayoutParams(lp01);

                // upper
                LinearLayout upper = new LinearLayout(mContext);

                TextView selected_text = new TextView(mContext);
                selected_text.setText(SELECTED_TEXT);
                selected_text.setTextSize(15);
//                selected_text.setTextColor(0xaaa);

                LinearLayout.LayoutParams lp02 = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                lp02.leftMargin = 25;
                lp02.rightMargin = 25;
                lp02.topMargin = 30;
                lp02.bottomMargin = 30;

                upper.addView(selected_text);
                upper.setLayoutParams(lp02);

                // whole
                LinearLayout root = new LinearLayout(mContext);
                root.setOrientation(LinearLayout.VERTICAL);

                // merge all
                root.addView(upper);
                root.addView(bottom);

                root.setBackgroundColor(0xC7EDCC);

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setIcon(R.drawable.ic_launcher);

                builder.setTitle(text);

                builder.setView(root);

//                builder.setMessage("QrCode++");

                builder.setNegativeButton("Cancel", null);

                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        root.addView(innovate);

    }

    private static final int IMAGE_HALFWIDTH = 18;//宽度值，影响中间图片大小

    // 512
    // 128
    private static final int QR_CODE_SIZE = 256;//宽度值，影响中间图片大小


    /**
     * 生成二维码
     *
     * @param url     二维码中包含的文本信息
     * @param mBitmap logo图片
     * @param format  编码格式
     * @return Bitmap 位图
     * @throws WriterException
     */
    public Bitmap createCode(String url, Bitmap mBitmap, BarcodeFormat format)
            throws WriterException {
        Matrix m = new Matrix();
        float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();
        float sy = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getHeight();
        m.setScale(sx, sy);//设置缩放信息

        //将logo图片按martix设置的信息缩放
        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,
                mBitmap.getWidth(), mBitmap.getHeight(), m, false);

        MultiFormatWriter writer = new MultiFormatWriter();

        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
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
                if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
                        && y > halfH - IMAGE_HALFWIDTH
                        && y < halfH + IMAGE_HALFWIDTH) {//该位置用于存放图片信息

                    //记录图片每个像素信息
                    pixels[y * width + x] = mBitmap.getPixel(x - halfW
                            + IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    ViewGroup createView(Context context, String text, String url) {
        return null;
    }


}
