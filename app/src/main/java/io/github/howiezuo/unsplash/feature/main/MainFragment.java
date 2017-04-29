package io.github.howiezuo.unsplash.feature.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.howiezuo.unsplash.databinding.FragmentMainBinding;
import io.github.howiezuo.unsplash.viewmodel.PhotosViewModel;


public class MainFragment extends Fragment {

    private PhotosViewModel photosViewModel;

    private FragmentMainBinding mainBinding;

    public MainFragment() {
        //
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false);
        mainBinding.setViewModel(photosViewModel);
        return mainBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = mainBinding.rvPhotos;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MainAdapter((MainListener) getActivity()));

        photosViewModel.load();
    }

    public void setViewModel(PhotosViewModel viewModel) {
        photosViewModel = viewModel;
    }
}
