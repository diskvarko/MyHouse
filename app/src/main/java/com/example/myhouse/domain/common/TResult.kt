package com.example.myhouse.domain.common

sealed class TResult<out T>(
    open val data: T? = null,
    open val exception: Throwable? = null
) {
    data class Success<out T>(override val data: T) : TResult<T>()
    data class Error<out T>(override val exception: Throwable) : TResult<T>()
}