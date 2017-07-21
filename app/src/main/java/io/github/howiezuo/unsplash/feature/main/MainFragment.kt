package io.github.howiezuo.unsplash.feature.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.howiezuo.unsplash.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    lateinit var mainViewModel: MainViewModel

    private lateinit var mainBinding: FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainBinding = FragmentMainBinding.inflate(inflater, container, false)
        mainBinding.viewModel = mainViewModel
        return mainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainBinding.rvPhotos.layoutManager = LinearLayoutManager(context)
        mainBinding.rvPhotos.adapter = MainAdapter(activity as MainListener)

        mainViewModel.create()
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

}
