package com.example.fakestoreandro.ui.payment

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.fakestoreandro.R
import com.example.fakestoreandro.databinding.FragmentPaymentBinding
import com.example.fakestoreandro.ui.customview.creditcard.ExpiryDateTextWatcher
import com.example.fakestoreandro.util.viewbinding.viewBinding


class PaymentFragment : Fragment(R.layout.fragment_payment) {
    private val binding by viewBinding(FragmentPaymentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() = with(binding) {
        textFieldExpiryDate.editText?.addTextChangedListener(ExpiryDateTextWatcher {
            viewCreditCard.setExpiryDate(it.toString())
        })

        textFieldCVV.editText?.setOnFocusChangeListener { v, hasFocus ->
            viewCreditCard.flip(hasFocus)
        }

        textFieldCardNumber.editText?.addTextChangedListener {
            viewCreditCard.setCreditCardNumber(it.toString())
        }

        textFieldHolderName.editText?.addTextChangedListener {
            viewCreditCard.setCardHolderName(it.toString())
        }

        textFieldCVV.editText?.addTextChangedListener {
            viewCreditCard.setCvcNumber(it.toString())
        }
    }
}
