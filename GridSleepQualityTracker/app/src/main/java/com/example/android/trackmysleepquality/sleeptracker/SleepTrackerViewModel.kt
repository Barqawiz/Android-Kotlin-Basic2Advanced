/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import android.text.Spanned
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.trackmysleepquality.convertDurationToFormatted
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.*

/**
 * ViewModel for SleepTrackerFragment.
 * AndroidViewModel similar to viewModel but it takes the application context as parameter
 */
class SleepTrackerViewModel(
        val database: SleepDatabaseDao,
        application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val allNights = database.getAllNights()

    /*val allNightsString = Transformations.map(allNights) { allNights ->
        formatNights(allNights, application.resources)
    }*/

    private var tonight = MutableLiveData<SleepNight?>()

    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()
    val navigateToSleepQuality: LiveData<SleepNight>
        get() = _navigateToSleepQuality

    // visibility map
    val startButtonVisible = Transformations.map(tonight) {
        null == it
    }

    val stopButtonVisible = Transformations.map(tonight) {
        null != it
    }

    val clearButtonVisible = Transformations.map(allNights) {
        it?.isNotEmpty()
    }

    // details message
    private var _detailsMesssage = MutableLiveData<String>()

    val detailsMesssage: LiveData<String>
        get() = _detailsMesssage

    // snackbar message
    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    init {
        initalizeTonight()
    }

    fun onStartTracking() {
        uiScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDB()
        }
    }

    fun onStopTracking() {
        uiScope.launch {
            val toUpdateNight = tonight.value ?: return@launch
            toUpdateNight.endTimeMilli = System.currentTimeMillis()

            update(toUpdateNight)

            _navigateToSleepQuality.value = toUpdateNight
            tonight.value = null
        }
    }

    fun onDoneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun onClear() {
        uiScope.launch {
            clear()
            tonight.value = null
            _showSnackbarEvent.value = true
        }
    }

    fun onDoneNavigating() {
        _navigateToSleepQuality.value = null
    }

    fun getAllNightsString(): Spanned {
        return formatNights(allNights.value ?: listOf<SleepNight>(), getApplication<Application>().resources)
    }

    fun displayDetailsById(nightId: Long) {
        uiScope.launch {
            _detailsMesssage.value = getDetailsById(nightId)
        }
    }

    /* --------- --------- --------- --------- */

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    private suspend fun update(toUpdateNight: SleepNight) {
        withContext(Dispatchers.IO) {
            database.update(toUpdateNight)
        }
    }

    private suspend fun insert(newNight: SleepNight) {
        withContext(Dispatchers.IO) {
            database.insert(newNight)
        }
    }

    private fun initalizeTonight() {
        uiScope.launch {
            tonight.value = getTonightFromDB()
        }
    }

    private suspend fun getTonightFromDB(): SleepNight? {
        return withContext(Dispatchers.IO) {
            var night = database.getTonight()
            if (night?.endTimeMilli != night?.startTimeMilli) {
                // no open nights
                night = null;
            }
            night
        }
    }

    private suspend fun getDetailsById(nightId: Long): String? {
        return withContext(Dispatchers.IO) {
            var night = database.get(nightId)
            var details = "Unknown"
            night?.let {
                details = "sleep duration: ${convertDurationToFormatted(night.startTimeMilli, night.endTimeMilli,
                        getApplication<Application>().resources)}"
            }
            details
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

