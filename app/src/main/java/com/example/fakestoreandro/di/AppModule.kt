package com.example.fakestoreandro.di

import com.example.fakestoreandro.domain.validation.payment.PaymentValidations
import com.example.fakestoreandro.domain.validation.ValidateEmptyField
import com.example.fakestoreandro.domain.validation.payment.ValidateCardNumber
import com.example.fakestoreandro.domain.validation.payment.ValidateCvc
import com.example.fakestoreandro.domain.validation.payment.ValidateExpiryDate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaymentValidations() = PaymentValidations(
        validateCardNumber = ValidateCardNumber(),
        validateCardHolderName = ValidateEmptyField(),
        validateExpiryDate = ValidateExpiryDate(),
        validateCvc = ValidateCvc()
    )
}