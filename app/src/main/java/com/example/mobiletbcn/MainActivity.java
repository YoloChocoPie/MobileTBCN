package com.example.mobiletbcn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.Controller.UserController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.KeepInformation;
import com.example.mobiletbcn.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Database database;
    BookController bookController;
    UserController userController;
    Button back_icon,login,forget;
    List<User> userArrayList;
    CheckBox chkBoxRememberMe;
    EditText Username, Password;
    SharedPreferences loginPreferences;
    SharedPreferences.Editor loginPrefsEditor;
    boolean saveLogin;
    private String us, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = new Database(this, "ManagementBook.sqlite", null, 1);

        bookController = new BookController(database);
        userController = new UserController(database);

        login = findViewById(R.id.button);
        login.setOnClickListener(this::onClick);
        Username = findViewById(R.id.userName);
        Password = findViewById(R.id.password);
        chkBoxRememberMe = findViewById(R.id.chkRememberMe);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if (saveLogin == true){
            Username.setText(loginPreferences.getString("username", ""));
            Password.setText(loginPreferences.getString("password", ""));
            chkBoxRememberMe.setChecked(true);
        }

        forget = findViewById(R.id.btn_forget);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PasswordActivity_forget.class);
                startActivity(intent);
            }
        });

        back_icon = findViewById(R.id.back_icon1);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Welcom_page.class);
                startActivity(intent);
            }
        });

    }

    public void onClick(View view){
        if (view == login){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(Username.getWindowToken(), 0);
            us = Username.getText().toString();
            pw = Password.getText().toString();
            if (chkBoxRememberMe.isChecked()){
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", us);
                loginPrefsEditor.putString("password", pw);
                loginPrefsEditor.commit();
            }
            else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }
            moveToHome();
        }
    }

    private void moveToHome() {
        EditText userName = findViewById(R.id.userName);
        EditText password = findViewById(R.id.password);
        String us = userName.getText().toString();
        String pass = password.getText().toString();
        boolean checkUser = userController.checkUserByUserNameAndPassword(us, pass);
        if (checkUser) {
            // thành công thì chuyển sang trang home
            Intent intent = new Intent(MainActivity.this, Home_screen.class);
            intent.putExtra("name", KeepInformation.getIdUser());
            startActivity(intent);
        } else {
            // thất bại
            Toast.makeText(this, "Wrong username and password", Toast.LENGTH_SHORT).show();
        }
    }
}
