package com.imdb.movies.network



import com.imdb.movies.base.AppClass
import com.imdb.movies.network.ApiConstant.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit


object ApiClient {

    val apiService: ApiService by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        buildClient().create(ApiService::class.java)
    }

    private fun buildClient(): Retrofit {
        val okHttpClient by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { buildHttpClient() }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder()
//                    .excludeFieldsWithoutExposeAnnotation()
                    .create()))
            .client(okHttpClient)
            .build()
    }

    private fun buildHttpClient(): OkHttpClient {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)

        return OkHttpClient.Builder()
//            .cookieJar(JavaNetCookieJar(cookieManager))
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(Interceptor {

                val original: Request = it.request()
                val request = original.newBuilder()
                    .addHeader("content-type", "application/json")
                    .addHeader("X-RapidAPI-Host", "imdb188.p.rapidapi.com")
                    .addHeader("X-RapidAPI-Key", "badf99a020mshbb45f3eb579140fp1a8287jsn7eea716d7241")
//                    .addHeader("app-version", BuildConfig.VERSION_NAME)
                    .build()
                println("@@@@@@@@@@@@ request: $request")
                return@Interceptor it.proceed(request)
            })
            .addInterceptor(Interceptor {
                val response: Response = it.proceed(it.request())
                println("@@@@@@@@@@@@ response: $response")
                return@Interceptor response
            }).build()
    }

}