package com.tool.app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.tool.app.R;

public class ButtonView extends ImageView {

    // 定义画笔
    private Paint mPaint;
    // 用于获取文字的宽和高
    private Rect mBounds;
    // 计数值，每点击一次本控件，其值增加1
    private int mCount;

    public ButtonView(Context context,AttributeSet attrs){
        super(context, attrs);
        Log.d("view","init");
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
    }

    /*public ButtonView(Context context) {
        super(context);
//        invalidate();//ondraw

//        requestLayout();//onmesasure
        // 初始化画笔、Rect

    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("view", "onmeasure");

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d("view", "dispatchDraw");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("view", "onDraw");

        Drawable drawable = getDrawable();

        // 将图片转为位图
        Bitmap mBitmap = ((BitmapDrawable) drawable).getBitmap();

        Bitmap cpBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
        // 得到画布宽高
        int width = getWidth();
        int height = getHeight();

//        Canvas canvas = new Canvas(cpBitmap);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(cpBitmap, 0f, 0f, mPaint);
        // 绘制圆形
//        canvas.drawCircle(width / 2,
//                height / 2, width / 2,
//                mPaint);
        canvas.drawCircle(width / 2,
                height / 2, width / 2,
                mPaint);

        Bitmap outputbmp = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas(outputbmp);// 创建一个相同大小的画布
        Paint paint = new Paint();// 定义画笔
        paint.setAntiAlias(true);// 设置抗锯齿
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas1.drawARGB(0, 0, 0, 0);
        canvas1.drawCircle(width / 2,
                height / 2, width / 2,
                paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas1.drawBitmap(cpBitmap, 0, 0, paint);

        canvas.drawBitmap(cpBitmap, 0f, 0f, mPaint);
//
//        mPaint.setColor(Color.BLUE);
//        // 绘制一个填充色为蓝色的矩形
//        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaint);
//
//        mPaint.setColor(Color.YELLOW);
//        mPaint.setTextSize(50);
//        String text = String.valueOf(mCount);
//        // 获取文字的宽和高
//        mPaint.getTextBounds(text, 0, text.length(), mBounds);
//        float textWidth = mBounds.width();
//        float textHeight = mBounds.height();
//
//        // 绘制字符串
//        canvas.drawText(text, getWidth() / 2 - textWidth / 2, getHeight() / 2
//                + textHeight / 2, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("view", "onLayout");

    }


}
