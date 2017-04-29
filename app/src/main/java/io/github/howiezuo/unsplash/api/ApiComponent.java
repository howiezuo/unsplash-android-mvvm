package io.github.howiezuo.unsplash.api;

import javax.inject.Singleton;

import dagger.Component;
import io.github.howiezuo.unsplash.feature.detail.DetailViewModel;
import io.github.howiezuo.unsplash.feature.main.MainViewModel;

@Singleton
@Component(
        modules = {
                ApiModule.class
        }
)
public interface ApiComponent {

    void inject(MainViewModel viewModel);

    void inject(DetailViewModel viewModel);

}
