package com.training.colormyviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.training.colormyviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // click action
        setListeners()
    } /*end function on-create*/

    private fun setListeners() {
        binding.apply {
            val clickableViews: List<View> = listOf(textBoxOne, textBoxTwo, textBoxThree,
                    textBoxFour, textBoxFive, layoutConstraint, buttonRed, buttonOrange, buttonGreen)

            for (item in clickableViews) {
                item.setOnClickListener{ colorMe(it) }
            }
        } /*end: binding*/

    } /*end function listener*/

    private fun colorMe(view: View) {
        when(view.id) {
            R.id.text_box_one -> view.setBackgroundColor(ContextCompat.getColor(this, R.color.c_box1))
            R.id.text_box_two -> view.setBackgroundResource(R.color.c_box2)
            R.id.text_box_three -> view.setBackgroundResource(R.color.c_box3)
            R.id.text_box_four -> view.setBackgroundResource(R.color.c_box4)
            R.id.text_box_five -> view.setBackgroundResource(R.color.c_box5)

            R.id.button_red -> binding.textBoxThree.setBackgroundResource(android.R.color.holo_red_light)
            R.id.button_orange -> binding.textBoxFour.setBackgroundResource(android.R.color.holo_orange_light)
            R.id.button_green -> binding.textBoxFive.setBackgroundResource(android.R.color.holo_green_light)


            else -> view.setBackgroundColor(ContextCompat.getColor(this, R.color.c_mybg))
        }
    }
} /*end class MainActivity*/