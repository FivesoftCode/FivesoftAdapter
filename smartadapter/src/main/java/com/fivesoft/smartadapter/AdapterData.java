package com.fivesoft.smartadapter;


import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class AdapterData<T> extends ArrayList<T> {

    private RecyclerView.Adapter<?> adapter;

    public AdapterData(){
        super();
    }

    public AdapterData(Collection<T> src){
        super(src);
    }

    public void attachToRecyclerView(@NonNull RecyclerView recyclerView){
        this.adapter = recyclerView.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public boolean isAttachedToRecyclerView(){
        return adapter != null;
    }

    @Override
    public boolean add(T t) {
        super.add(t);
        if(adapter != null)
            adapter.notifyItemInserted(size() - 1);
        return true;
    }

    @Override
    public void add(int index, T element) {
        super.add(index, element);
        if(adapter != null)
            adapter.notifyItemInserted(index);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends T> c) {
        return addAll(size(), c);
    }

    @Override
    public boolean addAll(int index, @NonNull Collection<? extends T> c) {
        boolean res = super.addAll(index, c);
        if(res)
            if(adapter != null)
                adapter.notifyItemRangeInserted(index, c.size());
        return res;
    }

    @Override
    public void clear() {
        super.clear();
        if(adapter != null)
            adapter.notifyItemRangeRemoved(0, size());
    }

    @Override
    public T remove(int index) {
        T res = super.remove(index);
        if(adapter != null)
            adapter.notifyItemRemoved(index);
        return res;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        boolean res = super.remove(o);
        if(res)
            if(adapter != null)
                adapter.notifyItemRemoved(indexOf(o));
        return res;
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> c) {
        int removed = 0;
        for(int x = 0; x < size(); x++){
            if(c.contains(get(x))) {
                remove(x);
                if(adapter != null)
                    adapter.notifyItemRemoved(x);
                removed++;
                x--;
            }
        }
        if(adapter != null)
            adapter.notifyDataSetChanged();
        return removed > 0;
    }

    @Override
    public void sort(@Nullable Comparator<? super T> c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            super.sort(c);
        } else {
            Collections.sort(this, c);
        }
        if(adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> c) {
        int removed = 0;
        for(int x = 0; x < size(); x++){
            if(!c.contains(get(x))) {
                remove(x);
                if(adapter != null)
                    adapter.notifyItemRemoved(x);
                removed++;
                x--;
            }
        }
        return removed > 0;
    }

    @Override
    public T set(int index, T element) {
        T res = super.set(index, element);
        if(adapter != null)
            adapter.notifyItemChanged(index);
        return res;
    }

    public void swap(int index1, int index2){
        Collections.swap(this, index1, index2);
        if(adapter != null) {
            adapter.notifyItemMoved(index1, index2);
            adapter.notifyItemMoved(index2, index1);
        }
    }

}
