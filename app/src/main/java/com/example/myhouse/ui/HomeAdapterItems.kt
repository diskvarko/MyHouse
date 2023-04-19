package com.example.myhouse.ui

import androidx.annotation.StringRes
import com.example.myhouse.R

enum class HomeAdapterItems(
    val itemNum: Int,
    @StringRes val title: Int
) {
    CAMERAS(0, R.string.cameras_fragment_title),
    DOORS(1, R.string.doors_fragment_title)
}
