package com.pearldroidos.dessertshop.presenter.utils

import com.google.gson.Gson
import java.lang.reflect.Type
import java.util.*

class JsonUtils {
    companion object {
        val gson:Gson by lazy { Gson() }
        fun <T> covertObjectToJSON(ob: T): String = gson.toJson(ob)
        fun <K> covertJSONToObject(jsonStr: String, clazz: Class<K>): K =  gson.fromJson(jsonStr, clazz)
        fun <E> covertJSONToArrayObject(jsonStr: String, type: Type): E =  gson.fromJson(jsonStr, type)
    }
}
