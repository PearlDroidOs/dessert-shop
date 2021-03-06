package com.pearldroidos.dessertshop.model

import com.google.gson.annotations.SerializedName

/**
 * This is dessert information which is used to receive data in API service
 *
 * Created by Pearl DroidOs
 */
data class InformationModel (@SerializedName("id")
            val id:Int,
            @SerializedName("title")
            val name:String,
            @SerializedName("image")
            val image:String,
            @SerializedName("content")
            val description:String,
            @SerializedName("isNewProduct")
            val isNewProduct:Boolean
)