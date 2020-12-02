package com.sweetmay.advancedcryptoindicators2.presenter

import android.util.Log
import com.sweetmay.advancedcryptoindicators2.App
import com.sweetmay.advancedcryptoindicators2.model.entity.Coin
import com.sweetmay.advancedcryptoindicators2.model.repo.ICoinsListRepo
import com.sweetmay.advancedcryptoindicators2.model.repo.retrofit.CoinsListRepo
import com.sweetmay.advancedcryptoindicators2.presenter.list.ICoinsListPresenter
import com.sweetmay.advancedcryptoindicators2.view.CoinsListView
import com.sweetmay.advancedcryptoindicators2.view.item.CoinItemView
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class ListFragmentPresenter(val coinsRepo: ICoinsListRepo, val scheduler: Scheduler): MvpPresenter<CoinsListView>() {
    private val TAG: String = this::class.java.simpleName

    val coinsListPresenter = CoinsListPresenter()

    inner class CoinsListPresenter(): ICoinsListPresenter{

        val coins = arrayListOf<Coin>()

        override fun onItemClick(view: CoinItemView) {

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