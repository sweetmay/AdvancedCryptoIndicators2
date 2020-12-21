package com.sweetmay.advancedcryptoindicators2.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.databinding.ItemCoinBinding
import com.sweetmay.advancedcryptoindicators2.presenter.list.ICoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.utils.converter.Converter
import com.sweetmay.advancedcryptoindicators2.utils.image.IImageLoader
import com.sweetmay.advancedcryptoindicators2.view.custom.FavButton
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView

class CoinsListAdapter(val presenter: ICoinsListPresenter, val imageLoader: IImageLoader<ImageView>)
    : RecyclerView.Adapter<CoinsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemCoinBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemPos = position
        holder.itemView.setOnClickListener {
            presenter.onItemClick(holder)
        }

        holder.imageButton.setOnClickListener {
            it as FavButton
            it.checked = !it.checked
            if (it.checked) {
                presenter.saveFav(holder)
            } else presenter.deleteFav(holder)
        }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }


    inner class ViewHolder(private val itemCoinBinding: ItemCoinBinding)
        : RecyclerView.ViewHolder(itemCoinBinding.root), CoinItemView {
        val imageButton: FavButton = itemCoinBinding.favButton
        var itemPos = -1

        override fun setIcon(imgUrl: String) {
            imageLoader.loadImageIntoView(imgUrl, itemCoinBinding.coinIcon)
        }

        override fun setName(name: String) {
            itemCoinBinding.coinName.text = name
        }

        override fun setPrice(price: Float) {
            var tmp = ""
            if(price<1){
                tmp = "$" + String.format("%.8f", price)
            }else{
                tmp = "$$price"
            }
            itemCoinBinding.coinPrice.text = tmp
        }

        override fun setFavIcon(boolean: Boolean) {
            itemCoinBinding.favButton.checked = boolean
        }

        override fun setPriceChange(change: Converter.ConvertedChange) {
            with(itemCoinBinding.priceChange) {
                text = change.convertedPriceString
                itemCoinBinding.priceChange.setTextColor(change.color)
            }
        }


        override fun getPos(): Int {
            return itemPos
        }

    }


}