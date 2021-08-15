package com.fivesoft.smartadapter;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper.Callback;
import androidx.recyclerview.widget.RecyclerView;

public class FivesoftRecyclerView extends RecyclerView {

    public FivesoftRecyclerView(@NonNull Context context) {
        super(context);
    }

    public FivesoftRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FivesoftRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FivesoftRecyclerView setDisplayData(AdapterData<Item> adapterData, Activity activity){
        adapterData.attachToRecyclerView(this);
        return setAdapter(new FivesoftAdapter(adapterData, activity));
    }

    public FivesoftRecyclerView setAdapter(FivesoftAdapter adapter){
        super.setAdapter(adapter);
        adapter.items.attachToRecyclerView(this);
        return this;
    }

    public FivesoftAdapter getAdapter(){
        return (FivesoftAdapter) super.getAdapter();
    }
    

}
