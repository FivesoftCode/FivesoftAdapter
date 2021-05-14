package com.fivesoft.fivesoftadapterdemoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.fivesoft.smartadapter.ActionListener;
import com.fivesoft.smartadapter.AdapterData;
import com.fivesoft.smartadapter.FivesoftAdapter;
import com.fivesoft.smartadapter.Item;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterData<Item> adapterData = new AdapterData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FivesoftAdapter.Builder builder = new FivesoftAdapter.Builder();


        ActionListener<String> actionListener = (action, position, data) -> {
            adapterData.remove(position);
        };

        builder.addItem(new SimpleItem("Selection 1", actionListener))
                .addItem(new SimpleItem("Selection 2", actionListener))
                .addItem(new SimpleItem("Selection 3", actionListener))
                .addItem(new SimpleItem("Selection 4", actionListener))
                .addItem(new SimpleItem("Selection 5", actionListener))
                .addItem(new SimpleItem("Selection 6", actionListener))
                .addItem(new SimpleItem("Selection 7", actionListener))
                .addItem(new SimpleItem("Selection 8", actionListener))
                .addItem(new SimpleItem("Selection 9", actionListener))
                .addItem(new SimpleItem("Selection 10", actionListener))
                .addItem(new SimpleItem("Selection 11", actionListener))
                .addItem(new SimpleItem("Selection 12", actionListener))
                .addItem(new SimpleItem("Selection 13", actionListener))
                .addItem(new SimpleItem("Selection 14", actionListener))
                .addItem(new SimpleItem("Selection 15", actionListener))
                .addItem(new SimpleItem("Selection 16", actionListener))
                .addItem(new SimpleItem("Selection 17", actionListener))
                .addItem(new SimpleItem("Selection 18", actionListener))
                .addItem(new SimpleItem("Selection 19", actionListener))
                .addItem(new SimpleItem("Selection 20", actionListener))
                .addItem(new SimpleItem("Selection 21", actionListener))
                .addItem(new SimpleItem("Selection 22", actionListener));

        adapterData = builder.build();

        recyclerView.setAdapter(new FivesoftAdapter(adapterData, this)
                .setItemSwitchMode(FivesoftAdapter.ItemSwitchMode.UNSELECT_FIRST)
                .setMaxSelectedItemCount(4));

        adapterData.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onBackPressed() {
        ((FivesoftAdapter) recyclerView.getAdapter()).setMaxSelectedItemCount(0);
    }
}