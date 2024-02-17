package com.yocto.wetodo.calendar

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yocto.wetodo.MainActivity
import com.yocto.wetodo.R

class CalendarFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateStatusBarColor()
    }

    private fun updateStatusBarColor() {
        (activity as? MainActivity)?.updateStatusBarColor(Color.parseColor("#00ff00"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.calendar_fragment, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CalendarFragment()
    }
}