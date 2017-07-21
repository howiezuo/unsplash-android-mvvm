package io.github.howiezuo.unsplash.feature.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.howiezuo.unsplash.databinding.ItemPhotoBinding
import io.github.howiezuo.unsplash.model.Photo


class MainAdapter(val listener: MainListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataSet = ArrayList<Photo>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as PhotoViewHolder).bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return PhotoViewHolder(binding, listener)
    }

    fun setDateSet(photos: List<Photo>) {
        dataSet.clear()
        dataSet.addAll(photos)
        notifyDataSetChanged()
    }

}
