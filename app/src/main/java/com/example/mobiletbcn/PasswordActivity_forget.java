package com.example.mobiletbcn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobiletbcn.Controller.UserController;

public class PasswordActivity_forget extends AppCompatActivity {
    EditText username;
    Button reset, back_icon;
    UserController userController;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        userController = new UserController(database);
        setContentView(R.layout.activity_password_forget);
        username = (EditText) findViewById(R.id.username_reset);
        reset = (Button) findViewById(R.id.btnreset);
        back_icon = findViewById(R.id.back_icon6);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PasswordActivity_forget.this, MainActivity.class);
                startActivity(intent);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                boolean checkUser = userController.checkUserByUserName(user);
                if (checkUser == true) {
                    Intent intent = new Intent(getApplicationContext(), ResetActivity_forget.class);
                    intent.putExtra("username", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(PasswordActivity_forget.this, "User does not exists", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}