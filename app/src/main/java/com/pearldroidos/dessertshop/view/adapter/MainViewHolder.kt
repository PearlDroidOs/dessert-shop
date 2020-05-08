package com.pearldroidos.dessertshop.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dessert_item.view.*

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    var name = itemView.tv_dessert_name
    var price = itemView.tv_price
    var image = itemView.iv_dessert
    var newProduct = itemView.tv_new
    private lateinit var clickListener: DessertItemClickListener

    init {
        itemView.setOnClickListener(this)
    }

    fun setOnClickListener(_clickListener: DessertItemClickListener) {
        this.clickListener = _clickListener
    }

    override fun onClick(v: View?) {
        clickListener.onItemClick(adapterPosition, v!!)
    }
}