package com.example.capsuleapp.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.capsuleapp.activity.MainActivity
import com.example.capsuleapp.databinding.FragmentNotesBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NotesFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentNotesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(layoutInflater)
        val view = binding.root

        MainActivity.rel_bg2.setBackgroundColor(Color.parseColor("#44BAF8"))

        binding.nextButton.setOnClickListener() {
            val mainActivity = MainActivity()
            mainActivity.setfragment(requireActivity().supportFragmentManager, FirstFragment())
        }

        return view
    }
}