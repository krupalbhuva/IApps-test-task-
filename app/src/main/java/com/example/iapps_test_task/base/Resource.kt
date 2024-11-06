package com.example.iapps_test_task.base

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val header: Map<String, String>? = null,
) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(message: String? = null, data: T? = null): Resource<T>(data, message)
}