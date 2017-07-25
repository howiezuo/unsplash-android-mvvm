package io.github.howiezuo.unsplash.feature.main

import android.databinding.Observable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.howiezuo.unsplash.databinding.FragmentMainBinding
import io.github.howiezuo.unsplash.util.SnackbarUtils


class MainFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel

    private lateinit var mainBinding: FragmentMainBinding

    private var snackbarCallback: Observable.OnPropertyChangedCallback? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false)
        mainBinding.viewModel = mainViewModel
        return mainBinding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        snackbarCallback = object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                SnackbarUtils.showSnackbar(getView(), mainViewModel.errorText.get())
            }
        }
        mainViewModel.errorText.addOnPropertyChangedCallback(snackbarCallback)

        mainBinding.srLayout.setOnRefreshListener {
            mainViewModel.refresh()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainBinding.rvPhotos.layoutManager = LinearLayoutManager(context)
        mainBinding.rvPhotos.adapter = MainAdapter(activity as MainListener)

        mainViewModel.create()
    }

    override fun onDestroy() {
        if (snackbarCallback != null) {
            mainViewModel.errorText.removeOnPropertyChangedCallback(snackbarCallback)
        }
        super.onDestroy()
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

}
