package io.github.howiezuo.unsplash.feature.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.howiezuo.unsplash.databinding.FragmentDetailBinding;
import io.github.howiezuo.unsplash.viewmodel.PhotoViewModel;


public class DetailFragment extends Fragment {

    public static final String ARG_PHOTO_ID = "PHOTO_ID";

    private PhotoViewModel photoViewModel;

    private FragmentDetailBinding detailBinding;

    public DetailFragment() {
        //
    }

    public static DetailFragment newInstance(String photoId) {
        Bundle args = new Bundle();
        args.putString(ARG_PHOTO_ID, photoId);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false);
        detailBinding.setViewModel(photoViewModel);
        return detailBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        photoViewModel.setId(getArguments().getString(ARG_PHOTO_ID));
        photoViewModel.load();
    }

    public void setViewModel(PhotoViewModel viewModel) {
        photoViewModel = viewModel;
    }
}
