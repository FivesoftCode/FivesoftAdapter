package com.fivesoft.smartadapter;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

public abstract class Item {

    int viewId;
    int id;
    boolean isSelected = false;
    OnItemSelected onItemSelectionChangeListener;

    protected Item(@LayoutRes int viewId){
        this.viewId = viewId;
        this.id = hashCode();
    }

    protected abstract void onViewCreated(View view, RecyclerView.ViewHolder viewHolder, Activity activity);

    protected abstract void onSelectionChanged(boolean isSelected);

    public abstract <T> T getData();

    public boolean isSelected(){
        return isSelected;
    }

    public final Item setSelected(boolean isSelected) {
        this.isSelected = isSelected;
        if(onItemSelectionChangeListener != null)
            onItemSelectionChangeListener.onItemSelectionChange(isSelected);
        onSelectionChanged(isSelected);
        return this;
    }

    public int getId(){
        return id;
    }

    interface OnItemSelected{
        void onItemSelectionChange(boolean isSelected);
    }


}
