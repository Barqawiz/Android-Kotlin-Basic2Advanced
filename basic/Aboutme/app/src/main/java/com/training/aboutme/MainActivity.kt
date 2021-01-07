package com.training.aboutme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.training.aboutme.databinding.ActivityMainBinding
import com.training.aboutme.model.MyName

/**
 * The big idea about data binding is to create an object that connects/maps/binds two pieces
 * of distant information together at compile time, so that you don't have to look for it at runtime.
 */
class MainActivity : AppCompatActivity() {

    /* ActivityMainBinding auto generated by the compiler and the name derived from activity_main.xml */
    private lateinit var binding: ActivityMainBinding
    /*update the binding data*/
    private lateinit var myName:MyName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // initial name data
        myName = MyName(getString(R.string.default_name), getString(R.string.greeting))

        // bind the layout with the data object
        binding.myName = myName

        //set action
        binding.btnUpdateName.setOnClickListener{
            updateNickname()
        }

    }

    private fun updateNickname() {

        binding.apply {
            //To allow nulls, we can declare a variable as nullable string, written String?:
            // Safe operation ? before . returns null if any of the properties in it is null.
            myName?.greeting = getString(R.string.greeting).replace("Alex", editNickname.text.toString())
            // refresh the UI with the new binding
            invalidateAll()
            // clean the edit text
            editNickname.setText("")
        }

    }
}