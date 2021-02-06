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
    fun `is credit card number valid`() {
        Assert.assertTrue(
            "valid credit card number should yield true",
            validateCreditCard("4539976741512043")
        )
    }


    @Test
    fun `is credit card number not valid`() {
        Assert.assertFalse(
            "valid credit card number should yield true",
            validateCreditCard("12312123123123123")
        )
    }


    @Test
    fun `is valid visa card provider`() {
        Assert.assertTrue(
            "is valid visa card provider should return true",
            isValidCardProvider("4") && isValidCardProvider("5") &&
                    isValidCardProvider("37") && isValidCardProvider("6")
        )
    }

    @Test
    fun `is not valid visa card provider`() {
        Assert.assertFalse(
            "is valid visa card provider should return false",
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
    fun `is not valid card length`() {
        Assert.assertFalse(
            "is valid card length should return false",
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
    fun `is not valid luhn check`() {
        Assert.assertFalse(
            "is valid luhn check return true",
            isLuhnCheck("123123123123")
        )
    }


}
