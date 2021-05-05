package com.example.telnetclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mBtnCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnCalculator = findViewById(R.id.btn_calculator);
        mBtnCalculator.setOnClickListener(new MyButtonOnClickListener());
    }
    class MyButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId())
            {
                case R.id.btn_calculator:
                    intent.setClass(MainActivity.this,CalculatorAcitvity2.class);
                    break;

            }
            startActivity(intent);


        }
    }
}