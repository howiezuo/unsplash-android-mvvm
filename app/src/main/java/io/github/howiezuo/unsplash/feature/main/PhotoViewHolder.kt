package io.github.howiezuo.unsplash.feature.main

import android.support.v7.widget.RecyclerView
import io.github.howiezuo.unsplash.databinding.ItemPhotoBinding
import io.github.howiezuo.unsplash.model.Photo
import io.github.howiezuo.unsplash.viewmodel.PhotoViewModel


class PhotoViewHolder(
        private val binding: ItemPhotoBinding,
        private val listener: MainListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(photo: Photo) {
        val vm = PhotoViewModel()
        vm.setPhoto(photo)
        vm.setMainListener(listener)
        binding.viewModel = vm
        binding.executePendingBindings()
    }

}
