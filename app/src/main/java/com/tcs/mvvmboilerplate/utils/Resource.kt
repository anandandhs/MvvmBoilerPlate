package com.tcs.mvvmboilerplate.utils

sealed class Resource<T>(
    val data: T? = null,
    val responseCode:Int? = null,
    val errorMessage: String? = null
) {
    class Success<T>(data: T,responseCode:Int?=null) : Resource<T>(data,responseCode)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class DataError<T>(errorMessage: String) : Resource<T>(errorMessage = errorMessage)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data,responseCode=$responseCode]"
            is DataError -> "Error[exception=$errorMessage]"
            is Loading<T> -> "Loading"
        }
    }
}
