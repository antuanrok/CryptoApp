package com.example.cryptoapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.cryptoapp.api.ApiFactory
import com.example.cryptoapp.database.AppDataBase
import com.example.cryptoapp.pojo.CoinPriceInfo
import com.example.cryptoapp.pojo.CoinPriceInfoRawData
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDataBase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val priceList = db.coinPriceInfoDao().getPriceList()

    init {
        loadData()
    }

    fun getDetailInfo(fsym: String): LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fsym)
    }

   private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo()
            .map { it.data?.map { it.coinInfo?.name }?.joinToString(",") }
            // .map {  ApiFactory.apiService.getFullPriceList(fsyms = it) }
            .flatMap { ApiFactory.apiService.getFullPriceList(fsyms = it) }
            .map { getPriceListFromRawData(it) }
            //.flatMap { getPriceListFromRawData(it) }
            .delaySubscription(10,TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            //.observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                  //  val a = getPriceListFromRawData(it)
                    db.coinPriceInfoDao().insertPriceList(it)
                    Log.d("Test", "Success:$it")
                    // Log.d("Test", b.toString())
                },
                {
                    Log.d("Test", "Error:$it")
                }
            )
        compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPriceInfo> {
        val listCoinPriceInfo =  ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return listCoinPriceInfo
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJsonObject = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJsonObject.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJsonObject.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                listCoinPriceInfo.add(priceInfo)
            }

        }
        return listCoinPriceInfo
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}