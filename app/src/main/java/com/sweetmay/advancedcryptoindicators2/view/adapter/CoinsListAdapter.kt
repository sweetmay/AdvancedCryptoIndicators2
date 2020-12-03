package com.sweetmay.advancedcryptoindicators2.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sweetmay.advancedcryptoindicators2.R
import com.sweetmay.advancedcryptoindicators2.presenter.list.ICoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.view.custom.FavButton
import com.sweetmay.advancedcryptoindicators2.view.image.IImageLoader
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView
import kotlinx.android.extensions.LayoutContainer

class CoinsListAdapter(val presenter: ICoinsListPresenter, val imageLoader: IImageLoader<ImageView>)
    : RecyclerView.Adapter<CoinsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coin, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemPos = position
        holder.itemView.setOnClickListener {
            presenter.onItemClick(holder)

        }

        holder.imageButton.setOnClickListener {
            (it as FavButton).toggle()
            if(it.isChecked){
                it.setImageResource(R.drawable.favorite_24px_filled)
                presenter.saveFavCoin(holder)
            }else {
                it.setImageResource(R.drawable.favorite_border_24px)
                presenter.deleteFavCoin(holder)
            }
        }
        presenter.bindView(holder)
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }


    inner class ViewHolder(override val containerView: View)
        : RecyclerView.ViewHolder(containerView)
        , LayoutContainer
        , CoinItemView{
        val imageButton: FavButton = containerView.findViewById<FavButton>(R.id.fav_button)
        var itemPos = -1

        override fun setIcon(imgUrl: String) {
            imageLoader.loadImageIntoView(imgUrl, containerView.findViewById(R.id.coin_icon))
        }

        override fun setName(name: String) {
            containerView.findViewById<TextView>(R.id.coin_name).text = name
        }

        override fun setPrice(price: Float) {
            val tmp = "$ $price"
            containerView.findViewById<TextView>(R.id.coin_price).text = tmp
        }


        override fun getPos(): Int {
            return itemPos
        }

    }


}