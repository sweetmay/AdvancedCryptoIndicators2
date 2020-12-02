package com.sweetmay.advancedcryptoindicators2.view.image

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader: IImageLoader<ImageView> {
    override fun loadImageIntoView(url: String, view: ImageView) {
        Glide.with(view.context).load(url).into(view)
    }
}