package com.example.currency_converter.data


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class CurrencyData(

    @SerializedName("fromCountry") val fromCountry: String,
    @SerializedName("fromCurrencyName") val fromCurrencyName: String,
    @SerializedName("flagImageUrl") val flagImageUrl: String,
    @SerializedName("supported") val supported: List<SupportedConversions>
)

@Serializable
data class SupportedConversions(

    @SerializedName("toCountry") val toCountry: String,
    @SerializedName("toCurrencyName") val toCurrencyName: String,
    @SerializedName("flagImageUrl") val flagImageUrl: String,
    @SerializedName("conversionRate") val conversionRate: Double
)