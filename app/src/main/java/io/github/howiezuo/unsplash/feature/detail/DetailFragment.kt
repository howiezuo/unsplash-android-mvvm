package io.github.howiezuo.unsplash.feature.detail

import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.howiezuo.unsplash.databinding.FragmentDetailBinding
import io.github.howiezuo.unsplash.util.SnackbarUtils


class DetailFragment : Fragment() {

    lateinit var detailViewModel: DetailViewModel
    private lateinit var detailBinding: FragmentDetailBinding

    private var snackbarCallback: Observable.OnPropertyChangedCallback? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        detailBinding.viewModel = detailViewModel
        return detailBinding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackbarCallback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                SnackbarUtils.showSnackbar(view, detailViewModel.errorText.get())
            }
        }
        detailViewModel.errorText.addOnPropertyChangedCallback(snackbarCallback)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        detailViewModel.id = arguments.getString(ARG_PHOTO_ID)
        detailViewModel.create()
    }

    override fun onDestroy() {
        if (snackbarCallback != null) {
            detailViewModel.errorText.removeOnPropertyChangedCallback(snackbarCallback)
        }
        super.onDestroy()
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
