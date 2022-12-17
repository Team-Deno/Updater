package com.example.updater

import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.webkit.CookieManager
import android.webkit.URLUtil
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val btnDownload=findViewById<MaterialButton>(R.id.btnDownload)
        btnDownload.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://github.com/SahilSonar/ota_config/releases/download/v1.0.0/")
                .build()

            val myAPI = retrofit.create(MyAPI::class.java)

            val call: Call<ResponseBody?>? = myAPI.getFile()
            call!!.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>?, response: retrofit2.Response<ResponseBody>) {
                    if (response.isSuccessful()) {
                        // Do something with the response
                    } else {
                        // Handle the error
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
                    // Handle the failure
                }
            })
            val url="https://github.com/SahilSonar/ota_config/releases/download/v1.0.0/msm8953_64-ota-eng.cpu-29.zip"
            val request = DownloadManager.Request(Uri.parse(url))
            var title= URLUtil.guessFileName(url,null,null)
            request.setTitle(title)
            request.setDescription("Downloading file please wait....")
            val cookie= CookieManager.getInstance().getCookie(url)
            request.addRequestHeader("cookie",cookie)
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title)

            val downloadManager:DownloadManager= getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)


            Toast.makeText(this,"Download started", Toast.LENGTH_SHORT).show()
        }
    }
}