package com.example.android_city_hunter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AuthenticationView extends AppCompatActivity {
    private final FileIOManipulator fileIOManipulator = new FileIOManipulator(this);
    private Button btnSignUp;
    private TextView txtLogin;
    private TextView txtLoginDes;
    private EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);

        // initialize view
        btnSignUp = findViewById(R.id.btn_signup);
        Button btnLogin = findViewById(R.id.btn_login);
        txtLogin = findViewById(R.id.txt_login);
        txtLoginDes = findViewById(R.id.txt_login_des);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);

        btnSignUp.setOnClickListener(v -> {
            if ("Cancel".contentEquals(btnSignUp.getText())) {
                updateUiOnCancel();
            } else {
                updateUiOnSignUp();
            }
        });

        btnLogin.setOnClickListener(v -> handleLogin());
    }

    private void updateUiOnCancel() {
        txtLogin.setText("Login");
        txtLoginDes.setText("Please login to continue");
        btnSignUp.setText("Sign Up");
    }

    private void updateUiOnSignUp() {
        txtLogin.setText("Sign Up");
        txtLoginDes.setText("Create new account");
        btnSignUp.setText("Cancel");
    }

    private void handleLogin() {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            makeToast("Please fill in the required fields");
        } else {
            // Login
            if ("Login".contentEquals(txtLogin.getText())) {
                Thread newThread = new Thread(() -> {
                    User.CURRENT_USER = processLogin(username, password);
                    runOnUiThread(() -> {
                        if (User.CURRENT_USER != null) {
                            Intent intent;
                            if (User.CURRENT_USER.getFirstTimeLogin()) {
                                intent = new Intent(this, RegistrationForm.class);
                            } else {
                                intent = new Intent(this, MainActivity.class);
                            }

                            makeToast("Login Successfully ");

                            startActivity(intent);
                            finish();
                        } else {
                            makeToast("Login Failed");
                        }
                    });
                });
                newThread.start();
            } else {
                Thread newThread = new Thread(() -> runOnUiThread(() -> {
                    if (processSignUp(username, password)) {
                        makeToast("Register Successfully");
                    } else {
                        makeToast("Register Failed");
                    }
                }));
                newThread.start();
            }
        }
    }

    private User processLogin(String username, String password) {
        if (fileIOManipulator.isFileExist(username)) {
            final String retrievedData = fileIOManipulator.load(username);

            System.out.println(retrievedData);
            User currentUser = User.fromString(retrievedData);

            if  (currentUser.getPassword().equals(password)) {
                return currentUser;
            }
        }
        return null;
    }

    private boolean processSignUp(String username, String password) {
        if (fileIOManipulator.isFileExist(username)) {
            return false;
        } else {
            User newUser = new User(username, password);
            fileIOManipulator.save(newUser.getUsername(), newUser.toString());
            return true;
        }
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}