package com.huhx0015.androidbooster.injections.modules

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.huhx0015.androidbooster.network.interfaces.RetrofitInterface
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule (private val baseUrl: String) {

    /** CONSTANTS _______________________________________________________________________________ */

    companion object {
        private const val HTTP_CLIENT_CACHE = 10 * 1024 * 1024
    }

    /** MODULE METHODS _________________________________________________________________________  */

    @Provides
    @Singleton
    fun providesCache(application: Application): Cache {
        return Cache(application.cacheDir, HTTP_CLIENT_CACHE.toLong())
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(client)
                .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitInterface(retrofit: Retrofit): RetrofitInterface {
        return retrofit.create(RetrofitInterface::class.java)
    }
}