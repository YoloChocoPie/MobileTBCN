package com.example.mobiletbcn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mobiletbcn.Controller.UserController;
import com.example.mobiletbcn.model.KeepInformation;
import com.example.mobiletbcn.model.User;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {

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
        setContentView(R.layout.activity_profile);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        userController = new UserController(database);

        Bundle extras = getIntent().getExtras();
        int iduser = extras.getInt("user");

        user = userController.findUserById(iduser);
        this.iduser = user.getId();

        mappingData(iduser);
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

    public void buttonUpdateUser(View view) {
        User user = new User();
        user.setId(iduser);
        user.setFullName(fname.getText().toString());
        user.setUserName(uname.getText().toString());
        user.setRole(role.getSelectedItem().toString());
        user.setPassword(pass.getText().toString());
        user.setcfpass(cfpass.getText().toString());

        userController.updateuser(user);
        Toast.makeText(Profile.this,"Thay đổi thành công",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Profile.this, Home_screen.class);
        intent.putExtra("name",KeepInformation.getIdUser());
        startActivity(intent);
        //finish();
    }

    public void backtohome(View view) {
        finish();
    }
}