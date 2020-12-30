package com.example.mobiletbcn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.KeepInformation;

import java.util.ArrayList;
import java.util.List;


public class Search_Book extends AppCompatActivity {
    Button back_icon;
    ListView listView;
    List<Book> bookArrayList;
    List_all_book listBookAdapter;
    Database database;
    BookController bookController;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__book);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookController = new BookController(database);

        spinner = (Spinner) findViewById(R.id.spinnerTypeSearch);

        ArrayList<String> arraycate = new ArrayList<String>();
        arraycate.add("Novel");
        arraycate.add("Adventure");
        arraycate.add("Mystery");
        arraycate.add("Fantasy");
        arraycate.add("Historical Fiction");
        arraycate.add("Horror");
        arraycate.add("Literary Fiction");
        arraycate.add("Romance");
        arraycate.add("Sci-Fi");
        arraycate.add("Biographies");
        arraycate.add("Autobiographies");
        arraycate.add("Essays");
        arraycate.add("Self-Help");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line,arraycate);

        spinner.setAdapter(arrayAdapter);

        back_icon = findViewById(R.id.back_icon);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Search_Book.this,Home_screen.class);
                intent.putExtra("name",KeepInformation.getIdUser());
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
                        Intent intent = new Intent(Search_Book.this, DetailEditBook.class);
                        intent.putExtra("idBook", idItem);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Search_Book.this, DetailBook.class);
                        intent.putExtra("idBook", idItem);
                        startActivity(intent);
                    }
                }
            });
        } else {
            Toast.makeText(this, "Không có thông tin bạn cần tìm", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSearchByName(View view) {
        EditText editText = findViewById(R.id.searchNameBook);
        listView = findViewById(R.id.listViewSearchBook);
        bookArrayList = new ArrayList<>();
        bookArrayList = bookController.searchNameBook(editText.getText().toString().trim());
        editText.getText().clear();
        mappingData();
    }

    public void onClickSearchByNameAuthor(View view) {
        EditText editText = findViewById(R.id.searchNameAuthorBook);
        listView = findViewById(R.id.listViewSearchBook);
        bookArrayList = new ArrayList<>();
        bookArrayList = bookController.searchAuthorBook(editText.getText().toString().trim());
        editText.getText().clear();
        mappingData();
    }

    public void onClickSearchByType(View view) {

        listView = findViewById(R.id.listViewSearchBook);
        bookArrayList = new ArrayList<>();
        bookArrayList = bookController.searchTypeBook(spinner.getSelectedItem().toString().trim());
        mappingData();
    }



}
