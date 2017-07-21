package io.github.howiezuo.unsplash.feature.detail

import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import io.github.howiezuo.unsplash.app.ActivityScope
import io.github.howiezuo.unsplash.app.ViewModelHolder
import io.github.howiezuo.unsplash.util.ActivityUtils


@Module
class DetailViewModelModule(
        private val activity: AppCompatActivity,
        private val tag: String
) {

    @Provides
    @ActivityScope
    fun provideMainViewModel(): DetailViewModel {
        val holder = activity.supportFragmentManager.findFragmentByTag(tag) as? ViewModelHolder<DetailViewModel>
        if (holder != null && holder.viewModel != null) {
            return holder.viewModel!!
        } else {
            val vm = DetailViewModel()
            ActivityUtils.addFragmentToActivity(activity.supportFragmentManager, ViewModelHolder.createHolder(vm), tag)
            return vm
        }
    }

}
