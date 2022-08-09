package com.test.jsondatasource

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

class Util {

    fun loadJSONFromAsset(context: Context): String {
        val json: String?
        try {
            val inputStream = context.assets.open("jsonformatter.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
//        Log.d("json", json)
        return json
    }
}