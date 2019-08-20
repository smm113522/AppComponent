package com.kotlin.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class ImageUtils {


    public static Bitmap setImage(Context context, String path, ImageView imageView) {

//        Glide.with(context).asBitmap().load(path).into(imageView);

        Glide.with(context).asBitmap().load(path).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

            }

        });
        return null;

    }

}
