package com.example.footballapp.data.network

import com.example.footballapp.data.network.interceptor.NetworkInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://www.mocky.io/"

fun provideUserApi(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun provideGsonConvertorFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(NetworkInterceptor()).build()
}

fun provideRetrofit(): Retrofit {
    return retrofit {
        baseUrl(BASE_URL)
        addConverterFactory(provideGsonConvertorFactory())
    }
}

fun retrofit(block: Retrofit.Builder.() -> Unit): Retrofit {
    return Retrofit.Builder().apply(block).build()
}