package ro.bartis.ghiduriPrimAjutor

import android.content.Context

public fun loadJsonFromAsset(filename: String, context: Context): String? {
    var json: String? = null

    try {
        val inputStream = context.assets.open(filename)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charsets.UTF_8)
    } catch (ex: java.io.IOException) {
        ex.printStackTrace()
        return null
    }

    return json
}