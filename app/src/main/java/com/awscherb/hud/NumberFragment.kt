package com.awscherb.hud

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_number.*

class NumberFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_number, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        numberFragmentNumber.text = arguments?.getInt(EXTRA_NUMBER)?.toString() ?: "0"
    }

    companion object {
        private const val EXTRA_NUMBER = "number"
        fun newInstance(number: Int): NumberFragment = NumberFragment().apply {
            arguments = Bundle().apply {
                putInt(EXTRA_NUMBER, number)
            }
        }
    }
}