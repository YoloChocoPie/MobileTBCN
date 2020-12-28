package com.example.mobiletbcn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.Controller.BookingController;
import com.example.mobiletbcn.Controller.UserController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.KeepInformation;

import java.io.ByteArrayOutputStream;

public class DetailEditBook extends AppCompatActivity {
    Database database;
    BookController bookController;
    BookingController bookingController;
    UserController userController;
    Context context = this;

    Button backicon;

    private Book book;
    private int idBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_book_admin);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle extras = getIntent().getExtras();
        int idBook = extras.getInt("idBook"); // Lấy lại id từ list book frame

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookController = new BookController(database);
        userController = new UserController(database);
        bookingController = new BookingController(database);

        book = bookController.findBookById(idBook);
        this.idBook = book.getId();

        TextView nameBook = findViewById(R.id.detail_admin_namebook);
        TextView nameAuthor = findViewById(R.id.detail_admin_nameauthor);
        TextView description = findViewById(R.id.detail_admin_descriptionbook);
        TextView type = findViewById(R.id.detail_admin_typebook);
        TextView quantity = findViewById(R.id.detail_admin_quantity);
        ImageView imageView = findViewById(R.id.detail_admin_imgbook);

        nameBook.setText(book.getName());
        nameAuthor.setText(book.getAuthor());
        type.setText("Type: " + book.getType());
        description.setText(book.getDescription());
        quantity.setText("Quantity: " + book.getQuantity());
        // chuyển byte từ data sang bitmap và gắn vào imageView
        byte[] image = book.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        imageView.setImageBitmap(bitmap);

        backicon = findViewById(R.id.back_icon);
        backicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailEditBook.this,ListBook_frame.class));
                //finish();
            }
        });
    }

    public void onClickEditBook(View view) {
        Intent intent = new Intent(DetailEditBook.this, Edit_Book.class);
        intent.putExtra("idBook", idBook);
        startActivity(intent);
    }

    public void onClickDeleteBook(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        android.app.AlertDialog.Builder mydialog=new
                android.app.AlertDialog.Builder(context);
        mydialog.setTitle("Are you sure want to delete this book?");
        mydialog.setIcon(R.mipmap.ic_launcher);
        mydialog.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
                Book book = new Book();
                book.setId(idBook);
                bookController.deleteBookById(idBook);
                Toast.makeText(DetailEditBook.this,"delete the book successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DetailEditBook.this, ListBook_frame.class);
                intent.putExtra("idBook", idBook);
                startActivity(intent);
            }
        });
        mydialog.setNegativeButton("No",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which)
            {
                // TODO Auto-generated method stub
                dialog.cancel(); //dong dialog
            }
        });
        AlertDialog alerthoitham=mydialog.create();
        alerthoitham.show();
    }
}
