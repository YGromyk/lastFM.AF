package com.gromyk.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory : KoinComponent {
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
                .newBuilder()
                .addEncodedQueryParameter("api_key", BuildConfig.API_KEY)
                .addEncodedQueryParameter("format", "json")
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

    private val lastFmClient =
            OkHttpClient()
                    .newBuilder()
                    .addInterceptor(authInterceptor)
                    .retryOnConnectionFailure(true)
                    .writeTimeout(15_000, TimeUnit.MILLISECONDS)
                    .readTimeout(15_000, TimeUnit.MILLISECONDS)
                    .addInterceptor(loggingInterceptor)
                    .build()

    fun retrofit(): Retrofit = Retrofit.Builder()
            .client(lastFmClient)
            .baseUrl(BaseUrl.BASE_REST_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}