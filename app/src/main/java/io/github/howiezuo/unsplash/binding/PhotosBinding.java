package io.github.howiezuo.unsplash.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import io.github.howiezuo.unsplash.feature.MainAdapter;
import io.github.howiezuo.unsplash.model.Photo;

public class PhotosBinding {

    @BindingAdapter("bind:items")
    public static void setItems(RecyclerView recyclerView, List<Photo> photos) {
        MainAdapter adapter = (MainAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setDataSet(photos);
        }
    }

}
