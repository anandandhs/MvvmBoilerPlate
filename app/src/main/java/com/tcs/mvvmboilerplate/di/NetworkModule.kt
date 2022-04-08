package com.tcs.mvvmboilerplate.di

import android.content.Context
import com.tcs.mvvmboilerplate.BuildConfig
import com.tcs.mvvmboilerplate.data.remote.Network
import com.tcs.mvvmboilerplate.data.remote.NetworkConnectivity
import com.tcs.mvvmboilerplate.utils.Constants
import com.tcs.mvvmboilerplate.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkUtility(@ApplicationContext context: Context): NetworkConnectivity =
        Network(context)

    @Provides
    @Singleton
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            loggingInterceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder().apply {
            header(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_VAL)
        }.build()
        chain.proceed(request = request)
    }

    @Provides
    @Singleton
    fun provideHttpBuilder(
        logger: HttpLoggingInterceptor,
        headerInterceptor: Interceptor
    ): OkHttpClient.Builder = OkHttpClient.Builder().apply {
        addInterceptor(headerInterceptor)
        addInterceptor(logger)
        connectTimeout(Constants.TIMEOUT_CONNECT.toLong(), TimeUnit.SECONDS)
        readTimeout(Constants.TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
    }

    @Provides
    @Singleton
    fun provideHttpClient(okHttpBuilder: OkHttpClient.Builder): OkHttpClient = okHttpBuilder.build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        converterFactory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(client)
            addConverterFactory(converterFactory)
        }.build()

}