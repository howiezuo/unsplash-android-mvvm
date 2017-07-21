package io.github.howiezuo.unsplash.feature.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.howiezuo.unsplash.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    lateinit var detailViewModel: DetailViewModel
    private lateinit var detailBinding: FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        detailBinding.viewModel = detailViewModel
        return detailBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        detailViewModel.id = arguments.getString(ARG_PHOTO_ID)
        detailViewModel.create()
    }

    companion object {
        const val ARG_PHOTO_ID = "PHOTO_ID"

        fun newInstance(photoId: String): DetailFragment {
            val args = Bundle()
            args.putString(ARG_PHOTO_ID, photoId)
            val fragment = DetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
