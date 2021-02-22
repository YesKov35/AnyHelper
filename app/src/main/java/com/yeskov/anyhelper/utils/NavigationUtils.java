package com.yeskov.anyhelper.utils;

import com.yeskov.anyhelper.R;

import androidx.navigation.NavOptions;

public class NavigationUtils {

    public static NavOptions getNavOptions() {
        return new NavOptions.Builder()
                .setEnterAnim(R.anim.slide_in_right)
                .setExitAnim(R.anim.slide_out_left)
                .setPopEnterAnim(R.anim.slide_in_left)
                .setPopExitAnim(R.anim.slide_out_right)
                .build();
    }
}
