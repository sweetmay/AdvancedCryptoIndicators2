package com.sweetmay.advancedcryptoindicators2.presentation.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.presentation.callback.MainListCallBacks
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoader
import com.sweetmay.advancedcryptoindicators2.presentation.view.custom.FavButton

class MainListAdapter(
  imageLoader: IImageLoader<ImageView>,
  converter: Converter,
  override val callBack: MainListCallBacks
) :
  BaseCoinListAdapter(imageLoader, converter, callBack) {

  override fun onBindViewHolder(holderCoin: CoinViewHolder, position: Int) {
    super.onBindViewHolder(holderCoin, position)
    holderCoin.imageButtonHolder.setOnClickListener {
      val button = it.findViewById<FavButton>(R.id.fav_button)
      button.checked = !button.checked
      if (button.checked) {
        callBack.saveFav(coins[position])
      } else callBack.deleteFav(coins[position])
    }
  }

  override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
    super.onAttachedToRecyclerView(recyclerView)

    callBack.scrollToLastPos()

    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = (recyclerView.layoutManager as LinearLayoutManager)
        callBack.updatePagingState(
          layoutManager.findFirstVisibleItemPosition(),
          layoutManager.findLastVisibleItemPosition()
        )
      }
    })
  }
}