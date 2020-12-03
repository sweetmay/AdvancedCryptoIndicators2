package com.sweetmay.advancedcryptoindicators2.view.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import io.reactivex.rxjava3.core.Single

interface IImageLoader<V: ImageView> {
    fun loadImageIntoView(url: String, view: V)
}