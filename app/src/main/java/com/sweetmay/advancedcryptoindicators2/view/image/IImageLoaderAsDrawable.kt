package com.sweetmay.advancedcryptoindicators2.view.image

import android.content.Context
import android.graphics.drawable.Drawable
import io.reactivex.rxjava3.core.Single

interface IImageLoaderAsDrawable {
    fun loadImageAsDrawable(url: String): Single<Drawable>
}