package com.sweetmay.advancedcryptoindicators2.view.image

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GlideImageLoaderAsDrawable(val context: Context): IImageLoaderAsDrawable {

    override fun loadImageAsDrawable(url: String): Single<Drawable> {
        return Single.create<Drawable>{
            Glide.with(context).asDrawable().load(url).into<CustomTarget<Drawable>>(object : CustomTarget<Drawable>(){
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    it.onSuccess(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })
        }.subscribeOn(Schedulers.io())
    }
}