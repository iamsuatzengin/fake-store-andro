package com.example.fakestoreandro.ui.profile

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentProfileBinding
import com.example.fakestoreandro.util.viewbinding.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rowMyOrders.setOnRowClickListener {
            println("row tiklandi ! - myOrders")
        }
    }

    override fun onStart() {
        super.onStart()

        requireActivity().window.statusBarColor =
            ResourcesCompat.getColor(resources, R.color.color_primary, null)
    }

    override fun onDestroy() {
        super.onDestroy()

        requireActivity().window.statusBarColor = Color.WHITE
    }

}