package io.github.howiezuo.unsplash.feature.detail

import dagger.Component
import io.github.howiezuo.unsplash.app.ActivityScope


@ActivityScope
@Component(
        modules = arrayOf(
                DetailViewModelModule::class
        )
)
interface DetailComponent {

    fun inject(activity: DetailActivity)

}
