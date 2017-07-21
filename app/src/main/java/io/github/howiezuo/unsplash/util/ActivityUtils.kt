package io.github.howiezuo.unsplash.util

import android.support.annotation.IdRes
import android.support.annotation.NonNull
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


object ActivityUtils {

    fun addFragmentToActivity(@NonNull manager: FragmentManager, @NonNull fragment: Fragment, @IdRes layoutId: Int) {
        manager.beginTransaction().add(layoutId, fragment).commit()
    }

    fun addFragmentToActivity(@NonNull manager: FragmentManager, @NonNull fragment: Fragment, tag: String) {
        manager.beginTransaction().add(fragment, tag).commit()
    }

}
