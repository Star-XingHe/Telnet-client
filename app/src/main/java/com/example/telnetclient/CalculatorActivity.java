package com.example.telnetclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;

public class CalculatorActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_calculator);

        // 设置全屏，需要在setContentView之前调用
       // requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calculator);

    }

}