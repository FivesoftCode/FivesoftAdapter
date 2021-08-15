package com.fivesoft.smartadapter;

import static androidx.recyclerview.widget.RecyclerView.NO_POSITION;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FivesoftViewHolder extends RecyclerView.ViewHolder {

    int savedPosition = NO_POSITION;

    public FivesoftViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public FivesoftViewHolder savePosition(int position){
        this.savedPosition = position;
        return this;
    }

    public int getItemPosition(){
        if(getAdapterPosition() == NO_POSITION){
            return savedPosition;
        } else {
            return getAdapterPosition();
        }
    }

    public View getItemView(){
        return itemView;
    }
}
