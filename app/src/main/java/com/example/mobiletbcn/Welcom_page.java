package com.example.mobiletbcn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiletbcn.Controller.BookController;

public class Welcom_page extends AppCompatActivity {

    Button btnLogin,btnRegister;
    BookController bookController;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom_page);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookController = new BookController(database);

        /*database.queryData("DROP TABLE Book");
        database.queryData("DROP TABLE IF EXISTS User");
        database.queryData("DROP TABLE Booking");*/

        database.queryData("CREATE TABLE IF NOT EXISTS Book (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), " +
                "author VARCHAR(200), type VARCHAR(100), quantity INTEGER(100), image BLOB, description VARCHAR(200))");
        database.queryData("CREATE TABLE IF NOT EXISTS User (id INTEGER PRIMARY KEY AUTOINCREMENT, fullName VARCHAR(200), " +
                "role VARCHAR(20), userName VARCHAR(100), password VARCHAR(100), cfpass VARCHAR(100))");
        database.queryData("CREATE TABLE IF NOT EXISTS Booking (id INTEGER PRIMARY KEY AUTOINCREMENT, idUser_Booking INTEGER(100), " +
                "idBook_Booking INTEGER(100))");

        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        SharedPreferences.Editor editor = wmbPreference.edit();

        if (isFirstRun){
            database.queryData("INSERT INTO User VALUES (NULL, 'Lương Triễn Cường', 'admin', 'admin', 'admin', 'admin')");
            //database.queryData("INSERT INTO User VALUES (NULL, 'Nam', 'user', 'nam', '123', '123')");
            editor.putBoolean("FIRSTRUN", false);
            editor.apply();
        }



        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        /*btnExit = findViewById(R.id.btnExit);*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcom_page.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcom_page.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

       /* btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });*/
    }
}