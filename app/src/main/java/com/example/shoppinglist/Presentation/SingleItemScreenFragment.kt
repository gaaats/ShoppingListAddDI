package com.example.shoppinglist.Presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentSingleItemScreenBinding

class SingleItemScreenFragment : Fragment() {
    lateinit var binding: FragmentSingleItemScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingleItemScreenBinding.inflate(inflater)

        return binding.root
    }

}