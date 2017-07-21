package io.github.howiezuo.unsplash.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import io.github.howiezuo.unsplash.feature.main.MainAdapter
import io.github.howiezuo.unsplash.model.Photo


object PhotosBinding {

    @JvmStatic
    @BindingAdapter("bind:items")
    fun setItems(recyclerView: RecyclerView, photos: List<Photo>) {
        val adapter: MainAdapter? = recyclerView.adapter as? MainAdapter
        adapter?.setDateSet(photos)
    }

}
