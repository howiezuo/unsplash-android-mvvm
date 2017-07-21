package io.github.howiezuo.unsplash.api

import dagger.Component
import io.github.howiezuo.unsplash.feature.detail.DetailViewModel
import io.github.howiezuo.unsplash.feature.main.MainViewModel
import javax.inject.Singleton


@Singleton
@Component(
        modules = arrayOf(
                ApiModule::class
        )
)
interface ApiComponent {

    fun inject(vm: MainViewModel)

    fun inject(vm: DetailViewModel)

}
