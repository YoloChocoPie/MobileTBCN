package com.example.mobiletbcn;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.KeepInformation;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Add_Book extends AppCompatActivity{
    public static final int PICK_IMAGE = 2;
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;
    final private int REQUEST_CODE_CAMERA = 456;
    Button back_icon;
    EditText type;
    Spinner spinertype;
    EditText nameBook;
    EditText nameAuthor;
    EditText quantity;
    EditText description;
    ImageView image;

    Database database;
    BookController bookController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__book);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookController = new BookController(database);
        //KeepInformation.getRole().equals("admin");

        back_icon = findViewById(R.id.back_icon);
        back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_Book.this,Home_screen.class);
                intent.putExtra("name",KeepInformation.getIdUser());
                startActivity(intent);
            }
        });


        mapping();
    }


    public void mapping() {

        nameBook = findViewById(R.id.nameBookAdd);
        nameAuthor = findViewById(R.id.nameAuthorAdd);
        quantity = findViewById(R.id.quantityAdd);
        description = findViewById(R.id.descriptionAdd);
        image = findViewById(R.id.imageAdd);
        spinertype =(Spinner) findViewById(R.id.spinertypeBookAdd);

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

        spinertype.setAdapter(arrayAdapter);
    }


    public void buttonAddBook(View view) {
        Book book = new Book();
        book.setName(nameBook.getText().toString());
        book.setAuthor(nameAuthor.getText().toString());
        book.setType(spinertype.getSelectedItem().toString());
        book.setQuantity(quantity.getText().toString());
        book.setDescription(description.getText().toString());

        // chuyen data image sang byte[]
        BitmapDrawable bitmapDrawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] picture = byteArrayOutputStream.toByteArray();
        book.setImage(picture);

        bookController.addNewBook(book);
        Toast.makeText(Add_Book.this,"Thêm sách thành công",Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(Add_Book.this, Home_screen.class));
        Intent intent = new Intent(Add_Book.this,Home_screen.class);
        intent.putExtra("name",KeepInformation.getIdUser());
        startActivity(intent);
    }

    public void SelectImageToPhone(View view) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                //permission is not granted, request it.
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                //show popup for runtime permission
                requestPermissions(permissions, PERMISSION_CODE);
            }
            else {
                //permission already granted
                pickImageFromGallery();
            }
        }
        else {
            //system os is less them marshmallow
            pickImageFromGallery();
        }
    }

    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    pickImageFromGallery();
                }
                else {
                    //permission was denied
                    Toast.makeText(this, "Permission denied!!!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //handle result of picked image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_PICK_CODE && resultCode == RESULT_OK && data != null ) {
            image.setImageURI(data.getData());
        }
    }


}
