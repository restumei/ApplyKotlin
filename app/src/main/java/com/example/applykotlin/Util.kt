package com.example.applykotlin

import android.os.Message
import android.view.View
import android.widget.EditText

 object Util {
     val DATA_KEY = "datakey"

    fun EditText.textEmpty(): Boolean {
        return text.toString().trim().isEmpty()
    }

     fun View.visible(){
        visibility =  View.VISIBLE
     }
     fun View.invisible(){
        visibility = View.GONE
     }


}