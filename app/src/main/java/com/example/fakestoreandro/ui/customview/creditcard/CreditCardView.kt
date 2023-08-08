package com.example.fakestoreandro.ui.customview.creditcard

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.LayoutCreditCardBinding

class CreditCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutCreditCardBinding =
        LayoutCreditCardBinding.inflate(LayoutInflater.from(context), this)

    private lateinit var frontAnim: AnimatorSet
    private lateinit var backAnim: AnimatorSet

    init {
        setFlipAnimation()
    }

    private fun setFlipAnimation() {
        val scale = resources.displayMetrics.density

        val front = binding.clCreditCard
        val back = binding.clCreditCardBack

        front.cameraDistance = 16000 * scale
        back.cameraDistance = 16000 * scale

        frontAnim = AnimatorInflater.loadAnimator(
            context,
            R.animator.front_animator
        ) as AnimatorSet

        backAnim =
            AnimatorInflater.loadAnimator(context, R.animator.back_animator) as AnimatorSet
    }

    fun flip(isFront: Boolean) {
        if (isFront) {
            frontAnim.setTarget(binding.clCreditCard)
            backAnim.setTarget(binding.clCreditCardBack)
            frontAnim.start()
            backAnim.start()
        } else {
            frontAnim.setTarget(binding.clCreditCardBack)
            backAnim.setTarget(binding.clCreditCard)
            frontAnim.start()
            backAnim.start()
        }
    }

    fun setCreditCardNumber(number: String?) {
        if (number.isNullOrEmpty()) {
            binding.tvCardNumber.setText(R.string.placeholder_card_number)
            return
        }
        binding.tvCardNumber.text = number
    }

    fun setCardHolderName(fullName: String?) {
        if (fullName.isNullOrEmpty()) {
            binding.tvCardHolderName.setText(R.string.text_your_name)
            return
        }
        binding.tvCardHolderName.text = fullName
    }

    fun setExpiryDate(expiryDate: String?) {
        if (expiryDate.isNullOrEmpty()) {
            binding.tvExpiryDate.setText(R.string.placeholder_expiry_date)
            return
        }
        binding.tvExpiryDate.text = expiryDate
    }

    fun setCvcNumber(cvc: String?) {
        binding.tvCvc.text = cvc
    }
}
