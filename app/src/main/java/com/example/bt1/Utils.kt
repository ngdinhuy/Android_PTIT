package com.example.bt1

import android.text.Editable

class Utils {
    companion object{
        fun convertStringToEditable(s:String) =  Editable.Factory.getInstance().newEditable(s)
    }
}