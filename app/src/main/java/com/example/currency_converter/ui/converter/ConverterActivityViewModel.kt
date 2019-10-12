package com.example.currency_converter.ui.converter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currency_converter.data.CurrencyData
import com.example.currency_converter.repository.CurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ConverterActivityViewModel(private val currencyRepository: CurrencyRepository) : ViewModel() {

    val dataFromServer: MutableLiveData<CurrencyData> = MutableLiveData()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun getCurrencyData() {

        compositeDisposable.add(
            currencyRepository.fetchCurrencyData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    dataFromServer.value = result
                }, { error ->
                    error.printStackTrace()
                })
        )
    }

    fun onDestroy() {
        compositeDisposable.clear()
    }
}