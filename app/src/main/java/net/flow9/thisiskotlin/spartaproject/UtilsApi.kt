package net.flow9.thisiskotlin.spartaproject

import android.content.Context
import android.util.Log
import net.flow9.thisiskotlin.spartaproject.Api.PREFS_NAME
import net.flow9.thisiskotlin.spartaproject.Api.PREF_KEY
//import net.flow9.thisiskotlin.spartaproject.Constants.PREFS_NAME
//import net.flow9.thisiskotlin.spartaproject.Constants.PREF_KEY

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date


object UtilsApi {
    fun getDateFromTimestampWithFormat(
        timestamp: String?,
        fromFormatformat: String?,
        toFormatformat: String?
    ): String {
        var date: Date? = null //Date? 부분 import가 Util
        var res = ""
        try {
            val format = SimpleDateFormat(fromFormatformat)
            date = format.parse(timestamp)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        Log.d("date", "getDataFromTimestampWithFormat date >> $date")

        val dateformat = SimpleDateFormat(toFormatformat)
        res = dateformat.format(date)
        return res
    }

    fun getLastSearch(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(PREF_KEY, null)
    }

    fun saveLastSearch(context: Context, query: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(PREF_KEY, query).apply()
    }
}


object Api {

    const val BASE_URL = "https://dapi.kakao.com"

    const val AUTH_HEADER = "KakaoAK ${BuildConfig.KAKAO_API_KEY}" // Api 키 숨김

    const val PREFS_NAME = "com.mwkim.imagesearch.prefs"

    const val PREF_KEY = "IMAGE_SEARCH_PREF"
}
