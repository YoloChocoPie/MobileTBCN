package com.example.mobiletbcn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.Controller.UserController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.KeepInformation;
import com.example.mobiletbcn.model.User;

import java.util.ArrayList;
import java.util.List;

public class ListUser_frame extends AppCompatActivity {
    ListView listView;
    List<User> UserArrayList;
    List_all_user listUserAdapter;
    Button back_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_frame);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //KeepInformation.getRole().toUpperCase().equals("ADMIN");
        mapping();
        listUserAdapter = new List_all_user(this, R.layout.activity_list_all_user, UserArrayList);
        listView.setAdapter(listUserAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            int idItem = UserArrayList.get(position).getId();
            if (KeepInformation.getRole().trim()/*.toUpperCase()*/.equals("admin")) {
                Intent intent = new Intent(ListUser_frame.this, DetailEditUser.class);
                intent.putExtra("iduser", idItem);
                startActivity(intent);
            }
        });


        /*back_icon = findViewById(R.id.back_icon);
        back_icon.setOnClickListener(v -> {
            Intent intent = new Intent(ListUser_frame.this,Home_screen.class);
            startActivity(intent);
        });*/
    }

    public void mapping() {
        listView = findViewById(R.id.listBook);
        UserArrayList = new ArrayList<>();
        Database database = new Database(this, "ManagementBook.sqlite", null, 1);
        UserController userController = new UserController(database);
        UserArrayList = userController.getAllUser();
    }

    public void cumminghome(View view) {
        finish();
    }
}