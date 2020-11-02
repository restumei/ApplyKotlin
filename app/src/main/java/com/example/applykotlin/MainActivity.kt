package com.example.applykotlin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.applykotlin.Util.DATA_KEY
import com.example.applykotlin.Util.invisible
import com.example.applykotlin.Util.isEmpty
import com.example.applykotlin.Util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val total  = { gaji: Int, tunjangan: Int -> gaji + tunjangan }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//Calculate Tunjangan
        etTunjangan.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                etTotal.setText(total(
                    etGaji.text.toString().toInt(),
                    p0.toString().toInt()).toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })


        etExpect.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

                tvPersentase.text = countPercent(
                    total,
                    p0.toString().toInt(),
                    etGaji.text.toString().toInt(),
                    etTunjangan.text.toString().toInt()
                )
            }
        })

//SWitch Button
        swPekerjaan.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                layoutPekerjaan.visible()
            }else{
                layoutPekerjaan.invisible()
            }
        }

//Validate Empty when submit
        btnSubmit.setOnClickListener {
            var succes = true

            if (etNama.isEmpty()) {
                succes = false
                etNama.error = "Masukan Nama"
            }
            if (etEmail.isEmpty()) {
                succes = false
                etEmail.error = "Masukan Alamat Email"
            }
            if (etNoHp.isEmpty()) {
                succes = false
                etNoHp.error = "Masukan Nomor HP"
            }
            if (etUniv.isEmpty()) {
                succes = false
                etUniv.error = "Masukan Nama Universitas"
            }
            if (etJurusan.isEmpty()) {
                succes = false
                etJurusan.error = "Masukan Nomor HP"
            }
            if (etIPK.isEmpty()){
                succes = false
                etIPK.error = "Masukan IPK"
            }

//Validate when checked
            if (swPekerjaan.isChecked){
                if (etPerusahaan.isEmpty()){
                    succes = false
                    etPerusahaan.error = "Masukan Nama Perusahaan"
                }
                if (etGaji.isEmpty()){
                    succes = false
                    etGaji.error = "Masukan Nominal Gaji"
                }
                if (etTunjangan.isEmpty()){
                    succes = false
                    etTunjangan.error = "Masukan Tunjangan atau input 0"
                }
            }

//Parsing Gson to Json IF SUCCESS
            if (succes) {
                val gson = Gson()

                val data = Data(
                    etNama.text.toString(),
                    etEmail.text.toString(),
                    etNoHp.text.toString(),
                    etUniv.text.toString(),
                    etJurusan.text.toString(),
                    etIPK.text.toString(),
                    etGaji.text.toString(),
                    etTunjangan.text.toString(),
                    etTunjangan.text.toString(),
                    etTotal.text.toString(),
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

    fun countPercent(total:  (Int, Int) -> Int, expected: Int, gaji: Int, tunjangan: Int): String {
        val result = ((( expected - total(gaji,tunjangan)) * 100) / total(gaji, tunjangan) )
        return "$result% Lebih Besar"
    }

}



