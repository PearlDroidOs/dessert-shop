package com.pearldroidos.dessertshop.model.tasks

import android.os.AsyncTask
import android.util.Log
import com.google.gson.reflect.TypeToken
import com.pearldroidos.dessertshop.callback.interactor.LoadInformationInteractor
import com.pearldroidos.dessertshop.model.InformationModel
import com.pearldroidos.dessertshop.model.enum.ErrorEnum
import com.pearldroidos.dessertshop.presenter.utils.JsonUtils
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

class LoadInformationTask(_interactor: LoadInformationInteractor) :
    AsyncTask<Int, String, String>() {
    private var listOfDessertInformation = ArrayList<InformationModel>()
    private val interactor = _interactor

    override fun doInBackground(vararg params: Int?): String {
        //Connect API
        try {
            val url = URL(ConfigurationAPI.BASE_URL)
            val urlConnect = url.openConnection() as HttpsURLConnection
            urlConnect.connectTimeout = 7000 //Wait 7 seconds for any results

            //PLEASE SEE LOG -> You will see code and message to check logic later
            //Example: 200 do something 400 or NotFound do another thing
            Log.d("Test", "urlConnect: $urlConnect")
            Log.d(
                "Test",
                "urlConnect res Code: ${urlConnect.responseCode} | res Mes: ${urlConnect.responseMessage} "
            )

            if (urlConnect.responseCode == 200) {
                val stringResults = convertStreamToString(urlConnect.inputStream)
                Log.d("Test", "string: $stringResults")
                publishProgress(stringResults)
            } else {
                publishProgress(ErrorEnum.INTERNET_ERROR.name)
            }
        } catch (unKnownHost: UnknownHostException) {
            //Dialog internet
            publishProgress(ErrorEnum.INTERNET_ERROR.name)
            unKnownHost.printStackTrace()
        } catch (ex: Exception) {
            //Please try again
            Log.d("Test", "${ex.message} | $ex")
            publishProgress(ErrorEnum.INTERNET_ERROR.name)
            ex.printStackTrace()
        }
        return ""
    }


    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)
        //Receive data
        //Transform
        when {
            values[0] == ErrorEnum.INTERNET_ERROR.name -> {
                Log.d("Test", "Error: ${ErrorEnum.INTERNET_ERROR.name} | ${values[0]}")
                interactor.error(ErrorEnum.INTERNET_ERROR)
            }
            values[0] == ErrorEnum.ERROR.name -> {
                interactor.error(ErrorEnum.ERROR)
            }
            else -> {
                val json = values[0]
                Log.d("Test", "JSON:  $json")

                val arrayListType = object : TypeToken<ArrayList<InformationModel>>() {}.type
                val listOfInfo = JsonUtils.covertJSONToArrayObject<ArrayList<InformationModel>>(
                    json!!,
                    arrayListType
                )

                Log.d("Test", "LIST:  $listOfInfo")
                this.listOfDessertInformation = listOfInfo
            }
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        //Send To presenter
        interactor.updateInformation(listOfDessertInformation)
        Log.d("Test", "Finish $result")
    }

    private fun convertStreamToString(inputStream: InputStream): String {
        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String?
        var AllString = ""

        try {
            do {
                line = bufferReader.readLine()
                if (line != null) {
                    AllString += line
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: Exception) {
            //Message
        }

        return AllString
    }

}