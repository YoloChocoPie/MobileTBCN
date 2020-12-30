package com.example.mobiletbcn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiletbcn.Controller.UserController;

public class ResetActivity_forget extends AppCompatActivity {
    TextView username;
    EditText pass,repass;
    Button confirm,back;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_forget);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        username = (TextView)findViewById(R.id.username_reset_text);
        pass = (EditText)findViewById(R.id.password_reset);
        repass = (EditText)findViewById(R.id.repassword_reset);
        confirm = (Button)findViewById(R.id.btnconfirm);
        database = new Database(this, "ManagementBook.sqlite", null, 1);


        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));
        back = findViewById(R.id.back_icon7);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetActivity_forget.this,PasswordActivity_forget.class);
                startActivity(intent);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String password = pass.getText().toString();
                String repassword = repass.getText().toString();
                if (password.equals(repassword)) {
                    Boolean checkpasswordupdate = database.updateuser(user,password);
                    if (checkpasswordupdate == false) {
                        Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent1);
                        Toast.makeText(ResetActivity_forget.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetActivity_forget.this, "Password Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(ResetActivity_forget.this, "Password Not Matched", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}