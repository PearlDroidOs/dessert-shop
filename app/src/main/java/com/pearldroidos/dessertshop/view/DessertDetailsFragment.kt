package com.pearldroidos.dessertshop.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.pearldroidos.dessertshop.R
import com.pearldroidos.dessertshop.callback.DessertDetailsCallBack
import com.pearldroidos.dessertshop.model.DessertModel
import com.pearldroidos.dessertshop.presenter.DessertDetailsPresenter
import kotlinx.android.synthetic.main.action_bar_customize.*
import kotlinx.android.synthetic.main.fragment_dessert_details.*


/**
 * DessertDetailsFragment presents a dessert details' view
 *
 * Handle every things about View
 *
 * Created by Pearl DroidOs
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

        //Init Present
        presenter = DessertDetailsPresenter(this)

        //Init Views
        presenter.setActionBar()
        presenter.getData(arguments)
        presenter.setView()

        //Set on click listener
        ll_back.setOnClickListener(this)

        //handle back pressed on dispatch
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // in here you can do logic when backPress is clicked
                presenter.navigateToMainDesserts(view)
            }
        })
    }

    //Initialize dessert detail
    override fun initializeView(dessert: DessertModel, value:Int) {
        tv_new_detail.visibility = value
        tv_dessert_name_details.text = dessert.name
        tv_dessert_details.text = dessert.description
        tv_price_details.text =dessert.price.toString()
        iv_dessert_details.setImageBitmap(dessert.image)

    }

    //Set customized action bar
    override fun setActionBar() {
        tv_bar_title.text = getString(R.string.detail_title)
        ll_back.visibility = View.VISIBLE
    }

    //Listener click action
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.ll_back -> {presenter.navigateToMainDesserts(v)}
        }
    }


}
