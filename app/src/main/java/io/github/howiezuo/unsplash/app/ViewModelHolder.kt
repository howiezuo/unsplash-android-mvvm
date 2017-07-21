package io.github.howiezuo.unsplash.app

import android.os.Bundle
import android.support.v4.app.Fragment


class ViewModelHolder<VM> : Fragment() {

    var viewModel: VM? = null
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
    }

    companion object {
        fun <M> createHolder(vm: M): ViewModelHolder<M> {
            val holder = ViewModelHolder<M>()
            holder.viewModel = vm
            return holder
        }
    }

}
