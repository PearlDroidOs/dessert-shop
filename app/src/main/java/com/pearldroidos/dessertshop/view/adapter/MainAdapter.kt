package com.pearldroidos.dessertshop.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pearldroidos.dessertshop.R
import com.pearldroidos.dessertshop.model.DessertModel
import com.pearldroidos.dessertshop.presenter.MainPresenter

/**
 * MainAdapter will handle item view, so which works with view holder
 *
 * Created by Pearl DroidOs
 */
class MainAdapter( private val listOfDesserts: ArrayList<DessertModel>, context: Context,
                   private val presenter:MainPresenter) :
    RecyclerView.Adapter<MainViewHolder>(), DessertItemClickListener {
    //Immutable variable of layoutInflater
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)


    //Config view and contact to work with view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = layoutInflater.inflate(R.layout.dessert_item, parent, false)
        val viewHolder = MainViewHolder(view)
        viewHolder.setOnClickListener(this)
        return viewHolder
    }

    //Item counting
    override fun getItemCount(): Int {
        return listOfDesserts.size
    }

    //Set each item
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.name.text = listOfDesserts[position].name
        holder.price.text = listOfDesserts[position].price.toString()
        holder.image.setImageBitmap(listOfDesserts[position].image)
        holder.newProduct.visibility = if(listOfDesserts[position].isNewProduct) View.VISIBLE else View.INVISIBLE
    }

    //Click Action of each item
    override fun onItemClick(position: Int, view: View) {
        presenter.navigateToDessertDetail(listOfDesserts[position],view)
    }


}