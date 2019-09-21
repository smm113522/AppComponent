package com.kotlin;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class GlideUtils {

    public static void showImage(Activity activity, ImageView imageView,Object url,
                                 int placeholder,int error){

        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(placeholder)
                .error(error)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

//        options.skipMemoryCache(true);//跳过内存

        Glide.with(activity).load(url)
                .apply(options)
                .apply(bitmapTransform(new CropCircleTransformation()))
                .apply(bitmapTransform(new BlurTransformation( 25, 4)))
                .into(imageView);



    }


    public static RequestBuilder<Bitmap> getGLideBitmap(Activity activity, Object url){

        RequestBuilder<Bitmap> bitmap = Glide
                .with(activity)
                .asBitmap()//这一个必须加
        .load(url);

        Glide.with(activity)
                .asBitmap()
                .load(url)
                .into(new Target<Bitmap>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onStop() {

                    }

                    @Override
                    public void onDestroy() {

                    }

                    @Override
                    public void onLoadStarted(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {

                    }

                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }

                    @Override
                    public void getSize(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void removeCallback(@NonNull SizeReadyCallback cb) {

                    }

                    @Override
                    public void setRequest(@Nullable Request request) {

                    }

                    @Nullable
                    @Override
                    public Request getRequest() {
                        return null;
                    }
                });
//                .load(url).thumbnail(new RequestBuilder<Bitmap>())
        return bitmap.listener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        });
//
    }

}
