package io.github.howiezuo.unsplash.util;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager manager, @NonNull Fragment fragment, @IdRes int layoutId) {
        manager.beginTransaction()
                .add(layoutId, fragment)
                .commit();
    }

    public static void addFragmentToActivity(@NonNull FragmentManager manager, @NonNull Fragment fragment, String tag) {
        manager.beginTransaction()
                .add(fragment, tag)
                .commit();
    }

}
