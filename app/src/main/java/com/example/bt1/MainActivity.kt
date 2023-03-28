package com.example.bt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var edt1 : EditText
    lateinit var edt2 : EditText
    lateinit var btnAdd : Button
    lateinit var txtResult : TextView
//    lateinit var btnLogin : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buoi_4_1)
        initView()
        setUpEvent()
    }

    private fun setUpEvent() {
//       btnAdd.setOnClickListener{
//           val x1 = edt1.text.toString() as Double
//           val x2 = edt2.text.toString() as Double
//           Toast.makeText(this,(x1+x2).toString(),Toast.LENGTH_SHORT).show()
//       }
//        btnLogin.setOnClickListener(this)
    }

    private fun initView() {
        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
//        btnLogin = findViewById(R.id.btnLogin)
    }

    override fun onClick(p0: View) {
        if(p0.id == R.id.btnLogin){
            if(edt1.text.toString()=="huy" && edt2.text.toString() =="147"){
                Toast.makeText(this,"SUCCESS",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"FAIL",Toast.LENGTH_SHORT).show()
            }
        }
    }


}