package com.training.myseetings.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.preference.PreferenceManager
import com.training.myseetings.R
import com.training.myseetings.databinding.ActivityMainBinding
import com.training.myseetings.setting.KEY_PREF_EXAMPLE_SWITCH
import com.training.myseetings.setting.SettingsActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        // set default preference
        // false, this method sets the default values only if this method has never been called in the past
        PreferenceManager
            .setDefaultValues(this, R.xml.preferences, false);



    }

    override fun onStart() {
        super.onStart()
        displaySwtichValue()
    }

    fun displaySwtichValue() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val switchPref = sharedPreferences.getBoolean(KEY_PREF_EXAMPLE_SWITCH, false)

        Snackbar.make(binding.root, "Switch : ${switchPref}", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}