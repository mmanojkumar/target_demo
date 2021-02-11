package com.target.targetcasestudy

import com.product.presentation.validator.isLuhnCheck
import com.product.presentation.validator.isValidCardProvider
import com.product.presentation.validator.isValidLength
import com.product.presentation.validator.validateCreditCard
import org.junit.Assert
import org.junit.Test

/**
 * Feel free to make modifications to these unit tests! Remember, you have full technical control
 * over the project, so you can use any libraries and testing strategies that see fit.
 */
class CreditCardValidatorTest {

    @Test
    fun `is valid credit card`() {
        val cardNumber = "4539976741512043"
        Assert.assertTrue(
            "Card number is valid # $cardNumber",
            validateCreditCard(cardNumber)
        )
    }


    @Test
    fun `is invalid credit card`() {
        val cardNumber = "12312123123123123"
        Assert.assertFalse(
            "Card number is valid # $cardNumber",
            validateCreditCard(cardNumber)
        )
    }


    @Test
    fun `is valid card number prefix matched`() {
        Assert.assertTrue(
            "is valid prefix matched true",
            isValidCardProvider("4") && isValidCardProvider("5") &&
                    isValidCardProvider("37") && isValidCardProvider("6")
        )
    }

    @Test
    fun `is  invalid card number prefix matched`() {
        Assert.assertFalse(
            "is in valid prefix matched false",
            isValidCardProvider("8")
        )
    }

    @Test
    fun `is valid card length`() {
        Assert.assertTrue(
            "is valid card length should return true",
            isValidLength("4539976741512043")
        )
    }

    @Test
    fun `is invalid card length`() {
        Assert.assertFalse(
            "is invalid card length should return false",
            isValidLength("823234")
        )
    }

    @Test
    fun `is valid luhn check`() {
        Assert.assertTrue(
            "is valid luhn check should return true",
            isLuhnCheck("4539976741512043")
        )
    }

    @Test
    fun `is invalid luhn check`() {
        Assert.assertFalse(
            "is invalid luhn check return true",
            isLuhnCheck("123123123123")
        )
    }


}
