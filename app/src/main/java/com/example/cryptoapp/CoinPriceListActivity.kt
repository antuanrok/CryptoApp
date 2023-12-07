package com.example.cryptoapp

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.adapters.CoinInfoAdapter
import com.example.cryptoapp.pojo.CoinPriceInfo

//import rx.android.schedulers.AndroidSchedulers

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

     private  lateinit var rv: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object: CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                  //Log.d("Test", "Success in Click:${coinPriceInfo.fromsymbol}")
                  //val intent = Intent(this@CoinPriceListActivity,CoinDetailActivity::class.java)
                  //intent.putExtra(CoinDetailActivity.EXTRA_FROM_SYMBOL,coinPriceInfo.fromsymbol)
                  val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity,coinPriceInfo.fromsymbol)
                  startActivity(intent)
            }
        }
        rv = findViewById(R.id.rvCoinPriceList)
        rv.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
             //  Log.d("Test", "Success in Activity:$it")
               adapter.coinInfoList = it
             //  Log.d("Test", "Size:${adapter.coinInfoList.size}")
        })

    }

}