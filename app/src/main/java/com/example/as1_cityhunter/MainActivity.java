package com.example.as1_cityhunter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button btnSignUp;
    TextView txtLogin, txtUsername, txtPassword, txtLoginDes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignUp = findViewById(R.id.btn_signup);
        txtLogin = findViewById(R.id.txt_login);
        txtUsername = findViewById(R.id.txt_username);
        txtPassword = findViewById(R.id.txt_password);
        txtLoginDes = findViewById(R.id.txt_login_des);

        btnSignUp.setOnClickListener(v -> {
            if (btnSignUp.getText() != "Go back") {
                txtLogin.setText("Sign Up");
                txtLoginDes.setText("Create new account");
                btnSignUp.setText("Go back");
            } else {
                txtLogin.setText("Login");
                txtLoginDes.setText("Please login to continue");
                btnSignUp.setText("Sign Up");
            }
        });
    }
}