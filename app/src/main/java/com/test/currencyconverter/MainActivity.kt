package com.test.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.test.currencyconverter.api.ApiService
import com.test.currencyconverter.screens.MainScreen
import com.test.currencyconverter.theme.AppTheme
import com.test.currencyconverter.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var apiService: ApiService

    private val mainViewModel: MainViewModel by viewModels()

    private val list = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycleScope.launch {
//            var response = apiService.getCurrencies("8df6a4a68c664aa59597436b5b738640")
//            Log.d(javaClass.canonicalName, "onCreate: $response")
//        }

        setContent {
            AppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen(list)
                }
            }
        }

        lifecycleScope.launch {
            try {
                mainViewModel.currencies.collect{
                    Timber.d("PayPay", "onCreate: $it")
                    if (it != null) {
                        list.addAll(it)
                    }
                }
            } catch (exception: Exception) {
                Timber.d("PAYPAY", "EXCEPTION $exception")

            }
        }
        lifecycleScope.launch {
            try {
                mainViewModel.latest.collect{
                    Timber.d("PayPay", "onCreate: $it")
                }

            } catch (exception: Exception) {
                Timber.d("PAYPAY", "EXCEPTION $exception")

            }
        }
    }






}