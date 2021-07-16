package com.sweetmay.advancedcryptoindicators2.presentation.adapter

import android.widget.ImageView
import com.sweetmay.advancedcryptoindicators2.presentation.callback.FavListCallBacks
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoader

class FavListAdapter(
  imageLoader: IImageLoader<ImageView>,
  converter: Converter,
  override val callBack: FavListCallBacks
) : BaseCoinListAdapter(imageLoader, converter, callBack) {

  override fun onBindViewHolder(holderCoin: CoinViewHolder, position: Int) {
    super.onBindViewHolder(holderCoin, position)
    holderCoin.imageButtonHolder.setOnClickListener {
      callBack.deleteFav(coins[position])
      coins.removeAt(position)
      callBack.notifyElementRemoved(position)
    }
  }
}