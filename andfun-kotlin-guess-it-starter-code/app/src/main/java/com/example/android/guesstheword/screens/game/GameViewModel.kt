package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel(){

    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String> //backing property
        get() = _word

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> //backing property
        get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish


    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    private val _timerText = MutableLiveData<String>()
    val timerText: LiveData<String>
        get() = _timerText

    val currentTimeString = Transformations.map(currentTime, {time ->
        DateUtils.formatElapsedTime(time)
    })

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 15000L
    }

    private val timer: CountDownTimer

    init {
        Log.i("GameViewModel", "GameViewModel created!")

        _eventGameFinish.value = false
        _currentTime.value = 0L
        _timerText.value = ""

        resetList()
        nextWord()

        _word.value = ""
        _score.value = 0

        //create countdown timer
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (_currentTime.value)?.plus(1)

                _timerText.value = DateUtils.formatElapsedTime(_currentTime.value ?: 0L)
            }

            override fun onFinish() {
                _eventGameFinish.value = true;
            }
        }

        timer.start()
    }

    override fun onCleared() {
        Log.i("GameViewModel", "GameViewModel destroyed!")
        super.onCleared()
        timer.cancel()
    }


    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()

        } else {
            _word.value = wordList.removeAt(0)
        }
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    fun onGameFinishComplete(){
        _eventGameFinish.value = false
    }

}
