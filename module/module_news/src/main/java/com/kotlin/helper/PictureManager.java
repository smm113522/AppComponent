package com.kotlin.helper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 *
 *  // 照相
 *             PictureManager.getInstance()
 *                     .with(this)
 *                     .setCallback(object : OnPictureCallback<String> {
 *                         override fun onCompleted(result: String?) {
 *                             Log.d("dddd", result)//
 *                             Glide.with(applicationContext).load(result).into(image)
 *                         }
 *
 *                         override fun onError(errorMsg: Throwable?) {
 *
 *                         }
 *                     }).startPhotoCamera()
 * // 选择图库
 *             PictureManager.getInstance()
 *                     .with(this)
 *                     .setCallback(object : OnPictureCallback<String> {
 *                         override fun onCompleted(result: String?) {
 *                             Log.d("dddd", result)//
 *                             Glide.with(applicationContext).load(result).into(image)
 *                         }
 *
 *                         override fun onError(errorMsg: Throwable?) {
 *
 *                         }
 *                     }).startPhotograph()
 *
 */

public class PictureManager extends PictureStrategy {

    /**
     * 扫描请求码
     */
    private static final int CAMERA_REQUEST_CODE = 666;
    private static final int PHOTO_REQUEST_CODE = 777;

    private static PictureManager mQRCodeManager;
    private Activity context;

    private OnPictureCallback callback;

    private File imageFile = null;
    private Uri imageUri = null;
    private String photoPath = null;

    private PictureManager() {
    }

    public static PictureManager getInstance() {
        synchronized (PictureManager.class) {
            if (mQRCodeManager == null) {
                mQRCodeManager = new PictureManager();
            }
        }
        return mQRCodeManager;
    }

    /**
     * 关联调用类
     *
     * @param context
     * @return
     */
    public PictureManager with(Activity context) {
        this.context = context;
        return this;
    }

    public PictureManager setCallback(OnPictureCallback callback) {
        this.callback = callback;
        return this;
    }

    /**
     * 结果回调
     *
     * @param requestCode 请求码
     * @param resultCode  结果码
     * @param data        数据
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (callback == null) {
            return;
        }

        if (resultCode == RESULT_OK || data != null){
            switch (requestCode){
                case CAMERA_REQUEST_CODE:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        photoPath = String.valueOf(imageFile);
                    } else {
                        photoPath = imageUri.getEncodedPath();
                    }
                    Log.d("拍照返回图片路径:", photoPath);
                    callback.onCompleted(photoPath);
                    break;
                case PHOTO_REQUEST_CODE:
                    photoPath = getPhotoFromPhotoAlbum.getRealPathFromUri(context, data.getData());
                    Log.d("相册返回图片路径:", photoPath);
                    callback.onCompleted(photoPath);
                    break;
            }
        }else {
            callback.onError(new Throwable("获取失败！"));
        }

    }

    /**
     * 打开照机
     */
    @Override
    public void startPhotoCamera() {
        imageFile = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        try {
            if (imageFile.exists()) {
                imageFile.delete();
            }
            imageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageUri = Uri.fromFile(imageFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            //第二个参数为 包名.fileprovider
//            imageUri = FileProvider.getUriForFile(context, "com.kotlin.app.fileprovider", imageFile);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        }
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        context.startActivityForResult(intent, CAMERA_REQUEST_CODE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址

//        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        context.startActivityForResult(intent, CAMERA_REQUEST_CODE); //启动照相

    }

    /**
     * 打开相册
     */
    @Override
    public void startPhotograph() {
        // 跳转到图片选择器
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        context.startActivityForResult(intent, PHOTO_REQUEST_CODE);
    }


}
