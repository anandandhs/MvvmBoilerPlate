package com.tcs.mvvmboilerplate.data.remote

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceGenerator @Inject constructor(retrofit: Retrofit) {
    private val myRetrofit: Retrofit = retrofit

    fun <S> createService(serviceClass: Class<S>): S {
        return myRetrofit.create(serviceClass)
    }
}