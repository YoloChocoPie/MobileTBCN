package com.example.mobiletbcn;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.KeepInformation;

import java.io.ByteArrayOutputStream;

public class AddBook extends AppCompatActivity {
    EditText nameBook;
    EditText nameAuthor;

    EditText quantity;
    EditText description;
    ImageView image;

    Database database;
    BookController bookController;
    final private int REQUEST_CODE_CAMERA = 456;
    public static final int PICK_IMAGE = 2;


}
