package com.kotlin.image.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.kotlin.image.weiget.ItemImage2;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    private List<String> list = new ArrayList<>();

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ImageHolder(new ItemImage2(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder imageHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder {

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
