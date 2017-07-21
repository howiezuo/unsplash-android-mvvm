package io.github.howiezuo.unsplash.feature.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.howiezuo.unsplash.R
import io.github.howiezuo.unsplash.util.ActivityUtils
import javax.inject.Inject


class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setUpInjection()

        val fragment = findOrCreateViewFragment()
        fragment.detailViewModel = detailViewModel
    }

    override fun onDestroy() {
        detailViewModel?.destroy()

        super.onDestroy()
    }

    private fun setUpInjection() {
        DaggerDetailComponent.builder()
                .detailViewModelModule(DetailViewModelModule(this, PHOTO_VIEW_MODEL_TAG))
                .build()
                .inject(this)
    }

    private fun findOrCreateViewFragment(): DetailFragment {
        var fragment = supportFragmentManager.findFragmentById(R.id.content_container) as? DetailFragment
        if (fragment == null) {
            val photoId = intent.getStringExtra(EXTRA_PHOTO_ID)
            fragment = DetailFragment.newInstance(photoId)
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.content_container)
        }
        return fragment
    }

    companion object {
        const val EXTRA_PHOTO_ID = "PHOTO_ID"
        const val PHOTO_VIEW_MODEL_TAG = "PHOTO_VIEW_MODEL_TAG"
    }

}
