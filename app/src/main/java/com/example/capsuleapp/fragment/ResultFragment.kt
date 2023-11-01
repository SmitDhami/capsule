package com.example.capsuleapp.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.capsuleapp.activity.MainActivity
import com.example.capsuleapp.databinding.FragmentResultBinding

class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater)
        val view = binding.root
        MainActivity.rel_bg4.setBackgroundColor(Color.parseColor("#44BAF8"))

        setUi()

        return view
    }

    private fun setUi() {
        if (MainActivity.bool_q_1 == true && MainActivity.bool_q_2 == true){
            binding.textViewUserScore.text = "100 / 100"
            binding.textViewCorrectAnswersCount.text = "2 / 2"
        }else if (MainActivity.bool_q_1 == true && MainActivity.bool_q_2 == false ){
            binding.textViewUserScore.text = "50 / 100"
            binding.textViewCorrectAnswersCount.text = "1 / 2"
        }else if (MainActivity.bool_q_1 == false && MainActivity.bool_q_2 == true ){
            binding.textViewUserScore.text = "50 / 100"
            binding.textViewCorrectAnswersCount.text = "1 / 2"
        }else if (MainActivity.bool_q_1 == false && MainActivity.bool_q_2 == false ){
            binding.textViewUserScore.text = "00 / 100"
            binding.textViewCorrectAnswersCount.text = "0 / 2"
        }

        binding.btnRetry.setOnClickListener(){
            MainActivity.bool_q_1 = false
            MainActivity.bool_q_2 = false
            startActivity(Intent(requireActivity(), MainActivity::class.java))

        }


    }
}