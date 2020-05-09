package com.pearldroidos.dessertshop

import com.google.gson.reflect.TypeToken
import com.pearldroidos.dessertshop.mock.ServiceDataMock
import com.pearldroidos.dessertshop.model.InformationModel
import com.pearldroidos.dessertshop.presenter.utils.JsonUtils
import org.junit.Assert
import org.junit.Test


/**
 * JsonUtilTest is tested on sub function that it provided
 *
 * Created by Pearl DroidOs
 */
class JsonUtilsTest {

    @Test
    fun testJsonConverterWorks() {
        //Getting list Mock
        val original = ServiceDataMock.INFORMATION_LIST

        //Transform object To string
        val json: String = JsonUtils.covertObjectToJSON(original)

        //Check null json variable
        Assert.assertNotNull(json)

        //Getting referring list type
        val arrayListType = object : TypeToken<ArrayList<InformationModel>>() {}.type

        //Convert String json to list object which is InformationModel
        val converted = JsonUtils.covertJSONToArrayObject<ArrayList<InformationModel>>(json, arrayListType)

        //Check null converted variable
        Assert.assertNotNull(converted)

        //Checking converted and original variable is equal or not
        Assert.assertEquals(original, converted)
    }
}