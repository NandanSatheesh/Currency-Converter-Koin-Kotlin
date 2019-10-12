package com.example.currency_converter.ui.converter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.currency_converter.R
import com.example.currency_converter.data.CurrencyData
import com.example.currency_converter.ui.converter.adapters.FromCurrencySpinnerAdapter
import com.example.currency_converter.ui.converter.adapters.ToCurrencySpinnerAdapter
import com.example.currency_converter.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_converter_layout.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ConverterActivity : AppCompatActivity() {

    private val viewModel: ConverterActivityViewModel by inject { parametersOf(this) }
    private var conversionRate: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter_layout)

        var data: CurrencyData
        ViewUtils.setDisabled(fromCurrencyEditText, toCurrencyEditText)
        viewModel.getCurrencyData()

        viewModel.dataFromServer.observe(this, Observer {
            data = it
            initializeSpinners(data)
        })

        fromCurrencyEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrBlank()) {
                    val convertedAmount = p0.toString().toDouble() * conversionRate
                    toCurrencyEditText.setText(
                        String.format(
                            "%.3f", convertedAmount
                        )
                    )
                } else {
                    toCurrencyEditText.setText("")
                }
            }
        })


    }

    private fun initializeSpinners(data: CurrencyData) {

        ViewUtils.setEnabled(fromCurrencyEditText, toCurrencyEditText)
        fromCurrencySelectSpinner.adapter =
            FromCurrencySpinnerAdapter(
                this,
                listOf(data)
            )

        toCurrencySelectSpinner.adapter =
            ToCurrencySpinnerAdapter(
                this,
                data.supported
            )

        fromCurrencySelectSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                }
            }


        toCurrencySelectSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    conversionRate = data.supported.get(position).conversionRate
                }
            }

    }
}
