package com.example.telnetclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.ContactsContract;
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
import java.net.UnknownHostException;

public class LoginActivity extends AppCompatActivity {

    private EditText mEtUserName;
    private EditText mEtPassWord;
    private Button mBtnLogin;
    private Button mBtnRegister;
    private static final String ipAddress = "192.168.43.124";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtPassWord = (EditText)findViewById(R.id.et_password);
        mEtUserName = (EditText)findViewById(R.id.et_username);
        mBtnRegister = (Button)findViewById(R.id.btn_register);
        mBtnLogin = (Button)findViewById(R.id.btn_login);

        mBtnLogin.setOnClickListener(new MyButtonOnClickListener());
        mBtnRegister.setOnClickListener(new MyButtonOnClickListener());

    }
    class MyButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            switch (v.getId()){
                case R.id.btn_login:
                    try{
                        /*if (android.os.Build.VERSION.SDK_INT > 9) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                        }*/
                        //端口号6666，自己选择
                        System.out.println(mEtUserName.getText().toString()+" "+mEtPassWord.getText().toString());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try{
                                    Socket socketClient = new Socket(ipAddress,6666);
                                    OutputStream os = socketClient.getOutputStream();
                                    DataOutputStream dos = new DataOutputStream(os);
                                    dos.writeUTF(mEtUserName.getText().toString()+" "+mEtPassWord.getText().toString()+" Login");//向服务器传送登录账号和密码
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
                                        intent.setClass(LoginActivity.this,MainActivity.class);
                                        startActivity(intent);
                                        finish(); //关闭当前LoginActivity，只是将活动推向后台，并没有立即释放内存，活动的资源并没有被清理
                                    }
                                    else if(getStr.equals("NO"))
                                    {
                                        System.out.println("用户名或密码错误");
                                        Toast.makeText(getBaseContext(),"密码错误",Toast.LENGTH_LONG).show();

                                    }
                                    //这些close???

                                    dis.close();
                                    dos.close();
                                    socketClient.close();

                                }catch (IOException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        }).start();


                    } catch (Exception e) {
                        System.out.println("异常");
                        //Toast在子线程中不显示
                        //Toast.makeText(LoginActivity.this,"登录异常",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    break;
                case R.id.btn_register:
                    intent.setClass(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent);
                    finish();
                    break;

            }
        }
    }
}
