//package com.sweetmay.advancedcryptoindicators2.di.modules
//
//import android.widget.ImageView
//import com.sweetmay.advancedcryptoindicators2.App
//import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoader
//import com.sweetmay.advancedcryptoindicators2.utils.image.GlideImageLoaderAsDrawable
//import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoader
//import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoaderAsDrawable
//import dagger.Module
//import dagger.Provides
//
//@Module
//class ImageLoaderModule {
//
//    @Provides
//    fun imageLoaderAsDrawable(app: App): IImageLoaderAsDrawable {
//        return GlideImageLoaderAsDrawable(app.applicationContext)
//    }
//
//    @Provides
//    fun imageLoader(): IImageLoader<ImageView>{
//        return GlideImageLoader()
//    }
//}