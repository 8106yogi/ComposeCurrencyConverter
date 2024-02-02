package com.test.paypaytest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.test.paypaytest.api.ApiService
import com.test.paypaytest.screens.MainScreen
import com.test.paypaytest.theme.PayPayTheme
import com.test.paypaytest.theme.R
import com.test.paypaytest.theme.components.AppTextFields
import com.test.paypaytest.theme.components.ThemePreview
import com.test.paypaytest.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var apiService: ApiService

    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        lifecycleScope.launch {
//            var response = apiService.getCurrencies("8df6a4a68c664aa59597436b5b738640")
//            Log.d(javaClass.canonicalName, "onCreate: $response")
//        }

        setContent {
            PayPayTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }

        lifecycleScope.launch {
            try {
                mainViewModel.currencies.collect{
                    Log.d("PayPay", "onCreate: $it")
                }
            } catch (exception: Exception) {
                Log.d("PAYPAY", "EXCEPTION $exception")

            }
        }
        lifecycleScope.launch {
            try {
                mainViewModel.latest.collect{
                    Log.d("PayPay", "onCreate: $it")
                }

            } catch (exception: Exception) {
                Log.d("PAYPAY", "EXCEPTION $exception")

            }
        }
    }






}