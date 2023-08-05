package com.example.fakestoreandro.ui.payment

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentPaymentBinding
import com.example.fakestoreandro.util.viewbinding.viewBinding


class PaymentFragment : Fragment(R.layout.fragment_payment) {
    private val binding by viewBinding(FragmentPaymentBinding::bind)

    private lateinit var frontAnim: AnimatorSet
    private lateinit var backAnim: AnimatorSet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flipAnim()
    }

    private fun flipAnim() {
        var isFront = true
        val scale = resources.displayMetrics.density

        val front = binding.cardFront
        val back = binding.cardBack
        val button = binding.flipBtn

        front.cameraDistance = 16000 * scale
        back.cameraDistance = 16000 * scale

        frontAnim = AnimatorInflater.loadAnimator(
            requireContext(),
            R.animator.front_animator
        ) as AnimatorSet

        backAnim =
            AnimatorInflater.loadAnimator(requireContext(), R.animator.back_animator) as AnimatorSet


        button.setOnClickListener {
            if(isFront) {
                frontAnim.setTarget(front)
                backAnim.setTarget(back)
                frontAnim.start()
                backAnim.start()
                isFront = false
            } else {
                frontAnim.setTarget(back)
                backAnim.setTarget(front)
                frontAnim.start()
                backAnim.start()
                isFront = true
            }
        }
    }

}