package com.example.mobiletbcn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.Controller.UserController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.User;

import java.util.ArrayList;

public class DetailEditUser extends AppCompatActivity {
    Database database;
    UserController userController;

    private User user;
    private int iduser;


    EditText fname;
    EditText uname;
    Spinner role;
    EditText pass;
    EditText cfpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_edit_user);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        userController = new UserController(database);

        Bundle extras = getIntent().getExtras();
        int iduser = extras.getInt("iduser");

        user = userController.findUserById(iduser);
        this.iduser = user.getId();

        mappingData(iduser);

        /*backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
    }

    public void mappingData(int iduser) {
        fname = findViewById(R.id.fullnameEdit);
        uname = findViewById(R.id.usernameEdit);
        role = (Spinner) findViewById(R.id.rolespinner);
        pass = findViewById(R.id.passwordEdit);
        cfpass = findViewById(R.id.cfpasswordEdit);

        ArrayList<String> arrayrole = new ArrayList<String>();
        arrayrole.add("admin");
        arrayrole.add("user");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,arrayrole);
        role.setAdapter(arrayAdapter);

        User usern = userController.findUserById(iduser);
        fname.setText(usern.getFullName());
        uname.setText(usern.getUserName());
        role.setSelection(arrayAdapter.getPosition(usern.getRole()));
        pass.setText(usern.getPassword());
        cfpass.setText(usern.getcfpass());
    }

    public void buttonUpdate(View view) {
        User user = new User();
        user.setId(iduser);
        user.setFullName(fname.getText().toString());
        user.setUserName(uname.getText().toString());
        user.setRole(role.getSelectedItem().toString());
        user.setPassword(pass.getText().toString());
        user.setcfpass(cfpass.getText().toString());

        userController.updateuser(user);
        Intent intent = new Intent(this, ListUser_frame.class);
        startActivity(intent);
    }

    public void backtofuture(View view) {
        finish();
    }
}