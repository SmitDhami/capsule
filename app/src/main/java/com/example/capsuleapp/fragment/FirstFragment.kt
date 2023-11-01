package com.example.capsuleapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.capsuleapp.activity.MainActivity
import com.example.capsuleapp.activity.MainActivity.Companion.bool_check
import com.example.capsuleapp.activity.MainActivity.Companion.bool_q_1
import com.example.capsuleapp.activity.MainActivity.Companion.bool_q_2
import com.example.capsuleapp.R

class FirstFragment : Fragment() {

    private lateinit var question: TextView
    private lateinit var answer_1: EditText
    private lateinit var answer_2: EditText
    private lateinit var answer_3: EditText
    private lateinit var answer_4: EditText
    private lateinit var next_q: RelativeLayout
    private lateinit var rel_chek: RelativeLayout
    private var check_click: Int = 5

    var btn_array = ArrayList<EditText>()
    var question_array = HashMap<String, ArrayList<String>>()

    companion object {
        var answerarray = ArrayList<String>()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_first, container, false)
        findview(view)
        MainActivity.rel_bg3.setBackgroundColor(Color.parseColor("#44BAF8"))

        return view
    }

    private fun findview(view: View) {
        answer_1 = view.findViewById(R.id.answer_1)
        answer_2 = view.findViewById(R.id.answer_2)
        answer_3 = view.findViewById(R.id.answer_3)
        answer_4 = view.findViewById(R.id.answer_4)
        next_q = view.findViewById(R.id.next_q)
        question = view.findViewById(R.id.question)
        rel_chek = view.findViewById(R.id.rel_chek)


        btn_array.clear()
        btn_array.add(answer_1)
        btn_array.add(answer_2)
        btn_array.add(answer_3)
        btn_array.add(answer_4)
        var answer_array = ArrayList<String>()
        answer_array.add("Seismic zones")
        answer_array.add("Fault zones")
        answer_array.add("Danger zones")
        answer_array.add("Both(A) and (B)")

        var answer_array2 = ArrayList<String>()
        answer_array2.add("Japan")
        answer_array2.add("Asia")
        answer_array2.add("South Korea")
        answer_array2.add("Malaysia")

        question_array.put(
            "The weak zones of Earth's crust which are more prone to earthquakes are called _____?",
            answer_array
        )
        question_array.put(
            "The world’s nation 5G mobile network was launched by which country?", answer_array2
        )
        btnClick()
    }

    private fun btnClick() {
        rel_chek.setOnClickListener() {
            if (check_click == 5) {
                Toast.makeText(requireActivity(), "Please Select Answer", Toast.LENGTH_LONG).show()
            } else {
                if (btn_array[check_click].text.contains("Both(A) and (B)")) {
                    Toast.makeText(requireActivity(), "Right Answer", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireActivity(), "Wrong Answer", Toast.LENGTH_LONG).show()
                }
            }
        }
        next_q.setOnClickListener() {
            if (check_click == 5) {
                Toast.makeText(requireActivity(), "Please Select Answer", Toast.LENGTH_LONG).show()
            } else {

                btn_array[check_click].setBackgroundResource(R.drawable.q_bg)
                btn_array[check_click].setTextColor(resources.getColor(R.color.black))


                var mcq_array = ArrayList<String>()
                mcq_array.add(answer_1.text.toString())
                mcq_array.add(answer_2.text.toString())
                mcq_array.add(answer_3.text.toString())
                mcq_array.add(answer_4.text.toString())
                answerarray.add(btn_array[check_click].text.toString())

                if (btn_array[check_click].text.contains("Both(A) and (B)")) {
                    bool_q_1 = true
                }
                if (btn_array[check_click].text.contains("South Korea")){
                    bool_q_2 = true
                }

                if (bool_check == true) {
                    bool_check = false
                    val mainActivity = MainActivity()
                    mainActivity.setfragment(requireActivity().supportFragmentManager, ResultFragment())
                }else {
                    bool_check = true
                    for (key in question_array.keys) {
                        question.text = key
                        answer_1.setText(question_array[key]?.get(0))
                        answer_2.setText(question_array[key]?.get(1))
                        answer_3.setText(question_array[key]?.get(2))
                        answer_4.setText(question_array[key]?.get(3))
                    }
                    btn_array[check_click].setBackgroundResource(R.drawable.q_bg)
                }
                check_click = 5
            }

        }
        loopclick()

    }

    private fun loopclick() {

        for (i in 0 until btn_array.size) {
            hiding(i)
            if (check_click == i) {
                btn_array[i].setBackgroundResource(R.drawable.q_bg_select)
                btn_array[i].setTextColor(resources.getColor(R.color.white))
            } else {
                btn_array[i].setBackgroundResource(R.drawable.q_bg)
                btn_array[i].setTextColor(resources.getColor(R.color.black))
            }
            btn_array[i].setOnClickListener() {
                check_click = i
                loopclick()
            }
        }
    }

    private fun hiding(i: Int) {
        btn_array[i].isFocusable = false
        btn_array[i].isCursorVisible = false
        btn_array[i].isClickable = true
        btn_array[i].keyListener = null
    }
}