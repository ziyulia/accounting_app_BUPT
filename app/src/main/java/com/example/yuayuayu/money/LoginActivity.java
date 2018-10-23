package com.example.yuayuayu.money;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button Signin;
    Button Signup;
    UserSQLHelper helper;
    EditText User_name;
    EditText User_password;
    String _name,_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Signin=(Button) findViewById(R.id.signin);
        Signup=(Button) findViewById(R.id.zhuce);
        User_name=(EditText) findViewById(R.id.yonghuming);
        User_password=(EditText)findViewById(R.id.mima);
        helper=new UserSQLHelper(this);
        Signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(LoginActivity.this,SignActivity.class);
               startActivity(intent);
           }
       });
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    _name=User_name.getText().toString();
                    _password=User_password.getText().toString();

                 String b=helper.searchDatabase(_name);
                    if(b.equals(_password))
                    {
                        Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                        final Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("user", _name);
                        intent.putExtras(bundle);
                        CircularAnim.init(500,300, R.drawable.mainmask2);
                        CircularAnim.fullActivity(LoginActivity.this, v)
                                .go(new CircularAnim.OnAnimationEndListener() {
                                    @Override
                                    public void onAnimationEnd() {
                                        startActivity(intent);
                                    }
                                });
                    }
                    else {


                        Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                    }

                }


        });

    }
}
