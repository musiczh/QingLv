package com.example.qinglv.UserPackage.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qinglv.R;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvRegister;
    private EditText etUsername,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        tvRegister = findViewById(R.id.tv_rt_register);
        etUsername = findViewById(R.id.et_rt_username);
        etPassword = findViewById(R.id.et_rt_password);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.toString().trim();
                String password = etPassword.toString().trim();

            }
        });
    }
}
