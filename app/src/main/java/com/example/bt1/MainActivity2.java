package com.example.bt1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    EditText editText1,editText2;
    Button btnAdd;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
        setUpEvent();
    }

    private void init(){
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        btnAdd = findViewById(R.id.btnAdd);
        textViewResult = findViewById(R.id.textViewResult);
    }

    private void setUpEvent() {
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double firstNumber = Double.parseDouble(editText1.getText().toString());
                double secondNumber = Double.parseDouble(editText2.getText().toString());

                double result = firstNumber + secondNumber;
                textViewResult.setText(String.valueOf(result));
            }
        });
    }
}