package com.example.telnetclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RegisterActivity extends AppCompatActivity {
    private Button mBtnRegister;
    private EditText mEtUsername,mEtPassword,mEtRePassword;
    private static final String ipAddress = "192.168.43.124";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mBtnRegister = (Button)findViewById(R.id.btn_register);
        mEtUsername = (EditText)findViewById(R.id.et_username);
        mEtPassword = (EditText)findViewById(R.id.et_password);
        mEtRePassword = (EditText)findViewById(R.id.et_rePassword);

        mBtnRegister.setOnClickListener(new MyButtonOnClickListener());

    }
    class MyButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            String str = mEtPassword.getText().toString();
            Intent intent = new Intent();
            if(str.equals(mEtRePassword.getText().toString()))//当两次输入密码相同时
            {
                System.out.println("登录"+mEtUsername.getText().toString()+" "+mEtPassword.getText().toString());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Socket socketClient = new Socket(ipAddress,6666);
                            OutputStream os = socketClient.getOutputStream();
                            DataOutputStream dos = new DataOutputStream(os);
                            dos.writeUTF(mEtUsername.getText().toString()+" "+mEtPassword.getText().toString()+" Register");//向服务器传送登录账号和密码
                            //等待200s
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                }
                            },50);

                            InputStream is = socketClient.getInputStream();
                            DataInputStream dis = new DataInputStream(is);
                            String getStr = dis.readUTF();


                            if(getStr.equals("YES"))
                            {
                                intent.setClass(RegisterActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish(); //关闭当前LoginActivity，只是将活动推向后台，并没有立即释放内存，活动的资源并没有被清理
                            }
                            else if(getStr.equals("NO"))
                            {
                                System.out.println("用户名已存在");
                                Toast.makeText(getBaseContext(),"用户名已存在",Toast.LENGTH_SHORT).show();

                            }
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
            }
            else//两次密码不一致时
            {
                Toast.makeText(RegisterActivity.this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
            }
        }
    }
}