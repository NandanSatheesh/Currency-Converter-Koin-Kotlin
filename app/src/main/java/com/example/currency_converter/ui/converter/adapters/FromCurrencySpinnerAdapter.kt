package com.example.currency_converter.ui.converter.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.currency_converter.R
import com.example.currency_converter.data.CurrencyData
import kotlinx.android.synthetic.main.view_spinner_layout.view.*


class FromCurrencySpinnerAdapter(
    context: Context,
    currencyList: List<CurrencyData>
) :
    ArrayAdapter<CurrencyData>(context, 0, currencyList) {

    override fun getView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    override fun getDropDownView(position: Int, recycledView: View?, parent: ViewGroup): View {
        return this.createView(position, recycledView, parent)
    }

    private fun createView(position: Int, recycledView: View?, parent: ViewGroup): View {

        val item = getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.view_spinner_layout,
            parent,
            false
        )

        Glide.with(context)
            .load(
                item?.flagImageUrl
            )
            .transform(CenterCrop())
            .into(view.imageDropDownMenuIcon)

        view.textDropDownLabel.text = item?.fromCurrencyName
        return view
    }
}