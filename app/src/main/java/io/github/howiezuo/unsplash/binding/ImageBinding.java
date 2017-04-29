package io.github.howiezuo.unsplash.binding;

import android.databinding.BindingAdapter;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageBinding {

    @BindingAdapter("bind:url")
    public static void setUrl(final ImageView imageView, final String url) {
        if (url == null) return;

        imageView.post(new Runnable() {
            @Override
            public void run() {
                if (imageView == null || url == null) return;

                Glide.with(imageView.getContext())
                        .load(url)
                        .centerCrop()
                        .into(imageView);
            }
        });
    }

    @BindingAdapter({"bind:url", "bind:ratio"})
    public static void setUrl(final ImageView imageView, final String url, final double ratio) {
        if (url == null) return;

        imageView.post(new Runnable() {
            @Override
            public void run() {
                if (imageView == null || url == null) return;

                int w = imageView.getWidth();
                int h = (int) (w / ratio);

                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.height = h;
                imageView.setLayoutParams(params);
                Glide.with(imageView.getContext())
                        .load(url)
                        .centerCrop()
                        .override(w, h)
                        .into(imageView);
            }
        });
    }
}
