package io.github.howiezuo.unsplash.feature.main;

import dagger.Component;
import io.github.howiezuo.unsplash.app.ActivityScope;

@ActivityScope
@Component(
        modules = {
                MainViewModelModule.class
        }
)
public interface MainComponent {

    void inject(MainActivity activity);

}