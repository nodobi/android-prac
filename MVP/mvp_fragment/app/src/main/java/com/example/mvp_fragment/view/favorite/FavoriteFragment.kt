package com.example.mvp_fragment.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvp_fragment.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    lateinit var binding: FragmentFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, null, false)
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}