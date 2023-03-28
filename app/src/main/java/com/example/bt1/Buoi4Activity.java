package com.example.bt1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Buoi4Activity extends AppCompatActivity {

    private TextView kq;
    private EditText edit1, edit2;
    private Button btAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buoi_4_1);
        initView();
    }

    private void initView() {
        kq = findViewById(R.id.txtResult);
        edit1 = findViewById(R.id.edt1);
        edit2 = findViewById(R.id.edt2);
        btAdd = findViewById(R.id.btnADD);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nn1 = edit1.getText().toString();
                String nn2 = edit2.getText().toString();
                double kk = Double.parseDouble(nn1)+Double.parseDouble(nn2);
                Log.e("ok",String.valueOf(kk));
                kq.setText(String.valueOf(kk));
            }
        });
    }

}