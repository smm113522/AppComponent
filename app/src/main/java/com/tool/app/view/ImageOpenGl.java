package com.tool.app.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.tool.app.R;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class ImageOpenGl extends GLSurfaceView implements GLSurfaceView.Renderer {

    Bitmap bitmap;

    static float aFloat[] = {
            -1.0f,-1.0f,

    };

    public ImageOpenGl(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.imge);

    }

    public ImageOpenGl(Context context, AttributeSet attrs) {
        super(context, attrs);

    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //初始化frag 文件里面内容

    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {

        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {

    }
}
