package io.github.howiezuo.unsplash.feature.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.ViewModelHolder;
import io.github.howiezuo.unsplash.util.ActivityUtils;
import io.github.howiezuo.unsplash.viewmodel.PhotoViewModel;


public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO_ID = "PHOTO_ID";
    public static final String PHOTO_VIEW_MODEL_TAG = "PHOTO_VIEW_MODEL_TAG";

    private PhotoViewModel photoViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailFragment fragment = findOrCreateViewFragment();
        photoViewModel = findOrCreateViewModel();
        fragment.setViewModel(photoViewModel);
    }

    @Override
    protected void onDestroy() {
        photoViewModel.destroy();

        super.onDestroy();
    }

    private DetailFragment findOrCreateViewFragment() {
        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            String photoId = getIntent().getStringExtra(EXTRA_PHOTO_ID);
            fragment = DetailFragment.newInstance(photoId);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        return fragment;
    }

    private PhotoViewModel findOrCreateViewModel() {
        ViewModelHolder<PhotoViewModel> retainedHolder = (ViewModelHolder<PhotoViewModel>) getSupportFragmentManager().findFragmentByTag(PHOTO_VIEW_MODEL_TAG);
        if (retainedHolder != null && retainedHolder.getViewModel() != null) {
            return retainedHolder.getViewModel();
        } else {
            PhotoViewModel viewModel = new PhotoViewModel();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), ViewModelHolder.createHolder(viewModel), PHOTO_VIEW_MODEL_TAG);
            return viewModel;
        }
    }
}
