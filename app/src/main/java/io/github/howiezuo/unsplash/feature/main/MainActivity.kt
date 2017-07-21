package io.github.howiezuo.unsplash.feature.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.howiezuo.unsplash.R
import io.github.howiezuo.unsplash.feature.detail.DetailActivity
import io.github.howiezuo.unsplash.util.ActivityUtils
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainListener {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpInjection()

        val fragment = findOrCreateViewFragment()
        fragment.mainViewModel = mainViewModel
    }

    override fun onDestroy() {
        mainViewModel.destroy()

        super.onDestroy()
    }

    private fun setUpInjection() {
        DaggerMainComponent.builder()
                .mainViewModelModule(MainViewModelModule(this, PHOTOS_VIEW_MODEL_TAG))
                .build()
                .inject(this)
    }

    private fun findOrCreateViewFragment(): MainFragment {
        var fragment = supportFragmentManager.findFragmentById(R.id.content_container) as? MainFragment
        if (fragment == null) {
            fragment = MainFragment.newInstance()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, fragment, R.id.content_container)
        }
        return fragment
    }

    override fun openPhotoDetail(photoId: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_PHOTO_ID, photoId)
        startActivity(intent)
    }

    companion object {
        const val PHOTOS_VIEW_MODEL_TAG = "PHOTOS_VIEW_MODEL_TAG"
    }

}
