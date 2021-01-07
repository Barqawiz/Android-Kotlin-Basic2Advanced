package com.training.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    /*
    default way to write null
    var resultText: TextView? = null
    var resultImage: ImageView? = null
    */
    lateinit var resultText: TextView
    lateinit var resultImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // find views
        val rollButotn: Button = findViewById(R.id.button_roll)
        resultText = findViewById(R.id.text_result)
        resultImage = findViewById(R.id.image_dice)

        // roll action
        rollButotn.setOnClickListener {
            // Toast.makeText(this, "Roll the dcice", Toast.LENGTH_SHORT).show()
            rollDice()
        }

    } /*end funciton - on create*/

    private fun rollDice() {
        // random next int generate from 0 to number-1
        val selctNum = Random.nextInt(6) + 1

        // set text value
        // set the label
        val textValue = "${getString(R.string.selected_value)} ${selctNum.toShort()}"
        resultText.text = textValue

        // set image
        val selectedResource = when(selctNum) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        resultImage.setImageResource(selectedResource)

    } /*end function*/

} /*end class*/