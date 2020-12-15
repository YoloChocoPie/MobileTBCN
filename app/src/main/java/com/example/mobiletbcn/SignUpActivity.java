package com.example.mobiletbcn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiletbcn.Controller.UserController;
import com.example.mobiletbcn.model.User;

public class SignUpActivity extends AppCompatActivity {

    EditText etFullName, etRole, etUserName, etPassword, etRepeatPassword;
    Button back_icon;


    Database database;
    UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        database = new Database(this, "ManagementBook.sqlite", null, 1);


//        database.queryData("DROP TABLE Book");
//        database.queryData("DROP TABLE User");
//        database.queryData("DROP TABLE Booking");





        userController = new UserController(database);
        mapping();

    }

    public void mapping() {
        etFullName = findViewById(R.id.et_full_name);
        etRole = findViewById(R.id.et_role);
        etUserName = findViewById(R.id.et_user_name);
        etPassword = findViewById(R.id.et_password);
        etRepeatPassword = findViewById(R.id.et_repeat_password);
    }

    boolean validateInput(){
        if (etFullName.getText().toString().equals("")) {
            etFullName.setError("Please Enter Full Name");
            return false;
        }
        if (etRole.getText().toString().equals("")) {
            etRole.setError("Please Enter Role");
            return false;
        }
        if (etUserName.getText().toString().equals("")) {
            etUserName.setError("Please Enter UserName");
            return false;
        }
        if (etPassword.getText().toString().equals("")) {
            etPassword.setError("Please Enter Password");
            return false;
        }
        if (etRepeatPassword.getText().toString().equals("")) {
            etRepeatPassword.setError("Please Enter Repeat Password");
            return false;
        }
        if (!etPassword.getText().toString().equals(etRepeatPassword.getText().toString())) {
            etRepeatPassword.setError("Password does not match");
            return false;
        }
        return true;
    }

    public void performSignUp(View view){
        if (validateInput())
        {
            User user = new User();
            user.setFullName(etFullName.getText().toString());
            user.setRole(etRole.getText().toString());
            user.setUserName(etUserName.getText().toString());
            user.setPassword(etPassword.getText().toString());
            user.setcfpass(etRepeatPassword.getText().toString());

            userController.addnewuser(user);
            Intent intent = new Intent(this, Welcom_page.class);
            startActivity(intent);
        }





//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//        etFullName = findViewById(R.id.et_full_name);
//        etRole = findViewById(R.id.et_role);
//        etUserName = findViewById(R.id.et_user_name);
//        etPassword = findViewById(R.id.et_password);
//        etRepeatPassword = findViewById(R.id.et_repeat_password);
//        back_icon = findViewById(R.id.back_icon);
//        back_icon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SignUpActivity.this,Welcom_page.class);
//                startActivity(intent);
//            }
//        });
//
//    }

//    public void performSignUp (View v) {
//        if (validateInput()) {
//
//            String fullName = etFullName.getText().toString();
//            String role = etRole.getText().toString();
//            String userName = etUserName.getText().toString();
//            String password = etPassword.getText().toString();
//            String repeatPassword = etRepeatPassword.getText().toString();
//            userDb.createUser(fullName, role, userName, password);
//            Toast.makeText(this,"Sign Up Success",Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(SignUpActivity.this, Welcom_page.class);
//            startActivity(intent);
//
//
//
//
//        }
//    }
}}