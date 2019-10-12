package com.example.currency_converter.network

import com.example.currency_converter.data.CurrencyData
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/")
    fun getCurrencyData(): Observable<CurrencyData>
}