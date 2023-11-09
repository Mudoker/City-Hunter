package com.example.as1_cityhunter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    public final FileIOManipulator FILE_IO_MANIPULATOR = new FileIOManipulator(this);
    Button btnSignUp;
    Button btnLogin;
    TextView txtLogin, txtUsername, txtPassword, txtLoginDes;

    EditText edtUsername, edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btn_login);
        btnSignUp = findViewById(R.id.btn_signup);
        txtLogin = findViewById(R.id.txt_login);
        txtUsername = findViewById(R.id.txt_username);
        txtPassword = findViewById(R.id.txt_password);
        txtLoginDes = findViewById(R.id.txt_login_des);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);

        btnSignUp.setOnClickListener(v -> {
            if (btnSignUp.getText() != "Cancel") {
                txtLogin.setText("Sign Up");
                txtLoginDes.setText("Create new account");
                btnSignUp.setText("Cancel");
            } else {
                txtLogin.setText("Login");
                txtLoginDes.setText("Please login to continue");
                btnSignUp.setText("Sign Up");
            }
        });

        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in the required fields", Toast.LENGTH_SHORT).show();
            } else {
                // Login
                if (txtLogin.getText().toString().equals("Login")) {
                    // Check if user exists
                    if (FILE_IO_MANIPULATOR.isUserDataExist(edtUsername.getText().toString())) {
                        final String retrievedData = FILE_IO_MANIPULATOR.loadUserData(username);
                        User currentUser = User.fromString(retrievedData);

                        if (currentUser.getPassword().equals(password)) {
                            Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(this, "User not exist!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    System.out.println(txtLogin.getText());
                    User newUser = new User(username, password);

                    FILE_IO_MANIPULATOR.saveUserData(newUser);
                    Toast.makeText(this, "Register Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}