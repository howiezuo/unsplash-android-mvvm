package io.github.howiezuo.unsplash.feature.detail;

import dagger.Component;
import io.github.howiezuo.unsplash.app.ActivityScope;

@ActivityScope
@Component(
        modules = {
                DetailViewModelModule.class
        }
)
public interface DetailComponent {

    void inject(DetailActivity activity);

}