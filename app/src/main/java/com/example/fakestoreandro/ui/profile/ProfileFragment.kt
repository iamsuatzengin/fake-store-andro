package com.example.fakestoreandro.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentProfileBinding
import com.example.fakestoreandro.util.viewbinding.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}