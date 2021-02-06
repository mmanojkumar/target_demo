package  com.product.data.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import com.tutorial.github.commits.latest.data.network.interceptor.NoInternetException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class InternetConnectionInterceptor @Inject constructor(val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (isConnected()) {
            val builder: Request.Builder = chain.request().newBuilder()
            return chain.proceed(builder.build())
        }

        throw NoInternetException()
    }


    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var hasWifi = false
        var hasMobileData = false
        val networkInfos = connectivityManager.allNetworkInfo
        for (info in networkInfos) {
            if (info.typeName
                    .equals("WIFI", ignoreCase = true)
            ) if (info.isConnected) hasWifi = true
            if (info.typeName
                    .equals("MOBILE DATA", ignoreCase = true)
            ) if (info.isConnected) hasMobileData = true
        }
        return hasMobileData || hasWifi
    }
}