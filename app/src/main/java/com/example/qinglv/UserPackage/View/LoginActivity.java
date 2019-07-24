package com.example.qinglv.UserPackage.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.qinglv.R;
import com.example.qinglv.UserPackage.Contract.ILoginContract;

public class LoginActivity extends AppCompatActivity implements ILoginContract.View {

    private EditText etUsername,etPassword;
    private TextView tvLogin,tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void loginError(String msg) {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void registerError() {

    }
}
