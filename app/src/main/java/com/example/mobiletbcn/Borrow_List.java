
package com.example.mobiletbcn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.Controller.BookingController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.Booking;
import com.example.mobiletbcn.model.KeepInformation;

import java.util.ArrayList;
import java.util.List;

public class Borrow_List extends AppCompatActivity {
    ListView listView;
    List<Booking> bookingArrayList;
    Button back_icon;
    Database database;
    BookingController bookingController;
    BookController bookController;
    List_Booking listBookingOfUserAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow__list);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookingController = new BookingController(database);
        bookController = new BookController(database);

        mapping();
        listBookingOfUserAdapter = new List_Booking(this, R.layout.activity_list__booking, bookingArrayList);
        listView.setAdapter(listBookingOfUserAdapter);

        back_icon = findViewById(R.id.borrow_back_icon);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Borrow_List.this, Home_screen.class));
                Intent intent = new Intent(Borrow_List.this,Home_screen.class);
                intent.putExtra("name",KeepInformation.getIdUser());
                startActivity(intent);
            }
        });
    }

    private void mapping() {
        listView = findViewById(R.id.listBooking);
        bookingArrayList = new ArrayList<>();
        BookingController booking = new BookingController(database);
        bookingArrayList = booking.getAllBookOfUserBooking(KeepInformation.getIdUser());
        if (bookingArrayList.size() == 0) {
            Toast.makeText(this, "Bạn chưa thuê cuốn sách nào", Toast.LENGTH_SHORT).show();
        }
    }


}