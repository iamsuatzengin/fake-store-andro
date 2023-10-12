package com.example.domain.di

import com.example.domain.validation.ValidateEmptyField
import com.example.domain.validation.payment.PaymentValidations
import com.example.domain.validation.payment.ValidateCardNumber
import com.example.domain.validation.payment.ValidateCvc
import com.example.domain.validation.payment.ValidateExpiryDate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun providePaymentValidations() = PaymentValidations(
        validateCardNumber = ValidateCardNumber(),
        validateCardHolderName = ValidateEmptyField(),
        validateExpiryDate = ValidateExpiryDate(),
        validateCvc = ValidateCvc()
    )
}