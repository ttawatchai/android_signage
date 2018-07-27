package com.example.n007.signage.Network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import okhttp3.*
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by N007 on 17/7/2561.
 */
object NetworkUtility {

    private val MEDIA_TYPE_PNG = MediaType.parse("image/png")
    private val MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg")
    private val MEDIA_TYPE_JPG = MediaType.parse("image/jpg")
    private val JSON = MediaType.parse("application/json; charset=utf-8")
    fun isConnectivityAvailable(context: Context?): Boolean {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    @Throws(IOException::class)
    fun getOkHttpClient(request: Request): String {
        val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()

        val response = client.newCall(request).execute()
        return response.body()!!.string()
    }

    @Throws(IOException::class)
    fun getResponseWithGet(url: String?): String {
        Log.d("Url--->", url)

        val request = Request.Builder()
                .url(url)
                .build()

        return getOkHttpClient(request)
    }

    @Throws(IOException::class)
    fun getResponseWithPost(url: String?, postDataParams: HashMap<String, String>): String {

        Log.d("Url--->", url)

        val fromBodyBuilder = FormBody.Builder()
        for (entry in postDataParams) {
            Log.d("param--->", entry.key + ":" + entry.value)
            fromBodyBuilder.add(entry.key, entry.value)
        }

        val request = Request.Builder()
                .url(url)
                .post(fromBodyBuilder.build())
                .build()

        return getOkHttpClient(request)

    }

    @Throws(IOException::class)
    fun getResponseWithPostImages(url: String?, postDataParams: HashMap<String, String>, files: ArrayList<File>): String {

        Log.d("Url--->", url)
        val multipartBuilder = MultipartBody.Builder()
        multipartBuilder.setType(MultipartBody.FORM)

        for (entry in postDataParams) {
            Log.d("param--->", entry.key + ":" + entry.value)
            multipartBuilder.addFormDataPart(entry.key, entry.value)
        }
        var index = 0
        for (files_details in files) {
            ++index
            Log.d("file--->", "(" + index + ") fileName:" + files_details.name + " filepath:" + files_details.path)
            multipartBuilder.addFormDataPart("image" + index, files_details.name, RequestBody.create(MEDIA_TYPE_PNG, files_details))
        }
        val request = Request.Builder()
                .url(url)
                .post(multipartBuilder.build())
                .build()

        return getOkHttpClient(request)

    }

    @Throws(IOException::class)
    fun getResponseWithPostJson(url: String?, jsonBody: String?): String {

        Log.d("Url--->", url)

        Log.d("jsonBody--->", jsonBody)
        val body = RequestBody.create(JSON, jsonBody)

        val request = Request.Builder()
                .url(url)
                .post(body)
                .build()

        return getOkHttpClient(request)
    }
}