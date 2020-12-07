package com.sweetmay.advancedcryptoindicators2.utils.image

import android.widget.ImageView

interface IImageLoader<V: ImageView> {
    fun loadImageIntoView(url: String, view: V)
}