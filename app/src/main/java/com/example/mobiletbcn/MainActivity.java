package com.example.mobiletbcn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.Controller.UserController;

public class MainActivity extends AppCompatActivity {
    Database database;
    BookController bookController;
    UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this, "ManagementBook.sqlite", null, 1);

        bookController = new BookController(database);
        userController = new UserController(database);

//        database.queryData("DROP TABLE Book");
//        database.queryData("DROP TABLE User");
//        database.queryData("DROP TABLE Booking");

        database.queryData("CREATE TABLE IF NOT EXISTS Book (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200), " +
                "image BLOB, price VARCHAR(100), author VARCHAR(200), description VARCHAR(200), quantity INTEGER(100))");
        database.queryData("CREATE TABLE IF NOT EXISTS User (id INTEGER PRIMARY KEY AUTOINCREMENT, fullName VARCHAR(200), " +
                "role VARCHAR(20), userName VARCHAR(100), password VARCHAR(100))");
        database.queryData("CREATE TABLE IF NOT EXISTS Booking (id INTEGER PRIMARY KEY AUTOINCREMENT, idUser_Booking INTEGER(100), " +
                "idBook_Booking INTEGER(100))");

        database.queryData("INSERT INTO User VALUES (NULL, 'Mai Tuấn Anh', 'admin', 'admin', 'admin')");
        database.queryData("INSERT INTO User VALUES (NULL, 'Nguyễn Văn A', 'user', 'nguyenvana', '123')");
    }

    public void login(View view) {
        EditText userName = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        String us = userName.getText().toString();
        String pass = password.getText().toString();
        boolean checkUser = userController.checkUserByUserNameAndPassword(us, pass);
        if (checkUser) {
            // thành công thì chuyển sang trang home
            //Intent intent = new Intent(MainActivity.this, HomeMain.class);
            //startActivity(intent);
        } else {
            // thất bại
            Toast.makeText(this, "Wrong username and password", Toast.LENGTH_SHORT).show();
        }
    }
}
