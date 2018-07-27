package com.example.n007.signage.Network

/**
 * Created by N007 on 17/7/2561.
 */
interface ApiResponse {
    fun apiResponsePostProcessing(response: String, apiCode: Int)
    fun networkError(apiCode: Int)
    fun responseError(responseError: String, apiCode: Int)
    fun responseError(responseError: String, successedUrlList: ArrayList<Int>, apiCode: Int)
}