package com.example.currency_converter.ui.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currency_converter.repository.CurrencyRepository

class ConverterActivityViewModelFactory(private val currencyRepository: CurrencyRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConverterActivityViewModel(
            currencyRepository
        ) as T
    }
}