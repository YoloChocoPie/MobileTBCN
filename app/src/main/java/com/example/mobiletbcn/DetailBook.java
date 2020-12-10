package com.example.mobiletbcn;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.Controller.BookingController;
import com.example.mobiletbcn.Controller.UserController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.Booking;
import com.example.mobiletbcn.model.KeepInformation;

import java.util.List;

public class DetailBook extends AppCompatActivity {
    Database database;
    BookController bookController;
    BookingController bookingController;
    UserController userController;

    private Book book;
    private int idBook;


}
