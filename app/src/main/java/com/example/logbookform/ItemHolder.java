package com.example.logbookform;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemHolder extends RecyclerView.ViewHolder {
    TextView itemRender;
    public ItemHolder(View itemView) {
        super(itemView);
        itemRender = itemView.findViewById(R.id.renderItem);
    }
}
