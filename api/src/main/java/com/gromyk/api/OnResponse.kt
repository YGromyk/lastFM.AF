package com.gromyk.api

interface OnResponse<T> {
    fun onSuccess(responseBody: T)
    fun onError(exception: Exception)
}