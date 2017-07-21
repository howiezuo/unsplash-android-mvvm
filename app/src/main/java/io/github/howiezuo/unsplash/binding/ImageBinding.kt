package io.github.howiezuo.unsplash.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


object ImageBinding {

    @JvmStatic
    @BindingAdapter("bind:url")
    fun setUrl(imageView: ImageView, url: String?) {
        if (url == null) return

        imageView.post({
            Glide.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .into(imageView)
        })
    }

    @JvmStatic
    @BindingAdapter("bind:url", "bind:ratio")
    fun setUrl(imageView: ImageView, url: String?, ratio: Double?) {
        if (url == null || ratio == null) return

        imageView.post({
            val w = imageView.width
            val h = (w / ratio).toInt()
            imageView.layoutParams.height = h
            Glide.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .override(w, h)
                    .into(imageView)
        })
    }

}
