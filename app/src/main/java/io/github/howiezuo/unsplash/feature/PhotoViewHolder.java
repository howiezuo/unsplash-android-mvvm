package io.github.howiezuo.unsplash.feature;

import android.support.v7.widget.RecyclerView;

import io.github.howiezuo.unsplash.databinding.ItemPhotoBinding;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.viewmodel.PhotoViewModel;


public class PhotoViewHolder extends RecyclerView.ViewHolder {

    private final ItemPhotoBinding binding;
    private MainListener listener;

    public PhotoViewHolder(ItemPhotoBinding binding, MainListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        this.listener = listener;
    }

    public void bind(final Photo photo) {
        PhotoViewModel viewModel = new PhotoViewModel();
        viewModel.setPhoto(photo);
        viewModel.setMainListener(listener);
        this.binding.setViewModel(viewModel);
        this.binding.executePendingBindings();
    }
}
