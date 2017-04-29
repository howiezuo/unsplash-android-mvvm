package io.github.howiezuo.unsplash.feature.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.feature.detail.DetailActivity;
import io.github.howiezuo.unsplash.util.ActivityUtils;


public class MainActivity extends AppCompatActivity implements MainListener {

    public static final String PHOTOS_VIEW_MODEL_TAG = "PHOTOS_VIEW_MODEL_TAG";

    @Inject
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpDagger();

        MainFragment fragment = findOrCreateViewFragment();
        fragment.setViewModel(mainViewModel);
    }

    @Override
    protected void onDestroy() {
        mainViewModel.destroy();

        super.onDestroy();
    }

    private void setUpDagger() {
        DaggerMainComponent.builder()
                .mainViewModelModule(new MainViewModelModule(this, PHOTOS_VIEW_MODEL_TAG))
                .build()
                .inject(this);
    }

    private MainFragment findOrCreateViewFragment() {
        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        return fragment;
    }

    @Override
    public void openPhotoDetail(String photoId) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_PHOTO_ID, photoId);
        startActivity(intent);
    }
}
