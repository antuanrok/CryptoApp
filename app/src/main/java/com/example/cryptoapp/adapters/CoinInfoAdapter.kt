package com.example.cryptoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.cryptoapp.R
import com.example.cryptoapp.pojo.CoinInfo
import com.example.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso
import java.text.Format
import java.text.SimpleDateFormat


class CoinInfoAdapter (private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {


    var coinInfoList: List<CoinPriceInfo> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener:OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        Picasso.get().load(coin.getFullImageUrl()).into(holder.ivLogoCoin)
        holder.tvSymbols.text =
            context.getString(R.string.symbols_template, coin.fromsymbol, coin.tosymbol)
        holder.tvPrice.text = String.format("%.2f", coin.price)
        holder.tvDataD.text = coin.getFormattedDate()
        holder.tvData.text = context.getString(R.string.data_name)
        holder.tvTimeT.text = coin.getFormattedTime()
        holder.tvTime.text = context.getString(R.string.time_name)

        holder.itemView.setOnClickListener{
           onCoinClickListener?.onCoinClick(coin)
        }
    }

    override fun getItemCount() = coinInfoList.size

    inner class CoinInfoViewHolder(itemView: View) : ViewHolder(itemView) {
        val ivLogoCoin: ImageView = itemView.findViewById(R.id.ivLogoCoin)
        val tvSymbols: TextView = itemView.findViewById(R.id.tvSymbols)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvDataD: TextView = itemView.findViewById(R.id.tvDataD)
        val tvTimeT: TextView = itemView.findViewById(R.id.tvTimeT)
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvData: TextView = itemView.findViewById(R.id.tvData)
    }

    interface  OnCoinClickListener{
        fun onCoinClick(coinPriceInfo: CoinPriceInfo)
    }
}