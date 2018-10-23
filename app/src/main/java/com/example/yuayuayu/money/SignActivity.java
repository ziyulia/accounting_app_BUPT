package com.example.yuayuayu.money;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignActivity extends AppCompatActivity {
    Button signup;
    EditText User_name;
    EditText User_Password,User_Password2;
    String _name,_password,_password2;
    UserSQLHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        signup=(Button) findViewById(R.id.signup);
        User_name=(EditText) findViewById(R.id.username);
        User_Password=(EditText) findViewById(R.id.pass1);
        User_Password2=(EditText) findViewById(R.id.pass2);
        helper=new UserSQLHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _name=User_name.getText().toString();
                _password=User_Password.getText().toString();
                _password2= User_Password2.getText().toString();
                if(!_password.equals(_password2)){
                    Toast t=  Toast.makeText(SignActivity.this,"密码不匹配",Toast.LENGTH_LONG);
                    t.show();
                }
                else {
                    helper.insertDatabase(_name,_password);
                    Toast t=  Toast.makeText(SignActivity.this,"注册成功",Toast.LENGTH_LONG);
                    t.show();
                    Intent intent=new Intent(SignActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
