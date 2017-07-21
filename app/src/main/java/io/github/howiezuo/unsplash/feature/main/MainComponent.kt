package io.github.howiezuo.unsplash.feature.main

import dagger.Component
import io.github.howiezuo.unsplash.app.ActivityScope


@ActivityScope
@Component(
        modules = arrayOf(
                MainViewModelModule::class
        )
)
interface MainComponent {

    fun inject(activity: MainActivity)

}
