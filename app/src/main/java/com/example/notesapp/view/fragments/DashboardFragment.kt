package com.example.notesapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    var floatingBtnVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        binding = FragmentDashboardBinding.bind(view)
        return binding.root
    }
}