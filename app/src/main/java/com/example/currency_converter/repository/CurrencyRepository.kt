package com.example.currency_converter.repository

import com.example.currency_converter.data.CurrencyData
import com.example.currency_converter.network.ApiService
import io.reactivex.Observable

class CurrencyRepository(private val apiService: ApiService) {

    fun fetchCurrencyData(): Observable<CurrencyData> {
        return apiService.getCurrencyData()
    }
}