package com.example.fakestoreandro.ui.customview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.fakestoreandro.databinding.LayoutProductCountBinding

class ProductCountView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LayoutProductCountBinding =
        LayoutProductCountBinding.inflate(LayoutInflater.from(context), this)

    private var count: Int = 0

    private var countListener: ((Int) -> Unit)? = null

    init {
        initView()
    }

    private fun initView() = with(binding) {
        tvCount.text = count.toString()

        btnAdd.setOnClickListener { onClickAddButton() }
        btnRemove.setOnClickListener { onClickRemoveButton() }
    }


    private fun onClickAddButton() {
        scaleAnimation().start()
        count++
        updateCountText()
        scaleAnimationReverse().startReverseAnim()

        countListener?.invoke(count)
    }

    private fun onClickRemoveButton() {
        if (count < 1) return

        scaleAnimation().start()
        count--
        updateCountText()
        scaleAnimationReverse().startReverseAnim()

        countListener?.invoke(count)
    }

    fun setCountListener(listener: (Int) -> Unit) {
        countListener = listener
    }

    private fun updateCountText() {
        binding.tvCount.text = count.toString()
    }

    private fun scaleAnimation(): AnimatorSet {
        val scaleX = ObjectAnimator.ofFloat(binding.tvCount, "scaleX", 1.5f)
        val scaleY = ObjectAnimator.ofFloat(binding.tvCount, "scaleY", 1.5f)

        return AnimatorSet().apply {
            playTogether(scaleX, scaleY)
            duration = 500L
        }
    }

    private fun scaleAnimationReverse(): AnimatorSet {
        val scaleX = ObjectAnimator.ofFloat(binding.tvCount, "scaleX", 1f)
        val scaleY = ObjectAnimator.ofFloat(binding.tvCount, "scaleY", 1f)

        return AnimatorSet().apply {
            playTogether(scaleX, scaleY)
            duration = 500L
            startDelay = 300L
        }
    }

    private fun AnimatorSet.startReverseAnim() = this.start()
}