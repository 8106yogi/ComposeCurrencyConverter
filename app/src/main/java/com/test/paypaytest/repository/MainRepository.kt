package com.test.paypaytest.repository

import android.content.Context
import android.media.AudioTrack
import android.media.MediaParser
import android.util.Log
import com.google.gson.Gson
import com.test.paypaytest.api.ApiService
import com.test.paypaytest.models.Latest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val context: Context,
    private val apiService: ApiService
) {
    private val _currencies: MutableStateFlow<List<String>?> = MutableStateFlow(null)
    val currencies: StateFlow<List<String>?>
        get() = _currencies



    private val _latest: MutableStateFlow<Latest?> = MutableStateFlow(null)
    val latest: StateFlow<Latest?>
        get() = _latest




    suspend fun getCurrencies() {

        try {
//            val response = apiService.getCurrencies("8df6a4a68c664aa59597436b5b738640")
//            if (response.value?.isSuccessful == true && response.value?.body() != null) {
//                //    _currencies.emit(response.value?.body()!!)
//                _currencies.postValue(response.value)
//            }
            delay(1000)
            _currencies.emit(readCurrencyFromJson(context))

        } catch (e: Exception) {
            Log.d(javaClass.canonicalName, "getCurrencies: $e")
        }
    }


    suspend fun getLatest() {
        try {
//            val response = apiService.getCurrencies("8df6a4a68c664aa59597436b5b738640")
//            if (response.value?.isSuccessful == true && response.value?.body() != null) {
//                //    _currencies.emit(response.value?.body()!!)
//                _currencies.postValue(response.value)
//            }
            delay(1000)
            _latest.emit(readLatestFromJson(context))

        } catch (e: Exception) {
            Log.d(javaClass.canonicalName, "latest: $e")
        }
    }





    private fun readCurrencyFromJson(context: Context): List<String>? {
        return (Gson().fromJson(
            context.readTextFromAsset("currency.json"),
            Map::class.java
        ) as Map<String, String>?)?.keys?.toList()

    }

    private fun readLatestFromJson(context: Context): Latest? {
        return Gson().fromJson(
            context.readTextFromAsset("latest.json"),
            Latest::class.java
        )
    }

    private fun Context.readTextFromAsset(fileName: String): String {
        return assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    }

}