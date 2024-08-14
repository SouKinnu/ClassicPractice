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

/**
 * @Author : SongJin yu
 * @Email : kinnusou@gmail.com
 * @Date : on 2024/5/20 15:30.
 * @Description : 一个用于网络请求的客户端类，封装了 Retrofit 和 OkHttp 的配置。
 *
 * @param baseUrl 请求的基础 URL，默认为全局配置的 BASE_URL。
 * @param timeout 请求的超时时间，默认为全局配置的 TIME_OUT。
 */
class HttpClient(
    private val baseUrl: String = BASE_URL,
    private val timeout: Long = TIME_OUT
) {

    companion object {
        // 懒加载的 OkHttpClient 实例，用于执行网络请求
        private val okHttpClient: OkHttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpClient.Builder()
                // 设置请求超时时间
                .callTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                // 添加日志拦截器，设置日志级别为 HEADERS，方便调试
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .build()
        }
    }

    // 懒加载的 ApiService 实例，用于访问 API 接口
    val apiService: ApiService by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(
                // 添加 Gson 转换器，用于解析 JSON 数据
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient() // 设置 Gson 为宽松模式，允许一些不规范的 JSON 格式
                        .create()
                )
            )
            // 添加 RxJava3 的调用适配器，用于将网络请求转换为 RxJava3 的 Observable
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            // 设置 OkHttpClient 为 Retrofit 的客户端
            .client(okHttpClient)
            .build()
            // 创建 ApiService 实例，用于调用 API 接口
            .create(ApiService::class.java)
    }
}