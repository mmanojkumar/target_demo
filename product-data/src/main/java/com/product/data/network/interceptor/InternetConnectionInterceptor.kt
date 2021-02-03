package  com.product.data.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.tutorial.github.commits.latest.data.network.interceptor.NoInternetException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


class InternetConnectionInterceptor(private val context: Context) : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        if(isConnected()){
            val builder: Request.Builder = chain.request().newBuilder()
            return chain.proceed(builder.build())
        }

        throw NoInternetException()
    }


    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}