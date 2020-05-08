package com.pearldroidos.dessertshop.view

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.View.inflate
import androidx.fragment.app.Fragment
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.pearldroidos.dessertshop.R
import com.pearldroidos.dessertshop.callback.DessertDetailsCallBack
import com.pearldroidos.dessertshop.model.DessertModel
import com.pearldroidos.dessertshop.model.enum.GlobalEnum
import com.pearldroidos.dessertshop.presenter.DessertDetailsPresenter
import com.pearldroidos.dessertshop.presenter.utils.JsonUtils
import kotlinx.android.synthetic.main.action_bar_customize.*
import kotlinx.android.synthetic.main.fragment_dessert_details.*


/**
 * A simple [Fragment] subclass.
 * Use the [DessertDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DessertDetailsFragment : Fragment(), DessertDetailsCallBack, View.OnClickListener {

    private lateinit var presenter:DessertDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dessert_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = DessertDetailsPresenter(this, context!!.applicationContext)
        presenter.setActionBar()
        presenter.setView(arguments)

        ll_back.setOnClickListener(this)

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // in here you can do logic when backPress is clicked
                presenter.navigateToMainDesserts(view)
            }
        })
    }

    override fun initializeView(dessert: DessertModel, value:Int) {
        tv_new_detail.visibility = value
        tv_dessert_name_details.text = dessert.name
        tv_dessert_details.text = dessert.description
        tv_price_details.text =dessert.price.toString()
        iv_dessert_details.setImageBitmap(dessert.image)

    }

    override fun setActionBar(title: String, backButtonVisible: Int) {
        tv_bar_title.text = title
        ll_back.visibility = backButtonVisible
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.ll_back -> {presenter.navigateToMainDesserts(v)}
        }
    }


}
