package com.example.telnetclient;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class CalculatorAcitvity2 extends AppCompatActivity {
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_0;
    private Button btn_equal;
    private Button btn_multi;
    private Button btn_divide;
    private Button btn_add;
    private Button btn_sub;
    private Button btn_clear;
    private Button btn_dot;
    private Button btn_left;
    private Button btn_right;
    private EditText et_input;
    private EditText et_output;
    private Button btn_del;
    private String formula="";
    private static final String ipAddress = "192.168.43.124";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_acitvity2);
        findView();
        setListeners();
    }
    public void findView()
    {
        btn_0 = (Button)findViewById(R.id.btn_0);
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_4 = (Button)findViewById(R.id.btn_4);
        btn_5 = (Button)findViewById(R.id.btn_5);
        btn_6 = (Button)findViewById(R.id.btn_6);
        btn_7 = (Button)findViewById(R.id.btn_7);
        btn_8 = (Button)findViewById(R.id.btn_8);
        btn_9 = (Button)findViewById(R.id.btn_9);
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_divide = (Button)findViewById(R.id.btn_divide);
        btn_multi = (Button)findViewById(R.id.btn_multi);
        btn_sub = (Button)findViewById(R.id.btn_sub);
        btn_clear =(Button)findViewById(R.id.btn_clear);
        btn_equal = (Button)findViewById(R.id.btn_equal);
        btn_dot = (Button)findViewById(R.id.btn_dot);
        et_input = (EditText)findViewById(R.id.et_input);
        et_output = (EditText)findViewById(R.id.et_output);
        btn_left = (Button)findViewById(R.id.btn_left);
        btn_right = (Button)findViewById(R.id.btn_right);
        btn_del = (Button)findViewById(R.id.btn_del);

    }
    public void setListeners(){
        OnClick onclick = new OnClick();
        btn_0.setOnClickListener(onclick);
        btn_1.setOnClickListener(onclick);
        btn_2.setOnClickListener(onclick);
        btn_3.setOnClickListener(onclick);
        btn_4.setOnClickListener(onclick);
        btn_5.setOnClickListener(onclick);
        btn_6.setOnClickListener(onclick);
        btn_7.setOnClickListener(onclick);
        btn_8.setOnClickListener(onclick);
        btn_9.setOnClickListener(onclick);
        btn_add.setOnClickListener(onclick);
        btn_multi.setOnClickListener(onclick);
        btn_sub.setOnClickListener(onclick);
        btn_dot.setOnClickListener(onclick);
        btn_divide.setOnClickListener(onclick);
        btn_equal.setOnClickListener(onclick);
        btn_clear.setOnClickListener(onclick);
        btn_left.setOnClickListener(onclick);
        btn_right.setOnClickListener(onclick);
        btn_del.setOnClickListener(onclick);
    }
    public class OnClick implements View.OnClickListener{

        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.btn_0:
                    formula +="0";
                    break;
                case R.id.btn_1:
                    formula +="1";
                    break;
                case R.id.btn_2:
                    formula +="2";
                    break;
                case R.id.btn_3:
                    formula +="3";
                    break;
                case R.id.btn_4:
                    formula +="4";
                    break;
                case R.id.btn_5:
                    formula +="5";
                    break;
                case R.id.btn_6:
                    formula +="6";
                    break;
                case R.id.btn_7:
                    formula +="7";
                    break;
                case R.id.btn_8:
                    formula +="8";
                    break;
                case R.id.btn_9:
                    formula +="9";
                    break;
                case R.id.btn_add:
                    formula +="+";
                    break;
                case R.id.btn_multi:
                    formula +="*";
                    break;
                case R.id.btn_divide:
                    formula +="/";
                    break;
                case R.id.btn_sub:
                    formula +="-";
                    break;
                case R.id.btn_dot:
                    formula +=".";
                    break;
                case R.id.btn_equal:
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try{
                                Socket socketClient = new Socket(ipAddress,6666);
                                OutputStream os = socketClient.getOutputStream();
                                DataOutputStream dos = new DataOutputStream(os);
                                dos.writeUTF("calc ");//向服务器传送登录账号和密码
                                //等待200s
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                },50);
                                dos.writeUTF(formula);
                                //等待200s
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                    }
                                },50);
                                InputStream is = socketClient.getInputStream();
                                DataInputStream dis = new DataInputStream(is);
                                String getStr = dis.readUTF();

                                et_output.setText(getStr);

                                //这些close???

                                dis.close();
                                dos.close();
                                socketClient.close();
                            }catch(IOException e)
                            {
                                e.printStackTrace();
                            }

                        }
                    }).start();
                    break;
                case R.id.btn_left:
                    formula+="(";
                    break;
                case R.id.btn_right:
                    formula+=")";
                    break;
                case R.id.btn_clear:
                    formula="";
                    break;
                case R.id.btn_del:
                    formula = formula.substring(0,formula.length()-1);
                    break;


            }
            System.out.println(formula);
            et_input.setText(formula);
        }
    }


}