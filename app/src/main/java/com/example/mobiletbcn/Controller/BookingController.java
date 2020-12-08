package com.example.mobiletbcn.Controller;

import android.database.Cursor;

import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.User;
import com.example.mobiletbcn.Database;

import java.util.ArrayList;
import java.util.List;

public class BookingController implements Booking {
    Database database;

    public BookingController(Database database) {
        this.database = database;
    }

    @Override
    public void deleteBookingById(int id) {
        database.queryData("DELETE FROM Booking WHERE id = + " + id);
    }

    @Override
    public List getAllBookOfUserBooking(int idUserBooking) {
        Cursor cursor = database.getData("SELECT Booking.id, User.id, Book.id, Book.name, Book.image, Book.quantity" +
                " FROM User INNER JOIN Booking ON Booking.idUser_Booking = User.id " +
                "INNER JOIN Book ON Book.id = Booking.idBook_Booking WHERE User.id = " + idUserBooking);
        List<com.example.mobiletbcn.model.Booking> list = new ArrayList();
        while (cursor.moveToNext()) {
            User user = new User();
            user.setId(cursor.getInt(1));
            Book book = new Book();
            book.setId(cursor.getInt(2));
            book.setName(cursor.getString(3));
            book.setImage(cursor.getBlob(4));
            book.setQuantity(cursor.getString(5));

            com.example.mobiletbcn.model.Booking booking = new com.example.mobiletbcn.model.Booking();
            booking.setId(cursor.getInt(0));
            booking.setUser(user);
            booking.setBook(book);

            list.add(booking);
        }
        return list;
    }

    @Override
    public void userBookingBook(int idUserBooking, int idBookBooking) {
        database.queryData("INSERT INTO Booking VALUES(NULL, '" + idUserBooking + "', '" + idBookBooking + "')");
    }
}
