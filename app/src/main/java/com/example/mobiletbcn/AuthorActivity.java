package com.example.mobiletbcn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.KeepInformation;

import java.util.ArrayList;
import java.util.List;

public class AuthorActivity extends AppCompatActivity {
    List<Book> bookArrayList;
    List_all_book listBookAdapter;
    Database database;
    BookController bookController;
    ListView listView;
    Button back_icon;
    TextView authorName, tvListAllBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookController = new BookController(database);

        listView = findViewById(R.id.lvBookByAuthor);
        back_icon = findViewById(R.id.back_icon3);
        authorName = findViewById(R.id.txtAuthorName);
        tvListAllBook = findViewById(R.id.tvDanhSachTacPham);


        Bundle extras = getIntent().getExtras();
        String nameAu = extras.getString("nameauthor");
        authorName.setText(nameAu);
        tvListAllBook.setText("List books of " + nameAu + ":");

        bookArrayList = new ArrayList<>();
        bookArrayList = bookController.searchAuthorBook(authorName.getText().toString().trim());
        mappingData();


        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AuthorActivity.this, Home_screen.class);
                startActivity(intent);
            }
        });
    }
    public void mappingData() {
        listBookAdapter = new List_all_book(this, R.layout.activity_list_all_book, bookArrayList);
        if (bookArrayList.size() > 0) {
            listView.setAdapter(listBookAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {
                    int idItem = bookArrayList.get(position).getId();
                    if (KeepInformation.getRole().trim()/*.toUpperCase()*/.equals("admin")) {
                        Intent intent = new Intent(AuthorActivity.this, DetailEditBook.class);
                        intent.putExtra("idBook", idItem);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(AuthorActivity.this, DetailBook.class);
                        intent.putExtra("idBook", idItem);
                        startActivity(intent);
                    }
                }
            });
        } else {
            Toast.makeText(this, "Không có thông tin bạn cần tìm", Toast.LENGTH_SHORT).show();
        }
    }
}