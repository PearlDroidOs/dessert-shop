package com.pearldroidos.dessertshop.view

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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
 * A simple [Fragment] subclass.
 *
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), MainCallback, View.OnClickListener {

    private lateinit var presenter: MainPresenter
    private lateinit var recyclerView: RecyclerView
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
        viewManager = GridLayoutManager(activity, 2)
        presenter = MainPresenter(this, context!!.applicationContext)
        presenter.setActionBar()
        presenter.setView()

        btn_refresh.setOnClickListener(this)

    }


    override fun initializeView(listDessert: ArrayList<DessertModel>) {
        viewAdapter = MainAdapter(listDessert, context!!.applicationContext, presenter)
        rv_dessert.layoutManager = viewManager
        rv_dessert.adapter = viewAdapter
    }

    override fun setActionBar(title: String, backButtonVisible: Int) {
        tv_bar_title.text = title
        ll_back.visibility = backButtonVisible
    }


    override fun showDialog(message: String, image: Int) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK button
                    })
            }
            // Set other dialog properties
            val view = layoutInflater.inflate(R.layout.error_dialog, null)
            view.tv_error_message.text = message
            view.iv_dialog.setImageResource(image)
            builder.setView(view)

            // Create the AlertDialog
            Log.d("Test", "call dialog")
            builder.create()
        }
        alertDialog!!.show()
    }

    override fun setProgressBar(value: Int) {
        pb_load.visibility = value
    }

    override fun showRefresh(value: Int) {
        btn_refresh.visibility = value
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_refresh -> {
                presenter.setView()
            }
        }
    }

}
