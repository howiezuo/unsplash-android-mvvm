package io.github.howiezuo.unsplash.feature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.ViewModelHolder;
import io.github.howiezuo.unsplash.util.ActivityUtils;
import io.github.howiezuo.unsplash.viewmodel.PhotosViewModel;


public class MainActivity extends AppCompatActivity implements MainListener {

    public static final String PHOTOS_VIEW_MODEL_TAG = "PHOTOS_VIEW_MODEL_TAG";

    private PhotosViewModel photosViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment fragment = findOrCreateViewFragment();
        photosViewModel = findOrCreateViewModel();
        fragment.setViewModel(photosViewModel);
    }

    @Override
    protected void onDestroy() {
        photosViewModel.destroy();

        super.onDestroy();
    }

    private MainFragment findOrCreateViewFragment() {
        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        return fragment;
    }

    private PhotosViewModel findOrCreateViewModel() {
        ViewModelHolder<PhotosViewModel> retainedHolder =
                (ViewModelHolder<PhotosViewModel>) getSupportFragmentManager().findFragmentByTag(PHOTOS_VIEW_MODEL_TAG);

        if (retainedHolder != null && retainedHolder.getViewModel() != null) {
            return retainedHolder.getViewModel();
        } else {
            PhotosViewModel viewModel = new PhotosViewModel();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), ViewModelHolder.createHolder(viewModel), PHOTOS_VIEW_MODEL_TAG);
            return viewModel;
        }
    }

    @Override
    public void openPhotoDetail(String photoId) {
        //
    }
}
