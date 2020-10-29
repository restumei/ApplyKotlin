package com.example.applykotlin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.applykotlin.Util.DATA_KEY
import com.example.applykotlin.Util.invisible
import com.example.applykotlin.Util.textEmpty
import com.example.applykotlin.Util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        etTunjangan.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val total  = { gaji: Int, tunjangan: Int -> gaji + tunjangan }
                etTotal.setText(total(
                    etGaji.text.toString().toInt(),
                    p0.toString().toInt()).toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        btnSubmit.setOnClickListener {
            var succes = true

            if (etNama.textEmpty()) {
                succes = false
                etNama.error = "Masukan Nama"
            }
            if (etEmail.textEmpty()) {
                succes = false
                etEmail.error = "Masukan Email"
            }
            if (etNoHp.textEmpty()) {
                succes = false
                etNoHp.error = "Masukan Nomor HP"
            }

           


            if (succes) {
                val gson = Gson()
                val data = Data(
                    etNama.text.toString(),
                    etEmail.text.toString(),
                    etNoHp.text.toString(),
                    if(rbMale.isChecked){"pria"}else{"wanita"}

                )

                val intent = Intent(this, FinishActivity::class.java)
                data?.let {
                    intent.putExtra(DATA_KEY, gson.toJson(it))
                }
                startActivity(intent)
            }

        }

    }

}



