/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding
import com.example.android.guesstheword.util.Helper

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private val TAG = "GameFragment"

    // viewModel
    private lateinit var viewModel: GameViewModel

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )
        // allow live data to automatically update the layout
        binding.lifecycleOwner = this

        // reference the game view model (:: used for reflection in Kotlin)
        Log.i(TAG, "calling viewModel reference")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        // bind the data
        binding.gameViewModel = viewModel

        // setup the observer relationship with the live data
        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { status ->
            if (status) {
                gameFinished()
                // reset the finish status
                viewModel.onGameFinishComplete()
            }
        })

        viewModel.eventBuzz.observe(viewLifecycleOwner, Observer { type ->
            if (type != GameViewModel.BuzzType.NO_BUZZ) {
                buzz(type.pattern)
                viewModel.onBuzzComplete()
            }
        })

        // *** removed and replaced with data binding ***
        /*
        viewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
            updateWordText(newWord)
        })
        viewModel.score.observe(viewLifecycleOwner, Observer { newScoer ->
            updateScoreText(newScoer.toString())
        })
        viewModel.currentTimeString.observe(viewLifecycleOwner, Observer { newTimer ->
            binding.timerText.text = newTimer
        })
        */

        // process
        return binding.root

    }

    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val currentScore = viewModel.score.value ?: 0
        val action = GameFragmentDirections.actionGameToScore(currentScore)

        findNavController(this).navigate(action)
    }

    /** Methods for updating the UI **/

    private fun updateWordText(word: String) {
        binding.wordText.text = word

    }

    private fun updateScoreText(newScore: String) {
        binding.scoreText.text = newScore
    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            Helper.vibrator(pattern, buzzer)
        }
    }

}
