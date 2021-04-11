package com.fivesoft.fivesoftadapterdemoapp;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fivesoft.smartadapter.ActionListener;
import com.fivesoft.smartadapter.Item;

public class SimpleItem extends Item {

    private String data;
    private TextView textView;
    private ActionListener<String> actionListener;

    public SimpleItem(String data, @NonNull ActionListener<String> actionListener){
        super(R.layout.simple_item);
        this.data = data;
        this.actionListener = actionListener;
    }

    protected SimpleItem(int viewId) {
        super(viewId);
    }

    @Override
    protected void onViewCreated(View view, RecyclerView.ViewHolder holder, Activity activity) {

        textView = view.findViewById(R.id.text);
        textView.setText(data);

        textView.setOnClickListener(v -> {
            setSelected(!isSelected());
        });

        if(isSelected()){
            textView.setBackgroundColor(Color.parseColor("#EFEFEF"));
        } else {
            textView.setBackgroundColor(Color.WHITE);
        }

        textView.setOnLongClickListener(v -> {
            actionListener.onAction(ActionListener.ACTION_LONG_CLICK, holder.getAdapterPosition(), data);
            return true;
        });
    }

    @Override
    protected void onSelectionChanged(boolean isSelected) {
        if(isSelected){
            textView.setBackgroundColor(Color.parseColor("#EFEFEF"));
        } else {
            textView.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public <T> T getData() {
        return (T) data;
    }
}
