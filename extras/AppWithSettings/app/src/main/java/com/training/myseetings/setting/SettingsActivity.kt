package com.training.myseetings.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

public val KEY_PREF_EXAMPLE_SWITCH = "example_switch"

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // add setting fragment to the activity
        supportFragmentManager.beginTransaction().replace(android.R.id.content, SettingsFragment()).commit()
    }
}