package com.song.httplibrary

import com.song.httplibrary.utils.TIME_OUT
import com.google.gson.GsonBuilder
import com.song.httplibrary.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient(
    private val baseUrl: String = BASE_URL,
    private val timeout: Long = TIME_OUT
) {

    companion object {
        private val okHttpClient: OkHttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpClient.Builder()
                .callTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .build()
        }
    }

    val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            )
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}