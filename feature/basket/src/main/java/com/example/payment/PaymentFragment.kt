package com.example.payment

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.basket.R
import com.example.basket.databinding.FragmentPaymentBinding
import com.example.ui.customview.creditcard.ExpiryDateTextWatcher
import com.example.util.extension.collectWithLifecycle
import com.example.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PaymentFragment : Fragment(R.layout.fragment_payment) {
    private val binding by viewBinding(FragmentPaymentBinding::bind)

    private val viewModel by viewModels<PaymentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        viewLifecycleOwner.collectWithLifecycle(Lifecycle.State.STARTED) {
            viewModel.uiEvent.collectLatest { uiEvent ->
                when (uiEvent) {
                    is PaymentUiEvent.Success -> {
                        showTextFieldError(false)
                        delay(1000)
                        navigateToPaymentSuccessful()
                    }

                    is PaymentUiEvent.Error -> {
                        uiEvent.apply {
                            showTextFieldError(
                                true,
                                messageCardNumber,
                                messageCardHolderName,
                                messageExpiryDate,
                                messageCvc
                            )
                        }
                    }
                }
            }
        }
    }

    private fun navigateToPaymentSuccessful() {
        val action = PaymentFragmentDirections.toPaymentSuccessFragment()
        findNavController().navigate(action)
    }

    private fun initView() = with(binding) {
        toolbar.setNavigationOnClickListener { findNavController().navigateUp() }

        btnComplete.setOnClickListener {
            val cardNumber = textFieldCardNumber.editText?.text
            val cardHolderName = textFieldHolderName.editText?.text
            val expiryDate = textFieldExpiryDate.editText?.text
            val cvcNumber = textFieldCVV.editText?.text

            viewModel.validation(
                cardNumber.toString(),
                cardHolderName.toString(),
                expiryDate.toString(),
                cvcNumber.toString()
            )
        }

        textFieldExpiryDate.editText?.addTextChangedListener(ExpiryDateTextWatcher {
            viewCreditCard.setExpiryDate(it.toString())
        })

        textFieldCVV.editText?.setOnFocusChangeListener { _, hasFocus ->
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

    private fun showTextFieldError(
        hasError: Boolean,
        messageCardNumber: String = "",
        messageCardHolderName: String = "",
        messageExpiryDate: String = "",
        messageCvc: String = "",
    ) = with(binding) {

        textFieldCardNumber.isErrorEnabled = !hasError
        textFieldCardNumber.error = if(hasError) messageCardNumber else null

        textFieldHolderName.isErrorEnabled = !hasError
        textFieldHolderName.error = if(hasError) messageCardHolderName else null

        textFieldExpiryDate.isErrorEnabled = !hasError
        textFieldExpiryDate.error = if(hasError) messageExpiryDate else null

        textFieldCVV.isErrorEnabled = !hasError
        textFieldCVV.error = if(hasError) messageCvc else null
    }
}
