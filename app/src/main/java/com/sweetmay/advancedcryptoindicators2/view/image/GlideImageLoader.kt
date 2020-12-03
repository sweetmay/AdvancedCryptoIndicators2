package com.sweetmay.advancedcryptoindicators2.view.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GlideImageLoader: IImageLoader<ImageView>{
    override fun loadImageIntoView(url: String, view: ImageView) {
        Glide.with(view.context).load(url).into(view)
    }
}