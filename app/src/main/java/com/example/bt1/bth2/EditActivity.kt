package com.example.bt1.bth2

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.bt1.R
import java.util.*
import kotlin.collections.ArrayList

class EditActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etNoiDung: EditText
    lateinit var etNgay: EditText
    lateinit var spinner: Spinner
    lateinit var checkBox: CheckBox
    lateinit var btnEdit: Button
    var sqlHelper: SQLHelper? = null
    var id = 0
    val list = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        initView()
        loadArgument()
    }

    private fun loadArgument() {
        val mIntent = intent
        val item = mIntent.getSerializableExtra("item") as JobModel
        id = item.id!!
        etName.setText(item.ten)
        etNoiDung.setText(item.noiDung)
        etNgay.setText(item.ngay)
        for(i in 0 until list.size){
            if (item.tinhTrang == list[i]){
                spinner.setSelection(i)
                break
            }
        }

        checkBox.isChecked = item.congTac == "Mot minh"

    }

    private fun initView() {
        etName = findViewById(R.id.et_name)
        etNoiDung = findViewById(R.id.et_noi_dung)
        etNgay = findViewById(R.id.et_ngay)
        spinner = findViewById(R.id.spinner)
        checkBox = findViewById(R.id.checkbox)
        btnEdit = findViewById(R.id.btn_edit)
        list.add("Chua thuc hien")
        list.add("Dang thuc hien")
        list.add("Hoan thanh")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
        spinner.adapter = adapter
        sqlHelper = SQLHelper(this)
        etNgay.setOnClickListener{
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                // Display Selected date in textbox
                etNgay.setText("$dayOfMonth/${monthOfYear+1}/$year")

            }, year, month, day)
            dpd.show()


            //Timepicker Dialog
//                val mTimePicker: TimePickerDialog
//                val mcurrentTime = Calendar.getInstance()
//                val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
//                val minute = mcurrentTime.get(Calendar.MINUTE)
//
//                mTimePicker = TimePickerDialog(this, { view, hourOfDay, minute ->
//                    etNgay.setText("$hour:$minute")
//                }
//                    , hour, minute, false)
//                mTimePicker.show()
        }

        btnEdit.setOnClickListener{
            val job = JobModel(id,
                etName.text.toString(), etNoiDung.text.toString(), etNgay.text.toString(), list[spinner.selectedItemPosition], if(checkBox.isChecked) "Mot minh" else "Cong tac")
            sqlHelper?.edit(job)
            this.finish()
        }
    }
}