package com.example.mobiletbcn;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.KeepInformation;

import java.util.ArrayList;
import java.util.List;

public class ListBook_frame extends AppCompatActivity {
    ListView listView;
    List<Book> bookArrayList;
    List_all_book listBookAdapter;
    Button back_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book_frame);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //KeepInformation.getRole().toUpperCase().equals("ADMIN");
        mapping();
        listBookAdapter = new List_all_book(this, R.layout.activity_list_all_book, bookArrayList);
        listView.setAdapter(listBookAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            int idItem = bookArrayList.get(position).getId();
            if (KeepInformation.getRole().trim()/*.toUpperCase()*/.equals("admin")) {
                Intent intent = new Intent(ListBook_frame.this, DetailEditBook.class);
                intent.putExtra("idBook", idItem);
                startActivity(intent);
            } else {
                Intent intent = new Intent(ListBook_frame.this, DetailBook.class);
                intent.putExtra("idBook", idItem);
                startActivity(intent);
            }
        });


        back_icon = findViewById(R.id.back_icon);
        back_icon.setOnClickListener(v -> {
            Intent intent = new Intent(ListBook_frame.this,Home_screen.class);
            intent.putExtra("name",KeepInformation.getIdUser());
            startActivity(intent);
        });

    }

    public void mapping() {
        listView = findViewById(R.id.listBook);
        bookArrayList = new ArrayList<>();
        Database database = new Database(this, "ManagementBook.sqlite", null, 1);
        BookController bookController = new BookController(database);
        bookArrayList = bookController.getAllBook();
    }


}