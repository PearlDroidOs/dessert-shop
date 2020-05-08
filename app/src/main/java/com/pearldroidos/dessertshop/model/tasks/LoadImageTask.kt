package com.pearldroidos.dessertshop.model.tasks

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import com.pearldroidos.dessertshop.R
import com.pearldroidos.dessertshop.callback.interactor.LoadImageInteractor
import java.io.InputStream
import java.net.URL
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection


class LoadImageTask(_interactor: LoadImageInteractor, _context: Context) :
    AsyncTask<ArrayList<String>, Void, ArrayList<Bitmap>>() {
    private val interactor = _interactor
    private val context = _context


    override fun doInBackground(vararg params: ArrayList<String>?): ArrayList<Bitmap> {
        val listOfBitmap = ArrayList<Bitmap>()
        for (url in params[0]!!) {
            var bmp: Bitmap?
            try {
                val urlConnect = URL(url).openConnection() as HttpsURLConnection
                Log.d("Test", "URL : ${urlConnect.responseCode}")

                if (urlConnect.responseCode == 200) {
                    val urlStream: InputStream = urlConnect.inputStream
                    bmp = BitmapFactory.decodeStream(urlStream)

                    Log.d("Test", "Bitmap: $bmp ")
                    listOfBitmap.add(bmp)
                } else {
                    //Do something
                    Log.d("Test", "Error : ${urlConnect.responseCode}")
                    listOfBitmap.add(
                        BitmapFactory.decodeResource(
                            context.resources,
                            R.mipmap.image_background
                        )
                    )
                }
            } catch (unKnownHost: UnknownHostException) {
                //Dialog internet
                unKnownHost.printStackTrace()
            } catch (ex: Exception) {
                //Please try again
                Log.d("Test", "${ex.message} | $ex")
                ex.printStackTrace()
            }

        }
        Log.d("Test", "ListBitMap : $listOfBitmap")
        return listOfBitmap

    }

    override fun onPostExecute(result: ArrayList<Bitmap>?) {
        super.onPostExecute(result)
        interactor.updateImageData(result!!)
    }


}