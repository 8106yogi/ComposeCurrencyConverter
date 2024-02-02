package com.test.paypaytest.api

import androidx.lifecycle.LiveData
import com.test.paypaytest.models.Currencies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //to get list of currencies
    //    currencies.json?app_id=8df6a4a68c664aa59597436b5b738640
    @GET("currencies.json")
     suspend fun getCurrencies(@Query("app_id") appId: String): LiveData<Response<Any>>
}