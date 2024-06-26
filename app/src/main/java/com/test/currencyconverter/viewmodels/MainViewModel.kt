package com.test.currencyconverter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.currencyconverter.models.Latest
import com.test.currencyconverter.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    val currencies: StateFlow<List<String>?>
        get() = repository.currencies

    val latest: StateFlow<Latest?>
        get() = repository.latest
    init {
        viewModelScope.launch {
            repository.getCurrencies()
            repository.getLatest()
        }
    }

}