package com.example.cryptoapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var tvprice: TextView
    private lateinit var tvmin: TextView
    private lateinit var tvmax: TextView
    private lateinit var tvwhere: TextView
    private lateinit var tvwhen: TextView
    private lateinit var tvfsymb: TextView
    private lateinit var tvtsymb: TextView
    private lateinit var ivlogo: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromsymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        var fsym: String = ""

        if (fromsymbol == null) {
            finish()
            return
        } else {
            fsym = fromsymbol
        }


        tvprice = findViewById(R.id.tvPriceInfo)
        tvmin = findViewById(R.id.tvMinInfo)
        tvmax = findViewById(R.id.tvMaxInfo)
        tvwhere = findViewById(R.id.tvWhereInfo)
        tvwhen = findViewById(R.id.tvWhenInfo)
        tvfsymb = findViewById(R.id.tvSymb1)
        tvtsymb = findViewById(R.id.tvSymb3)
        ivlogo = findViewById(R.id.ivLogoDetail)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fsym).observe(this, Observer {
            //Log.d("Test", "Success in Detail:${it.toString()}")
            tvfsymb.text = it.fromsymbol
            tvtsymb.text = it.tosymbol
            tvprice.text = String.format("%.2f", it.price)
            tvmin.text = String.format("%.2f", it.lowday)
            tvmax.text = String.format("%.2f", it.highday)
            tvwhere.text = it.lastmarket
            tvwhen.text = it.getFormattedTime()
            Picasso.get().load(it.getFullImageUrl()).into(this.ivlogo)
            //adapter.coinInfoList = it
            //  Log.d("Test", "Size:${adapter.coinInfoList.size}")
        })
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fsym"

        fun newIntent(context: Context, fromsymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromsymbol)
            return intent
        }
    }
}