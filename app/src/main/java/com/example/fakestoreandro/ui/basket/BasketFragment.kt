package com.example.fakestoreandro.ui.basket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentBasketBinding
import com.example.fakestoreandro.util.viewbinding.viewBinding


class BasketFragment : Fragment(R.layout.fragment_basket) {
    private val binding by viewBinding(FragmentBasketBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}