package com.example.android.guesstheword.util

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log

class Helper {

    companion object {
        fun vibrator(pattern: LongArray, buzzer: Vibrator) {
            Log.i("Control", "Vibrate the device")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
        fun formattedTime(millis: Long): String {
            val seconds = (millis / 1000) % 60
            val minute = (millis / 1000) / 60

            return "$minute:$seconds"
        }
    }
}