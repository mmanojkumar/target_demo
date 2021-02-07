package  com.product.data.network.interceptor

import com.google.gson.Gson
import com.product.data.exception.ApiException
import okhttp3.Interceptor
import okhttp3.Response


class FailureResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()
        val response = chain.proceed(request)
        if (response.code() != 200) {
            throw Gson().fromJson<ApiException>(response.body().string(), ApiException::class.java)
        }
        return response
    }

}