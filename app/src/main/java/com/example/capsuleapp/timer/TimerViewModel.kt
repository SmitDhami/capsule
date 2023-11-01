package com.example.capsuleapp.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class TimerViewModel : ViewModel() {
    private var timer = Timer()
    private var secondsRemaining = 600 // 5 minutes

    private val _timerLiveData = MutableLiveData<Int>()
    val timerLiveData: LiveData<Int>
        get() = _timerLiveData

    init {
        startTimer()
    }

    fun startTimer() {
        timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (secondsRemaining > 0) {
                    _timerLiveData.postValue(secondsRemaining)
                    secondsRemaining--
                } else {
                    timer.cancel()
                }
            }
        }, 0, 1000) // Update every second
    }

    // Function to restart the timer
    fun restartTimer() {
        // Cancel the existing timer
        timer.cancel()
        // Reset the timer to 5 minutes
        secondsRemaining = 600

        // Start a new timer
        startTimer()
    }

    fun stopTimer() {
        timer.cancel()
    }
}