package com.example.android.trackmysleepquality.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDetailsBinding  = DataBindingUtil.
            setContentView(this, R.layout.activity_details)

        // get extra data
        val extraData = intent.extras?.getString("message")

        // set the data
        binding.textMessage.text = extraData

        // handle click
        binding.btnClose.setOnClickListener() {
            finish()
        }

    }
}