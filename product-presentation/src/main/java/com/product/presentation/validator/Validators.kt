package com.product.presentation.validator

/**
 *
    Reference  https://www.geeksforgeeks.org/program-credit-card-number-validation/
    Credit card numbers follow certain patterns.
    A credit card number must have between 13 and 16 digits. It must start with:

    4 for Visa cards
    5 for Master cards
    37 for American Express cards
    6 for Discover cards
    The problem can be solved by using Luhn algorithm.
    Luhn check or the Mod 10 check, which can be described as follows (for illustration,
    consider the card number 4388576018402626):

    Step 1. Double every second digit from right to left. If doubling of a digit results in a
    two-digit number, add up the two digits to get a single-digit number (like for 12:1+2, 18=1+8).

    Step 2. Now add all single-digit numbers from Step 1.
    4 + 4 + 8 + 2 + 3 + 1 + 7 + 8 = 37

    Step 3. Add all digits in the odd places from right to left in the card number.
    6 + 6 + 0 + 8 + 0 + 7 + 8 + 3 = 38

    Step 4. Sum the results from Step 2 and Step 3.
    37 + 38 = 75

    Step 5. If the result from Step 4 is divisible by 10, the card number is valid; otherwise, it is invalid.
 */
fun validateCreditCard(creditCardNumber: String): Boolean {
    return isValidLength(creditCardNumber) &&
            isValidCardProvider(creditCardNumber) &&
            isLuhnCheck(creditCardNumber)
}


fun isValidCardProvider(creditCardNumber: String): Boolean {
    return creditCardNumber.startsWith("4") ||
            creditCardNumber.startsWith("5") ||
            creditCardNumber.startsWith("37") ||
            creditCardNumber.startsWith("6")
}

fun isValidLength(creditCardNumber: String): Boolean {
    return creditCardNumber.length in 13..16
}


fun isLuhnCheck(creditCardNumber: String): Boolean {
    return (sumOfDoubleEvenPlace(creditCardNumber.toLong()) +
            sumOfOddPlace(creditCardNumber.toLong())) % 10 == 0
}

fun getDigit(number: Int): Int {
    return if (number < 9) number else number / 10 + number % 10
}

fun sumOfDoubleEvenPlace(number: Long): Int {
    var sum = 0
    val num = number.toString() + ""
    var i = getSize(number) - 2
    while (i >= 0) {
        sum += getDigit((num[i].toString() + "").toInt() * 2)
        i -= 2
    }
    return sum
}

fun sumOfOddPlace(number: Long): Int {
    var sum = 0
    val num = number.toString() + ""
    var i: Int = getSize(number) - 1
    while (i >= 0) {
        sum += (num[i].toString() + "").toInt()
        i -= 2
    }
    return sum
}

fun getSize(d: Long): Int {
    val num = d.toString() + ""
    return num.length
}