package io.github.howiezuo.unsplash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


public class ViewModelHolder<VM> extends Fragment {

    private VM viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    public static <M> ViewModelHolder createHolder(M viewModel) {
        ViewModelHolder<M> holder = new ViewModelHolder<>();
        holder.setViewModel(viewModel);
        return holder;
    }

    public VM getViewModel() {
        return viewModel;
    }

    public void setViewModel(VM viewModel) {
        this.viewModel = viewModel;
    }
}
