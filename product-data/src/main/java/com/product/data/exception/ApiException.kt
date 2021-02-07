package com.product.data.exception

class ApiException(val code:String, override val message:String) : Exception()