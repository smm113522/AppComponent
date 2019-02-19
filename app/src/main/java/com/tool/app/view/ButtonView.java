package com.tool.app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.tool.app.R;

public class ButtonView extends ImageView {

    // 定义画笔
    private Paint mPaint = new Paint();
    // 用于获取文字的宽和高
    private final RectF mDrawableRect = new RectF();


    public ButtonView(Context context,AttributeSet attrs){
        super(context, attrs);
        Log.d("view","init");
    }

    public ButtonView(Context context) {
        super(context);
//        invalidate();//ondraw
//        requestLayout();//onmesasure
        // 初始化画笔、Rect

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("view", "onMeasure");

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d("view", "dispatchDraw");
    }
    private static final ScaleType SCALE_TYPE = ScaleType.CENTER_CROP;

    private static final int DEFAULT_CIRCLE_BACKGROUND_COLOR = Color.BLACK;

    private final Paint mCircleBackgroundPaint = new Paint();

    private int mCircleBackgroundColor = DEFAULT_CIRCLE_BACKGROUND_COLOR;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("view", "onDraw");

        super.setScaleType(SCALE_TYPE);

        Drawable drawable = getDrawable();

        // 将图片转为位图
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap mBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);

        Canvas canvas1 = new Canvas(mBitmap);
        drawable.setBounds(0, 0, canvas1.getWidth(), canvas1.getHeight());
        drawable.draw(canvas1);

        BitmapShader mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mPaint.setAntiAlias(true);
        mPaint.setShader(mBitmapShader);

        int mBitmapHeight = mBitmap.getHeight();
        int mBitmapWidth = mBitmap.getWidth();

        mDrawableRect.set(calculateBounds());

        float mDrawableRadius = Math.min(mDrawableRect.height() / 2.0f, mDrawableRect.width() / 2.0f);

        mCircleBackgroundPaint.setStyle(Paint.Style.FILL);
        mCircleBackgroundPaint.setAntiAlias(true);
        mCircleBackgroundPaint.setColor(mCircleBackgroundColor);

//        canvas.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mDrawableRadius, mCircleBackgroundPaint);

        canvas.drawCircle(mDrawableRect.centerX(), mDrawableRect.centerY(), mDrawableRadius, mPaint);
        invalidate();
//        canvas.drawCircle(width / 2,
//                height / 2, width / 2,
//                mPaint);

    }

    private RectF calculateBounds() {
        int availableWidth  = getWidth() - getPaddingLeft() - getPaddingRight();
        int availableHeight = getHeight() - getPaddingTop() - getPaddingBottom();

        int sideLength = Math.min(availableWidth, availableHeight);

        float left = getPaddingLeft() + (availableWidth - sideLength) / 2f;
        float top = getPaddingTop() + (availableHeight - sideLength) / 2f;

        return new RectF(left, top, left + sideLength, top + sideLength);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("view", "onLayout");

    }


}
