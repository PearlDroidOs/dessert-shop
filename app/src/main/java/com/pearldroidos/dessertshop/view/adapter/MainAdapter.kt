package com.pearldroidos.dessertshop.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.pearldroidos.dessertshop.R
import com.pearldroidos.dessertshop.model.DessertModel
import com.pearldroidos.dessertshop.presenter.MainPresenter

class MainAdapter(_listOfDesserts: ArrayList<DessertModel>, context: Context, _presenter:MainPresenter) :
    RecyclerView.Adapter<MainViewHolder>(), DessertItemClickListener {
    private val presenter:MainPresenter = _presenter
    private val listOfDesserts = _listOfDesserts
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = layoutInflater.inflate(R.layout.dessert_item, parent, false)
        val viewHolder = MainViewHolder(view)
        viewHolder.setOnClickListener(this)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return listOfDesserts.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.name.text = listOfDesserts[position].name
        holder.price.text = listOfDesserts[position].price.toString()
        holder.image.setImageBitmap(listOfDesserts[position].image)
        holder.newProduct.visibility = if(listOfDesserts[position].isNewProduct) View.VISIBLE else View.INVISIBLE
    }

    override fun onItemClick(position: Int, view: View) {
        Log.d("Test", "Position $position")
        presenter.navigateToDessertDetail(listOfDesserts[position],view)
    }


}