package io.github.howiezuo.unsplash.feature.detail

import android.databinding.BaseObservable
import android.databinding.ObservableField
import io.github.howiezuo.unsplash.api.DaggerApiComponent
import io.github.howiezuo.unsplash.api.PhotosService
import io.github.howiezuo.unsplash.model.Photo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import javax.inject.Inject


class DetailViewModel : BaseObservable() {

    val photo = ObservableField<Photo>()
    val errorText = ObservableField<String>()

    var id: String? = null
    var realm: Realm? = null

    @Inject
    lateinit var photosService: PhotosService

    private var disposable: Disposable? = null

    init {
        DaggerApiComponent.builder().build().inject(this)
    }

    private fun fetchPhoto() {
        disposable = id?.let {
            photosService.getPhoto(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ t ->
                    photo.set(t)
                    saveToLocal(t)
                }, { t ->
                    errorText.set(t.localizedMessage)
                })
        }
    }

    private fun fetchFromLocal(): io.github.howiezuo.unsplash.model.entity.Photo? {
        realm?.let {
            return it.where(io.github.howiezuo.unsplash.model.entity.Photo::class.java).equalTo("id", id).findFirst()
        }
        return null
    }

    private fun saveToLocal(photo: Photo) {
        realm?.let {
            it.executeTransaction({
                var p = fetchFromLocal()
                if (p == null) {
                    it.createObject(io.github.howiezuo.unsplash.model.entity.Photo::class.java, photo.id)
                }
            })
            it.close()
        }
    }

    fun create() {
        realm = Realm.getDefaultInstance()
        fetchPhoto()
    }

    fun destroy() {
        realm?.close()
        disposable?.dispose()
    }
}
