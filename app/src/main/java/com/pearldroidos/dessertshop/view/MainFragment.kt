package com.pearldroidos.dessertshop.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pearldroidos.dessertshop.R
import com.pearldroidos.dessertshop.callback.MainCallback
import com.pearldroidos.dessertshop.model.DessertModel
import com.pearldroidos.dessertshop.presenter.MainPresenter
import com.pearldroidos.dessertshop.view.adapter.MainAdapter
import kotlinx.android.synthetic.main.action_bar_customize.*
import kotlinx.android.synthetic.main.error_dialog.view.*
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * MainFragment presents a first page on dessert lists
 *
 * Handle every things about View
 *
 * Created by Pearl DroidOs
 */
class MainFragment : Fragment(), MainCallback, View.OnClickListener {

    private lateinit var presenter: MainPresenter
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Init presenter
        presenter = MainPresenter(this, context!!.applicationContext)

        //Init Views
        presenter.setActionBar()
        presenter.setView()
        presenter.initData()

        //Set on click listener
        btn_refresh.setOnClickListener(this)
    }

    //Initialize desserts by 'recyclerView' with 'GridLayoutManager'
    override fun initializeView(listDessert: ArrayList<DessertModel>) {
        viewManager = GridLayoutManager(activity, 2) // Set 2 row
        viewAdapter = MainAdapter(listDessert, context!!.applicationContext, presenter)
        rv_dessert.layoutManager = viewManager
        rv_dessert.adapter = viewAdapter
    }

    //Set customized action bar
    override fun setActionBar() {
        tv_bar_title.text = getString(R.string.product_title)
        ll_back.visibility = View.GONE
    }

    //Show progress bar
    override fun showProgressBar() {
        pb_load.visibility = View.VISIBLE
    }

    //Hide progress bar
    override fun hideProgressBar() {
        pb_load.visibility = View.GONE
    }

    //Show refresh view
    override fun showRefreshView() {
        btn_refresh.visibility = View.VISIBLE
    }

    //Hide refresh view
    override fun hideRefreshView() {
        btn_refresh.visibility = View.GONE
    }

    //Show dialog which sets message and image first
    override fun showDialog(message: String, image: Int) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK button - Can implement later to do something
                    })
            }

            // Set other dialog properties
            val view = layoutInflater.inflate(R.layout.error_dialog, null)
            view.tv_error_message.text = message
            view.iv_dialog.setImageResource(image)
            builder.setView(view)

            // Create the AlertDialog
            builder.create()
        }
        //Show dialog
        alertDialog!!.show()
    }

    //Listener click action
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_refresh -> {
                presenter.setView()
                presenter.initData()
            }
        }
    }

}
