package com.unreal.demotask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout passLayout, numLayout;
    TextInputEditText Number, Password;
    Button Login;
    TextView RegisterText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");

        Number = findViewById(R.id.num_inp_login);
        Password = findViewById(R.id.pass_inp_login);
        Login = findViewById(R.id.btn_login);
        RegisterText = findViewById(R.id.register_text);
        passLayout = findViewById(R.id.pass_inp_layout_login);
        numLayout = findViewById(R.id.number_inp_layout_login);
        DBHelper db = new DBHelper(LoginActivity.this);

        Login.setOnClickListener(view -> {
            String NUMBER_STRING = Number.getText().toString();
            String PASSWORD_STRING = Password.getText().toString();
            boolean checkNumberAndPass = db.checkNumberAndPassword(NUMBER_STRING, PASSWORD_STRING);
            boolean checkNumber = db.checkNumber(NUMBER_STRING);
            if (!checkNumberAndPass){
                Toast.makeText(LoginActivity.this, "Incorrect Number or Password!", Toast.LENGTH_SHORT).show();
            }
            else if (!checkNumber){
                Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent toHome = new Intent(this, MainActivity.class);
                startActivity(toHome);
                Toast.makeText(LoginActivity.this, "Login Successful 🙂", Toast.LENGTH_SHORT).show();
            }
        });

        RegisterText.setOnClickListener(view -> {
            Intent toRegister = new Intent(this, RegActivity.class);
            startActivity(toRegister);
        });

        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String passwordText = Password.getText().toString();
                if(passwordText.length() < 8)
                {
                    passLayout.setError("Minimum 8 Character Password");
                    passLayout.setHelperText("");
                }
                else if(!passwordText.matches(".*[A-Z].*"))
                {
                    passLayout.setError("Must Contain 1 Upper-case Character");
                    passLayout.setHelperText("");
                }
                else if(!passwordText.matches(".*[a-z].*"))
                {
                    passLayout.setError("Must Contain 1 Lower-case Character");
                    passLayout.setHelperText("");
                }
//                else if(!passwordText.matches(".*[@#\$%^&+=].*"))
//                {
//                    passLayout.setError("Must Contain 1 Special Character Character");
//                    passLayout.setHelperText("");
//                }
                else{
                    passLayout.setError("");
                    passLayout.setHelperText("Strong Password");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}

