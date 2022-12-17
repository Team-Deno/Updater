package com.example.updater

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


const val BASE_URL="https://raw.githubusercontent.com/SahilSonar/ota_config"
interface MyAPI {
    @GET("/msm8953_64-ota-eng.cpu-29.zip")
    open fun getFile(): Call<ResponseBody?>?

}