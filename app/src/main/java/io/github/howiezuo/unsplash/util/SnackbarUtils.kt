package io.github.howiezuo.unsplash.util

import android.support.design.widget.Snackbar
import android.view.View


object SnackbarUtils {

    fun showSnackbar(v: View?, text: String?) {
        if (v == null || text == null) return

        Snackbar.make(v, text, Snackbar.LENGTH_LONG).show()
    }

}