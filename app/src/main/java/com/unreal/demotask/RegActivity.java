package com.unreal.demotask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegActivity extends AppCompatActivity {
    TextInputLayout passLayout, numLayout;
    EditText Name, Number;
    TextInputEditText Password, PasswordAgain;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        getSupportActionBar().setTitle("Register");
        Name = findViewById(R.id.name_inp_reg);
        Number = findViewById(R.id.num_inp_reg);
        Password = findViewById(R.id.pass_inp_reg);
        PasswordAgain = findViewById(R.id.confirm_pass_inp_reg);
        Register = findViewById(R.id.btn_register);
        passLayout = findViewById(R.id.pass_inp_layout);
        numLayout = findViewById(R.id.number_inp_layout);
        DBHelper db = new DBHelper(RegActivity.this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NAME_STRING = Name.getText().toString();
                String NUMBER_STRING = Number.getText().toString();
                String PASSWORD_STRING = Password.getText().toString();
                String PASSWORD_AGAIN_STRING = PasswordAgain.getText().toString();

                if (NAME_STRING.isEmpty() || NUMBER_STRING.isEmpty() || PASSWORD_STRING.isEmpty() || PASSWORD_AGAIN_STRING.isEmpty()){
                    Toast.makeText(RegActivity.this, "Please Enter all Details", Toast.LENGTH_SHORT).show();
                }
                else if(!PASSWORD_STRING.equals(PASSWORD_AGAIN_STRING)){
                    Toast.makeText(RegActivity.this, "Please Enter same password", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean checkIfInserted = db.createData(NAME_STRING, NUMBER_STRING, PASSWORD_STRING);
                    if (checkIfInserted){
                        Toast.makeText(RegActivity.this, "Registered Successfully :)", Toast.LENGTH_SHORT).show();
                        Intent toLogin = new Intent(RegActivity.this, LoginActivity.class);
                        startActivity(toLogin);
                    }else{
                        Toast.makeText(RegActivity.this, "Registration Failed :(", Toast.LENGTH_SHORT).show();
                    }
                }
            }
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

    public void toList(View view) {
        Intent toUserList = new Intent(RegActivity.this, MainActivity.class);
        startActivity(toUserList);
    }
}