package io.github.howiezuo.unsplash.feature.detail;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.app.ActivityScope;
import io.github.howiezuo.unsplash.app.ViewModelHolder;
import io.github.howiezuo.unsplash.util.ActivityUtils;

@Module
public class DetailViewModelModule {

    private final AppCompatActivity activity;
    private String tag;

    public DetailViewModelModule(AppCompatActivity activity, String tag) {
        this.activity = activity;
        this.tag = tag;
    }

    @Provides
    @ActivityScope
    DetailViewModel provideMainViewModel() {
        ViewModelHolder<DetailViewModel> holder = (ViewModelHolder<DetailViewModel>) activity.getSupportFragmentManager().findFragmentByTag(tag);

        if (holder != null && holder.getViewModel() != null) {
            return holder.getViewModel();
        } else {
            DetailViewModel viewModel = new DetailViewModel();
            ActivityUtils.addFragmentToActivity(activity.getSupportFragmentManager(), ViewModelHolder.createHolder(viewModel), tag);
            return viewModel;
        }
    }
}
