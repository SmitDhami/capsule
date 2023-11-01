package com.example.capsuleapp.activity

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.example.capsuleapp.fragment.VideoFragment
import com.example.capsuleapp.R
import com.example.capsuleapp.timer.TimerViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var timerViewModel: TimerViewModel
    private lateinit var timerTextView: TextView
    private lateinit var btn_retry: TextView

    companion object {
        lateinit var rel_bg1: RelativeLayout
        lateinit var rel_bg2: RelativeLayout
        lateinit var rel_bg3: RelativeLayout
        lateinit var rel_bg4: RelativeLayout
        lateinit var rel_retry_layout: RelativeLayout
          var bool_q_1: Boolean ?=  false
          var bool_q_2: Boolean ?=  false
          var bool_check: Boolean ?=  false
    }


    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timerTextView = findViewById(R.id.timerTextView)
        rel_bg1 = findViewById(R.id.rel_bg1)
        rel_bg2 = findViewById(R.id.rel_bg2)
        rel_bg3 = findViewById(R.id.rel_bg3)
        rel_bg4 = findViewById(R.id.rel_bg4)
        btn_retry = findViewById(R.id.btn_retry)
        rel_retry_layout = findViewById(R.id.rel_retry_layout)
        timerViewModel = ViewModelProvider(this)[TimerViewModel::class.java]

        timerViewModel.timerLiveData.observe(this) { secondsRemaining ->
            if (secondsRemaining == 1) {
                val minutes = secondsRemaining / 60
                val seconds = secondsRemaining % 60
                timerTextView.text = String.format("%02d:%02d", minutes, seconds) + " min"
                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    rel_retry_layout.visibility = View.VISIBLE
                    timerTextView.text = "00:00" + " min"

                }, 1000)
                btn_retry.setOnClickListener() {

                    rel_retry_layout.visibility = View.GONE
                    timerTextView.text = "00:00" + " min"
                    val mainActivity = MainActivity()
                    mainActivity.setfragment(supportFragmentManager, VideoFragment())
                    timerViewModel.stopTimer()
                    timerViewModel.restartTimer()
                    setstaper()

                }
            } else {
                val minutes = secondsRemaining / 60
                val seconds = secondsRemaining % 60
                timerTextView.text = String.format("%02d:%02d", minutes, seconds) + " min"
            }
        }
        setfragment(supportFragmentManager, VideoFragment())
    }

    private fun setstaper() {
        rel_bg2.setBackgroundColor(Color.parseColor("#BBE8FF"))
        rel_bg3.setBackgroundColor(Color.parseColor("#BBE8FF"))
        rel_bg4.setBackgroundColor(Color.parseColor("#BBE8FF"))
    }

    // drawer when the icon is clicked
    fun setfragment(fragmentManager: FragmentManager, fragment: Fragment?) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.title_fragment, fragment!!)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {

    }

}