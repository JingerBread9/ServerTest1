package com.example.servertest1

import android.os.Bundle
import android.view.View
import android.view.textclassifier.TextLanguage
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.*
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.kaonsoft.mobidoo_sdk_test.server.JsonRequest
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import javax.xml.transform.ErrorListener


class MainActivity : AppCompatActivity() {
    var textView: TextView? = null
    private lateinit var requestQueue :RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        findViewById<View>(R.id.button).setOnClickListener {
            JsonRequest.requestHttp()
        }

    }


}