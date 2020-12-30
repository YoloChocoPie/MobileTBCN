package com.example.mobiletbcn;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobiletbcn.Controller.BookController;
import com.example.mobiletbcn.Controller.BookingController;
import com.example.mobiletbcn.Controller.UserController;
import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.Booking;
import com.example.mobiletbcn.model.KeepInformation;
import com.example.mobiletbcn.model.User;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class DetailBook extends AppCompatActivity {
    Database database;
    BookController bookController;
    BookingController bookingController;
    UserController userController;

    Button backicon, borrow,borrowing,oosp;

    private Book book;
    private int idBook;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_book);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle extras = getIntent().getExtras();
        creatNotificationChannel();
        int idBook = extras.getInt("idBook"); // Lấy lại id từ list book frame

        database = new Database(this, "ManagementBook.sqlite", null, 1);
        bookController = new BookController(database);
        userController = new UserController(database);
        bookingController = new BookingController(database);

        book = bookController.findBookById(idBook);
        this.idBook = book.getId();

        TextView nameBook = findViewById(R.id.detail_namebook);
        TextView nameAuthor = findViewById(R.id.detail_nameauthor);
        TextView description = findViewById(R.id.detail_descriptionbook);
        TextView type = findViewById(R.id.detail_typebook);
        TextView quantity = findViewById(R.id.detail_quantity);
        ImageView imageView = findViewById(R.id.detail_imgbook);

        borrow = findViewById(R.id.detail_btn_borrow) ;
        borrowing = findViewById(R.id.detail_btn_borrowing) ;
        oosp = findViewById(R.id.detail_btn_outofstock) ;

        int idUser = KeepInformation.getIdUser();
        List<Booking> listBookingOfUser = bookingController.getAllBookOfUserBooking(idUser);
        boolean checkBooking = false;
        int quantityBook = Integer.parseInt(bookController.findBookById(idBook).getQuantity());// nếu sl sách = 0 thì ko mượn đc
        for (int i = 0; i < listBookingOfUser.size(); i++) {
            if (idBook == listBookingOfUser.get(i).getBook().getId()) {
                checkBooking = true; // check sách đã có thì ko được mượn nữa
            }
        }
        if (checkBooking) {
            // nếu sách đã mượn rồi
            //Toast.makeText(DetailBook.this, "Bạn đã mượn sách này rồi ạ !", Toast.LENGTH_SHORT).show();
            borrowing.setVisibility(View.VISIBLE);
        } else {
            if (quantityBook == 0) {
                //Toast.makeText(DetailBook.this, "Đã hết sách !", Toast.LENGTH_SHORT).show();
                oosp.setVisibility(View.VISIBLE);
            } else {
                // nếu sách chưa mượn
                /*bookingController.userBookingBook(idUser, idBook);
                bookController.updateQuantityBookById(Integer.parseInt(book.getQuantity()) - 1, idBook);
                startActivity(new Intent(DetailBook.this, Borrow_List.class));*/
                borrow.setVisibility(View.VISIBLE);
            }
        }

        borrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailBook.this,"borrowed books successfully",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(DetailBook.this, ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(DetailBook.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                long timeAtButtonClick = System.currentTimeMillis();

                long tenSecondsInMills = 1000 + 10;

                alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonClick + tenSecondsInMills, pendingIntent);

                /*int idUser = KeepInformation.getIdUser();
                List<Booking> listBookingOfUser = bookingController.getAllBookOfUserBooking(idUser);
                boolean checkBooking = false;
                int quantityBook = Integer.parseInt(bookController.findBookById(idBook).getQuantity());// nếu sl sách = 0 thì ko mượn đc
                for (int i = 0; i < listBookingOfUser.size(); i++) {
                    if (idBook == listBookingOfUser.get(i).getBook().getId()) {
                        checkBooking = true; // check sách đã có thì ko được mượn nữa
                    }
                }
                if (checkBooking) {
                    // nếu sách đã mượn rồi
                    Toast.makeText(DetailBook.this, "Bạn đã mượn sách này rồi ạ !", Toast.LENGTH_SHORT).show();
*//*            Button btn_brwing = findViewById(R.id.detail_btn_borrowing);
            btn_brwing.setVisibility(View.VISIBLE);*//*
                } else {
                    if (quantityBook == 0) {
                        Toast.makeText(DetailBook.this, "Đã hết sách !", Toast.LENGTH_SHORT).show();
*//*                Button btn_outofstok = findViewById(R.id.detail_btn_outofstock);
                btn_outofstok.setVisibility(View.VISIBLE);*//*
                    } else {
                        // nếu sách chưa mượn
                        bookingController.userBookingBook(idUser, idBook);
                        bookController.updateQuantityBookById(Integer.parseInt(book.getQuantity()) - 1, idBook);
                        startActivity(new Intent(DetailBook.this, Borrow_List.class));
                    }
                }*/
                int idUser = KeepInformation.getIdUser();
                List<Booking> listBookingOfUser = bookingController.getAllBookOfUserBooking(idUser);
                bookingController.userBookingBook(idUser, idBook);
                bookController.updateQuantityBookById(Integer.parseInt(book.getQuantity()) - 1, idBook);
                startActivity(new Intent(DetailBook.this, Borrow_List.class));
            }
        });

        nameBook.setText(book.getName());

        nameAuthor.setText(book.getAuthor());
        nameAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameAu = nameAuthor.getText().toString();
                Intent intent = new Intent(DetailBook.this,AuthorActivity.class);
                intent.putExtra("nameauthor", nameAu);
                startActivity(intent);
            }
        });

        type.setText(/*"Type: " + */book.getType());
        type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String typeBook = type.getText().toString();
                Intent intent = new Intent(DetailBook.this,TypeActivity.class);
                intent.putExtra("nametype", typeBook);
                startActivity(intent);
            }
        });

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
                //startActivity(new Intent(DetailBook.this,ListBook_frame.class));
                finish();
            }
        });
    }

    private void creatNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "LemubitReminderChannel";
            String description = "Channel for Lemubit Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyLemubit", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    /*public void functionBooking(View view) {
        int idUser = KeepInformation.getIdUser();
        List<Booking> listBookingOfUser = bookingController.getAllBookOfUserBooking(idUser);
        boolean checkBooking = false;
        int quantityBook = Integer.parseInt(bookController.findBookById(idBook).getQuantity());// nếu sl sách = 0 thì ko mượn đc
        for (int i = 0; i < listBookingOfUser.size(); i++) {
            if (idBook == listBookingOfUser.get(i).getBook().getId()) {
                checkBooking = true; // check sách đã có thì ko được mượn nữa
            }
        }
        if (checkBooking) {
            // nếu sách đã mượn rồi
            Toast.makeText(this, "Bạn đã mượn sách này rồi ạ !", Toast.LENGTH_SHORT).show();
            Button btn_brwing = findViewById(R.id.detail_btn_borrowing);
            btn_brwing.setVisibility(View.VISIBLE);
        } else {
            if (quantityBook == 0) {
                Toast.makeText(this, "Đã hết sách !", Toast.LENGTH_SHORT).show();
                Button btn_outofstok = findViewById(R.id.detail_btn_outofstock);
                btn_outofstok.setVisibility(View.VISIBLE);
            } else {
                // nếu sách chưa mượn
                bookingController.userBookingBook(idUser, idBook);
                bookController.updateQuantityBookById(Integer.parseInt(book.getQuantity()) - 1, idBook);
                //Intent intent = new Intent(DetailBook.this, BookingListOfUser.class);
                //startActivity(intent);
            }
        }
    }*/


}
