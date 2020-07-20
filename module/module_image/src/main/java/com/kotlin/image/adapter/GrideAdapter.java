package com.kotlin.image.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.kotlin.image.weiget.ItemImage1;

import java.util.ArrayList;
import java.util.List;

public class GrideAdapter extends RecyclerView.Adapter<GrideAdapter.GrideHolder> {

    private List<String> list = new ArrayList<>();

    @NonNull
    @Override
    public GrideHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new GrideHolder(new ItemImage1(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull GrideHolder grideHolder, int i) {
    
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class GrideHolder extends RecyclerView.ViewHolder {

        public GrideHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
