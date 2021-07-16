package com.sweetmay.advancedcryptoindicators2.presentation.adapter

import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.databinding.ItemCoinBinding
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoader

open class CoinViewHolder(
  val itemCoinBinding: ItemCoinBinding,
  private val imageLoader: IImageLoader<ImageView>
) : RecyclerView.ViewHolder(itemCoinBinding.root) {
  val imageButtonHolder: FrameLayout = itemCoinBinding.favHolderLayout
  var itemPos = -1

  fun setIcon(imgUrl: String) {
    imageLoader.loadImageIntoView(imgUrl, itemCoinBinding.coinIcon)
  }

  fun setName(name: String) {
    itemCoinBinding.coinName.text = name
  }

  fun setPrice(price: Double) {
    val tmp: String = if (price < 1) {
      "$" + String.format("%.8f", price)
    } else {
      "$$price"
    }
    itemCoinBinding.coinPrice.text = tmp
  }

  fun setFavIcon(boolean: Boolean) {
    itemCoinBinding.favButton.checked = boolean
  }

  fun setPriceChange(change: Converter.ConvertedChange) {
    with(itemCoinBinding.priceChange) {
      text = change.convertedPriceString
      itemCoinBinding.priceChange.setTextColor(change.color)
    }
  }

  fun getPos(): Int {
    return itemPos
  }
}