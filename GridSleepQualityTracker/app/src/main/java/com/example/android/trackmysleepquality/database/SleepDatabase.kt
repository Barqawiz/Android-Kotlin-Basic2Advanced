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

package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [SleepNight::class/*,...*/], version = 1, exportSchema = false)
abstract class SleepDatabase: RoomDatabase() {

    // reference the defined entities Doa
    abstract val sleepDatabaseDao:SleepDatabaseDao

    // access the functions stticlally
    companion object {

        // Volatile make sure the valie of instance is always up-to-date (variable never cached)
        // changes visible to all threads
        @Volatile
        private var INSTANCE: SleepDatabase? = null

        fun getInstance(context: Context) : SleepDatabase {
            // make sure all threads use the same instance
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    // fallbackToDestructiveMigration = a function to defined the rows migration between versions with default wipe
                    // :: = reflection
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            SleepDatabase::class.java,
                            "sleep_history_database"
                    ).fallbackToDestructiveMigration().build()
                } /*end if: instance not null*/

                return instance
            } /*end sync*/
        } /*end fun: get instance*/
    } /*end companion*/
} /*end class*/
