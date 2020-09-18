package com.kotlin.app.video;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.listener.ErrorListener;
import com.cjt2325.cameralibrary.listener.JCameraListener;
import com.cjt2325.cameralibrary.util.FileUtil;
import com.kotlin.app.R;
import com.luck.picture.lib.config.PictureConfig;


/**
 * 相机拍照和拍摄视频
 * yy
 */
public class CameraActivity extends AppCompatActivity {

    private static final String TAG = CameraActivity.class.getSimpleName();
    public static final String CACHE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/mio";

    //视频缓存目录
    public static final String VIDEO_CACHE = CACHE_DIR + "/cache/video/";
    private JCameraView cameraView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initCameraView();
        getIntentData();
    }

    private void initCameraView() {
        cameraView = (JCameraView) findViewById(R.id.cameraView);
        cameraView.setSaveVideoPath(VIDEO_CACHE);
        cameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);
        cameraView.setMediaQuality(JCameraView.MEDIA_QUALITY_MIDDLE);

        cameraView.setErrorLisenter(new ErrorListener() {
            @Override
            public void onError() {
                //错误监听
                Log.i("CJT", "camera error");
                Intent intent = new Intent();
                setResult(103, intent);
                finish();
            }

            @Override
            public void AudioPermissionError() {
                Toast.makeText(CameraActivity.this, "给点录音权限可以?", Toast.LENGTH_SHORT).show();
            }
        });

        cameraView.setJCameraLisenter(new JCameraListener() {
            @Override
            public void captureSuccess(Bitmap bitmap) {
                String path = FileUtil.saveBitmap("JCamera", bitmap);
                Intent intent = new Intent();
                intent.putExtra("path", path);
                setResult(101, intent);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void recordSuccess(final String url, Bitmap firstFrame) {
//                DebugUtil.i("record bitmap:url = " + url + ", height:" + firstFrame.getHeight());

                String path = FileUtil.saveBitmap("JCamera", firstFrame);
                Log.i("CJT", "url = " + url + ", Bitmap = " + path);
                Intent intent = new Intent();
                intent.putExtra("path", path);
                setResult(101, intent);
                finish();
            }

        });
    }

    private void getIntentData() {
        int type = getIntent().getIntExtra("type", PictureConfig.TYPE_ALL);

        switch (type) {
            case PictureConfig.TYPE_ALL:
                cameraView.setFeatures(JCameraView.BUTTON_STATE_BOTH);
                cameraView.setTip("轻按拍照，按住摄像");
                break;
            case PictureConfig.TYPE_IMAGE:
                cameraView.setFeatures(JCameraView.BUTTON_STATE_ONLY_CAPTURE);
                cameraView.setTip("轻按拍照");
                break;
            case PictureConfig.TYPE_VIDEO:
                cameraView.setFeatures(JCameraView.BUTTON_STATE_ONLY_RECORDER);
                cameraView.setTip("按住摄像");
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //全屏显示
        if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.onPause();
    }

}
