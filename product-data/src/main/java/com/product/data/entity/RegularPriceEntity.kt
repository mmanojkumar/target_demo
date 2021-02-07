package com.product.data.entity

import com.google.gson.annotations.SerializedName

data class RegularPriceEntity(@SerializedName("amount_in_cents") var amountInCents: Double?) {

    @SerializedName("currency_symbol")
    var currencySymbol: String? = null

    @SerializedName("display_string")
    var displayString: String? = null
}