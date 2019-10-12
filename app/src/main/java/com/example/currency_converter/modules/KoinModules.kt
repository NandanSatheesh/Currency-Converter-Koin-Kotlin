package com.example.currency_converter.modules

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.currency_converter.BuildConfig
import com.example.currency_converter.network.ApiService
import com.example.currency_converter.repository.CurrencyRepository
import com.example.currency_converter.ui.converter.ConverterActivityViewModel
import com.example.currency_converter.ui.converter.ConverterActivityViewModelFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<Retrofit>(createdAtStart = true) {
        val loggingIntercepter = HttpLoggingInterceptor()
        loggingIntercepter.level = HttpLoggingInterceptor.Level.BODY
        val okHttp = OkHttpClient.Builder().addInterceptor(loggingIntercepter).build()
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttp)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService> {
        (get() as Retrofit).create(ApiService::class.java)
    }

    single<CurrencyRepository>(named(CURRENCY_REPOSITORY)) { CurrencyRepository(get()) }

    factory<ConverterActivityViewModel> { (fragmentActivity: FragmentActivity) ->
        ViewModelProviders.of(
            fragmentActivity,
            ConverterActivityViewModelFactory(
                get(
                    named(
                        (CURRENCY_REPOSITORY)
                    )
                )
            )
        ).get(ConverterActivityViewModel::class.java)
    }
}


private const val CURRENCY_REPOSITORY = "currencyRepository";