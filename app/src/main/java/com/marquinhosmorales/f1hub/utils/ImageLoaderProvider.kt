package com.marquinhosmorales.f1hub.utils

import android.content.Context
import coil.ImageLoader
import coil.util.DebugLogger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object ImageLoaderProvider {
    private var imageLoader: ImageLoader? = null

    fun getImageLoader(context: Context): ImageLoader {
        return imageLoader ?: synchronized(this) {
            imageLoader ?: buildImageLoader(context.applicationContext).also { imageLoader = it }
        }
    }

    private fun buildImageLoader(context: Context): ImageLoader {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .header("User-Agent", "F1Hub/1.0")
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(loggingInterceptor)
            .build()

        return ImageLoader.Builder(context)
            .okHttpClient(okHttpClient)
            .crossfade(true)
            .logger(DebugLogger()) // Enable logging for debugging
            .respectCacheHeaders(false) // Ignore cache headers to ensure images load
            .build()
    }
}