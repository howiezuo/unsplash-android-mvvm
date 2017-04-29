package io.github.howiezuo.unsplash.feature.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.util.ActivityUtils;


public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO_ID = "PHOTO_ID";
    public static final String PHOTO_VIEW_MODEL_TAG = "PHOTO_VIEW_MODEL_TAG";

    @Inject
    DetailViewModel detailViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setUpDagger();

        DetailFragment fragment = findOrCreateViewFragment();
        fragment.setViewModel(detailViewModel);
    }

    @Override
    protected void onDestroy() {
        detailViewModel.destroy();

        super.onDestroy();
    }

    private void setUpDagger() {
        DaggerDetailComponent.builder()
                .detailViewModelModule(new DetailViewModelModule(this, PHOTO_VIEW_MODEL_TAG))
                .build()
                .inject(this);
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
}
