package com.example.currency_converter.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.currency_converter.R
import com.example.currency_converter.databinding.ActivityMainBinding
import com.example.currency_converter.ui.converter.ConverterActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.buttonGo.setOnClickListener {
            goToNextActivity()
        }
    }

    fun goToNextActivity() {
        val intent = Intent(this, ConverterActivity::class.java)
        startActivity(intent)
        this.finish()
    }
}
