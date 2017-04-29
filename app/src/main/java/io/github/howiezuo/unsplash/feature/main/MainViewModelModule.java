package io.github.howiezuo.unsplash.feature.main;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.app.ActivityScope;
import io.github.howiezuo.unsplash.app.ViewModelHolder;
import io.github.howiezuo.unsplash.util.ActivityUtils;

@Module
public class MainViewModelModule {

    private final AppCompatActivity activity;
    private String tag;

    public MainViewModelModule(AppCompatActivity activity, String tag) {
        this.activity = activity;
        this.tag = tag;
    }

    @Provides
    @ActivityScope
    MainViewModel provideMainViewModel() {
        ViewModelHolder<MainViewModel> holder = (ViewModelHolder<MainViewModel>) activity.getSupportFragmentManager().findFragmentByTag(tag);

        if (holder != null && holder.getViewModel() != null) {
            return holder.getViewModel();
        } else {
            MainViewModel viewModel = new MainViewModel();
            ActivityUtils.addFragmentToActivity(activity.getSupportFragmentManager(), ViewModelHolder.createHolder(viewModel), tag);
            return viewModel;
        }
    }
}
