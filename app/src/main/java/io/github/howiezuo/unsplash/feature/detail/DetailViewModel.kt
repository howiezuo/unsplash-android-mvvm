package io.github.howiezuo.unsplash.feature.detail

import android.databinding.BaseObservable
import android.databinding.ObservableField
import io.github.howiezuo.unsplash.api.DaggerApiComponent
import io.github.howiezuo.unsplash.api.PhotosService
import io.github.howiezuo.unsplash.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class DetailViewModel : BaseObservable() {

    lateinit var id: String

    val photo = ObservableField<Photo>()

    val errorText = ObservableField<String>();

    @Inject
    lateinit var photosService: PhotosService

    private var disposable: Disposable? = null

    init {
        DaggerApiComponent.builder().build().inject(this)
    }

    private fun fetchPhoto() {
        disposable = photosService.getPhoto(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t ->
                    photo.set(t)
                }, { t ->
                    errorText.set(t.localizedMessage)
                })
    }

    fun create() {
        fetchPhoto()
    }

    fun destroy() {
        disposable?.dispose()
    }
}
