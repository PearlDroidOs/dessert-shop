package com.pearldroidos.dessertshop.model

import android.graphics.Bitmap

data class DessertModel(
     val id:Int,
     val name:String,
     val image: Bitmap,
     val price:Double,
     val description:String,
     val isNewProduct:Boolean
 )
