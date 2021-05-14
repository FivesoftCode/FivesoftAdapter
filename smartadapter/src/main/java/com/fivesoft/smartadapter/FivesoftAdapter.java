package com.fivesoft.smartadapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class FivesoftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    AdapterData<Item> items;
    ArrayList<Integer> selectedItems = new ArrayList<>();
    Activity activity;

    private int maxSelectedItemCount = 1;
    private ItemSwitchMode itemSwitchMode = ItemSwitchMode.DO_NOT_SELECT_NEW;

    public FivesoftAdapter(@NonNull AdapterData<Item> items, @NonNull Activity activity){
        this.items = items;
        this.activity = activity;
    }

    /**
     * Sets the maximum count of items user can
     * have selected at the same time.
     * @param i Maximum count of items.
     * @return This FivesoftAdapter object.
     */

    public FivesoftAdapter setMaxSelectedItemCount(int i){
        this.maxSelectedItemCount = i;

        while (selectedItems.size() > maxSelectedItemCount) {
            int index = findItemPositionById(selectedItems.get(0));
            if(index >= 0) {
                items.get(index).isSelected = false;
            }
            selectedItems.remove(0);
        }

        notifyDataSetChanged();

        return this;
    }

    /**
     * Setups selection behavior when
     * user is trying to select more items
     * than {@link #maxSelectedItemCount}
     */

    public FivesoftAdapter setItemSwitchMode(ItemSwitchMode itemSwitchMode){
        this.itemSwitchMode = itemSwitchMode;
        return this;
    }

    /**
     * Returns all selected items.
     * @return List of selected items.
     */

    public List<Item> getSelectedItems(){
        List<Item> res = new ArrayList<>();
        for(int itemId: selectedItems){
            int index = findItemPositionById(itemId);
            if (index >= 0)
                res.add(items.get(index));
        }
        return res;
    }

    /**
     * Returns all items in this adapter.
     * @return List of all items.
     */

    public List<Item> getItems(){
        return items;
    }

    public List<?> getSelectedItemsData(){
        List<?> res = new ArrayList<>();
        for(int itemId: selectedItems){
            int index = findItemPositionById(itemId);
            if (index >= 0)
                res.add(items.get(index).getData());
        }
        return res;
    }

    public List<?> getItemsData(){
        List<?> res = new ArrayList<>();
        for(Item item: items){
            res.add(item.getData());
        }
        return res;
    }

    public static class Builder {

        private final AdapterData<Item> items = new AdapterData<>();

        public Builder(){

        }

        public Builder addItem(Item item){
            items.add(item);
            return this;
        }

        public AdapterData<Item> build(){
            return items;
        }
    }

    /**
     * Describes the items behavior when
     * user is trying to select more items
     * than {@link #maxSelectedItemCount}
     */

    public enum ItemSwitchMode {

        UNSELECT_FIRST,

        DO_NOT_SELECT_NEW

    }

    //Internal methods

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int itemType) {
        return new ItemHolder(LayoutInflater.from(activity).inflate(itemType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try {
            Item item = items.get(choosePosition(holder, position));
            item.onViewCreated(((ItemHolder) holder).view, holder, activity);
            item.onSelectionChanged(item.isSelected);
            item.onItemSelectionChangeListener = isSelected -> {
                if(isSelected){
                    selectItem(choosePosition(holder, position));
                } else {
                    unselectItem(choosePosition(holder, position));
                }
            };
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returns all item count.
     * @return Item count.
     */

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).viewId;
    }

    private static class ItemHolder extends RecyclerView.ViewHolder {

        public View view;

        public ItemHolder(View view){
            super(view);
            this.view = view;
        }
    }

    private void selectItem(int position){
        Item item = items.get(position);
        if(itemSwitchMode.equals(ItemSwitchMode.DO_NOT_SELECT_NEW) && selectedItems.size() + 1 > maxSelectedItemCount){
            item.setSelected(false);
            return;
        } else {
            item.isSelected = true;
        }
        //Removes to avoid double selection of single item
        int index = selectedItems.indexOf(item.id);
        if(index >= 0) {
            selectedItems.remove(index);
        }
        //Selects item
        selectedItems.add(item.id);
        notifyItemChanged(position);

        while (selectedItems.size() > maxSelectedItemCount) {
            index = findItemPositionById(selectedItems.get(0));
            if(index >= 0) {
                items.get(index).isSelected = false;
                notifyItemChanged(index);
            }
            selectedItems.remove(0);
        }
    }

    private void unselectItem(int position){
        Item item = items.get(position);
        //Removes item from selected
        int index = selectedItems.indexOf(item.id);
        if(index >= 0) {
            selectedItems.remove(index);
        }
        notifyItemChanged(position);
        notifyDataSetChanged();
    }

    /**
     * Returns item position base on id.
     * @see Item#getId()
     * @param id The item it.
     * @return Item position.
     */

    public int findItemPositionById(int id){
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).id == id){
                return i;
            }
        }
        return -1;
    }

    private int choosePosition(RecyclerView.ViewHolder holder, int position){
        if(holder.getAdapterPosition() < 0 || holder.getAdapterPosition() >= getItemCount()){
            return position;
        } else {
            return holder.getAdapterPosition();
        }
    }


}
