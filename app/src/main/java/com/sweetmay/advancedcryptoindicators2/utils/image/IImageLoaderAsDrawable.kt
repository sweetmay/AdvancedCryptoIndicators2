package com.sweetmay.advancedcryptoindicators2.utils.image

import android.graphics.drawable.Drawable
import io.reactivex.rxjava3.core.Single

interface IImageLoaderAsDrawable {
    fun loadImageAsDrawable(url: String): Single<Drawable>
}