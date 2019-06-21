package com.gromyk.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory: KoinComponent {
    // Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addEncodedQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()
        chain.proceed(newRequest)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //OkHttpClient for building http request url
    private val lastFmClient =
        OkHttpClient()
            .newBuilder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    fun retrofit(): Retrofit = Retrofit.Builder()
        .client(lastFmClient)
        .baseUrl(BaseUrl.BASE_REST_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}