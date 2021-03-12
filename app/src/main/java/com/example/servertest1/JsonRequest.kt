package com.kaonsoft.mobidoo_sdk_test.server

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

object JsonRequest {
    private var a: String = ""
    fun requestHttp() {

        CoroutineScope(Dispatchers.Main).launch {

            var job: Job = CoroutineScope(Dispatchers.IO).launch {
                try {
                    Log.d("test", "시도하기.")
                    val url = URL("http://vpn.mobidoo.co.kr:20071/api/v1/init")
                    var conn = (url.openConnection() as HttpURLConnection).apply {
                        requestMethod = "POST"
                        setRequestProperty("Content-Type", "application/json")
                        setRequestProperty("charset", "utf-8")
                        setRequestProperty("Connection", "keep-alive")
                    }

                    // Json Post
                    var json = JSONObject()
                    json.put("apiKey", "INDONESIA_HIGO_EXAMPLE")
                    json.put("deviceName", "KAONSOFT_DEV1")
                    json.put("deviceVendor", "KAONSOFT_DEV1")
                    json.put("osType", "KAONSOFT_DEV1")
                    json.put("osVersion", "KAONSOFT_DEV1")
                    json.put("sdkVersion", "KAONSOFT_DEV1")

                    Log.d("test", json.toString())
                    val osw = OutputStreamWriter(conn.outputStream)
                    osw.write(json.toString())
                    osw.flush()

                    if (conn.responseCode == HttpURLConnection.HTTP_OK) {
                        Log.d("test", "연결 성공")
                        val streamReader = InputStreamReader(conn.inputStream)
                        val buffered = BufferedReader(streamReader)

                        val content = StringBuilder()
                        while (true) {
                            val line = buffered.readLine() ?: break
                            content.append(line)
                        }


                        Log.d("test", "값:$content")
                    }

                } catch (e: Exception) {
                    Log.d("test", e.toString())
                    Log.d("test", "연결 실패 !!")
                }
            }

            job.join()

        }
    }
}