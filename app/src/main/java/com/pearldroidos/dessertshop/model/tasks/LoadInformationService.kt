package com.pearldroidos.dessertshop.model.tasks

import android.os.AsyncTask
import com.google.gson.reflect.TypeToken
import com.pearldroidos.dessertshop.callback.interactor.LoadInformationListener
import com.pearldroidos.dessertshop.model.InformationModel
import com.pearldroidos.dessertshop.model.enum.ErrorEnum
import com.pearldroidos.dessertshop.presenter.utils.JsonUtils
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection


/**
 * LoadInformationService is a background service which work on responsibility
 *
 * Mainly, responsibility is load dessert information from api url (Business logic - Model)
 *
 * Created by Pearl DroidOs
 */
class LoadInformationService(private val listener: LoadInformationListener) :
    AsyncTask<Int, String, String>() {

    //A process which do in background
    override fun doInBackground(vararg params: Int?): String {
        //Connect API
        try {
            val url = URL(ConfigurationAPI.BASE_URL) //Use API URL by static class provided

            //Opn connection internet by url
            val urlConnect = url.openConnection() as HttpsURLConnection

            //Wait 7 seconds for any results | if not, doing something in the next step
            urlConnect.connectTimeout = 7000

            //Example: response code is 200 then do in covert stream to string
            //Response code is 400 or NotFound do error
            if (urlConnect.responseCode == 200) {
                return convertStreamToString(urlConnect.inputStream) // return string data
            } else {
                publishProgress(ErrorEnum.INTERNET_ERROR.name)
            }
        } catch (unKnownHost: UnknownHostException) {
            //Dialog internet error
            //return to 'onProgressUpdate' process to contact view
            publishProgress(ErrorEnum.INTERNET_ERROR.name)

            //Save stack
            unKnownHost.printStackTrace()
        } catch (ex: Exception) {
            //Dialog every error
            //return to 'onProgressUpdate' process to contact view
            publishProgress(ErrorEnum.INTERNET_ERROR.name)

            //Save stack
            ex.printStackTrace()
        }

        //Return empty string
        return ""
    }

    //During working on background and view
    override fun onProgressUpdate(vararg values: String?) {
        super.onProgressUpdate(*values)

        //Check condition error and return to presenter for contacting a view
        when {
            values[0] == ErrorEnum.INTERNET_ERROR.name -> {
                listener.error(ErrorEnum.INTERNET_ERROR)//Use listener to communicate with presenter
            }
            values[0] == ErrorEnum.ERROR.name -> {
                listener.error(ErrorEnum.ERROR)//Use listener to communicate with presenter
            }
        }
    }

    //Finished a process
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
           if(result != "") {
               if(result!!.startsWith('[')) {
                   //Information Array case
                   val arrayListType = object : TypeToken<ArrayList<InformationModel>>() {}.type

                   //Convert string to array list of InformationModel by GSON
                   val listOfInfo = JsonUtils.covertJSONToArrayObject<ArrayList<InformationModel>>(
                       result,
                       arrayListType
                   )

                   listener.updateInformation(listOfInfo) //Use listener to communicate with presenter
               }else{
                   //This is for an information set
                   //Developer can implement in the future
               }
           }else{
               //Do nothing - means no data
           }

    }

    //Convert input stream to String method
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
            } while (line != null) //If list == null | then a process to continue and close inputStream
            inputStream.close()
        } catch (ex: Exception) {
            //Message
            ex.printStackTrace()
        }

        return AllString
    }

}