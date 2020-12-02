package com.sweetmay.advancedcryptoindicators2.view.image

import android.view.View

interface IImageLoader<V: View> {
    fun loadImageIntoView(url: String, view: V)
}