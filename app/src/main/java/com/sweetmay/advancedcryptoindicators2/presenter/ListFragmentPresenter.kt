package com.sweetmay.advancedcryptoindicators2.presenter

import android.util.Log
import android.widget.ImageButton
import android.widget.ToggleButton
import androidx.navigation.NavController
import com.sweetmay.advancedcryptoindicators2.model.entity.coin.CoinBase
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.list.ICoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.view.CoinsListView
import com.sweetmay.advancedcryptoindicators2.view.custom.FavButton
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView
import com.sweetmay.advancedcryptoindicators2.view.ui.fragment.ListFragmentDirections
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class ListFragmentPresenter(val coinsRepo: ICoinsListRepo, val scheduler: Scheduler): MvpPresenter<CoinsListView>() {
    private val TAG: String = this::class.java.simpleName

    val coinsListPresenter = CoinsListPresenter()

    inner class CoinsListPresenter(): ICoinsListPresenter{

        val coins = arrayListOf<CoinBase>()

        override fun saveFavCoin(view: CoinItemView) {
            Log.d("a", "a")
        }

        override fun deleteFavCoin(view: CoinItemView) {
            TODO("Not yet implemented")
        }

        override fun onItemClick(view: CoinItemView) {
            viewState.selectCoin((coins[view.getPos()]))
        }

        override fun bindView(view: CoinItemView) {
            with(view){
                setIcon(coins[view.getPos()].image)
                setName(coins[view.getPos()].name)
                setPrice(coins[view.getPos()].current_price)
            }
        }

        override fun getCount(): Int {
            return coins.size
        }

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setTitle()
        viewState.initRv()
        loadData()
    }

    private fun loadData(currencyAgainst: String = CoinsListRepo.Currency.usd.toString()) {
        coinsRepo.getCoins(currencyAgainst).observeOn(scheduler).subscribe({list ->
            coinsListPresenter.coins.clear()
            coinsListPresenter.coins.addAll(list)
            viewState.updateList()
        }, {
            Log.d(TAG, it.message.toString())
        })
    }

}