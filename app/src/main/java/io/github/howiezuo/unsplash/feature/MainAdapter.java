package io.github.howiezuo.unsplash.feature;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.github.howiezuo.unsplash.databinding.ItemPhotoBinding;
import io.github.howiezuo.unsplash.model.Photo;


public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photo> dataSet = new ArrayList<>();

    private MainListener mainListener;

    public MainAdapter(MainListener listener) {
        mainListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPhotoBinding bind = ItemPhotoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PhotoViewHolder(bind, mainListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PhotoViewHolder) holder).bind(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setDataSet(List<Photo> photos) {
        this.dataSet = photos;
        notifyDataSetChanged();
    }
}
