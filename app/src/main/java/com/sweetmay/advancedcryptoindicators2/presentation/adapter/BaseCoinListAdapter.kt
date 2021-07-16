package com.sweetmay.advancedcryptoindicators2.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.databinding.ItemCoinBinding
import com.sweetmay.advancedcryptoindicators2.domain.model.entity.crypto.base_coin.CoinView
import com.sweetmay.advancedcryptoindicators2.presentation.callback.BaseListCallBack
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoader

abstract class BaseCoinListAdapter(
  open val imageLoader: IImageLoader<ImageView>,
  open val converter: Converter,
  open val callBack: BaseListCallBack
) : RecyclerView.Adapter<CoinViewHolder>() {

  val coins = ArrayList<CoinView>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
    val itemBinding = ItemCoinBinding
      .inflate(LayoutInflater.from(parent.context), parent, false)
    return CoinViewHolder(itemBinding, imageLoader)
  }

  override fun onBindViewHolder(holderCoin: CoinViewHolder, position: Int) {
    with(holderCoin) {
      itemPos = position
      itemView.setOnClickListener {
        callBack.onItemClick(coins[holderCoin.getPos()])
      }
      setIcon(coins[position].image)
      setPrice(coins[position].current_price)
      setName(coins[position].name)
      setFavIcon(coins[position].is_favorite)
      setPriceChange(converter.convertChange(coins[position].price_change_percentage_24h))
    }
  }

  override fun getItemCount(): Int {
    return coins.size
  }

}