package com.training.minipaint

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setContentView(R.layout.activity_main)
        val myCanvasView = MyCanvasView(context = this)
        // set configurations of the views
        myCanvasView.contentDescription = getString(R.string.canvasContentDescription)
        // set full screen
        @Suppress("DEPRECATION")
        myCanvasView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // bind the view
        setContentView(myCanvasView)

    }
}