package com.example.applykotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.applykotlin.Util.DATA_KEY
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {

    var dataCandidate: Data? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val gson = Gson()

        val newdata = intent.getStringExtra(DATA_KEY)

        newdata?.let {
            dataCandidate = gson.fromJson(it, Data::class.java)
        }

        tvNama.text = dataCandidate?.nama ?: ""


    }
}