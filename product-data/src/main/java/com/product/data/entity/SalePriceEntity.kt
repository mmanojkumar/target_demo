package com.product.data.entity

import com.google.gson.annotations.SerializedName

/**
 * description:
    The regular (non-sale) price of the product

    amountInCents*	integer($int32)
    nullable: false
    The price in whole cents (USD)

    currencySymbol*	string
    example: $
    nullable: false
    The currency symbol to use when displaying this price

    displayString*	string
    example: $5,372.99
    nullable: false
    A pre-formatted display string that can be used to display the price
 */

data class SalePriceEntity(@SerializedName("amount_in_cents") val amountInCents: Int,
                              @SerializedName("currency_symbol") val currencySymbol: String,
                              @SerializedName("display_string") val displayString: String)



