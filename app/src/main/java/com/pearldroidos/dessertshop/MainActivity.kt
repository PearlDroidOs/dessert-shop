package com.pearldroidos.dessertshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


/**
 * MainActivity is only once
 *
 * Application is used by fragment
 *
 * Created by Pearl DroidOs
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}
