package com.product.presentation.validator

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */
fun validateCreditCard(creditCardNumber: String): Boolean {
  return isValidLength(creditCardNumber) &&
          isValidCardProvider(creditCardNumber) &&
          isLuhnCheck(creditCardNumber)
}

/**
 * Credit card numbers follow certain patterns.
  A credit card number must have between 13 and 16 digits. It must start with:
    4 for Visa cards
    5 for Master cards
    37 for American Express cards
    6 for Discover cards
 */

fun isValidCardProvider(creditCardNumber: String):Boolean{
    return creditCardNumber.startsWith("4") ||
      creditCardNumber.startsWith("5")  ||
      creditCardNumber.startsWith("37")  ||
      creditCardNumber.startsWith("6")
}

fun isValidLength(creditCardNumber: String):Boolean{
  return creditCardNumber.length in 13..16
}


fun isLuhnCheck(creditCardNumber:String):Boolean{
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