package com.pearldroidos.dessertshop.model.enum


/**
 * ErrorEnum describes about error type
 *
 * Created by Pearl DroidOs
 */
enum class ErrorEnum (val message:String){
    //INTERNET_ERROR is about internet
    INTERNET_ERROR("Please check your internet !!!"),
    //ERROR is about another error without about internet
    ERROR("Please try again.")
}