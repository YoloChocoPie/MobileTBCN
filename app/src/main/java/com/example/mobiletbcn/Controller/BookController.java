package com.example.mobiletbcn.Controller;

import android.database.Cursor;

import com.example.mobiletbcn.Database;

import java.util.ArrayList;
import java.util.List;

public class BookController implements Book {
    Database database;

    public BookController(Database database) {
        this.database = database;
    }

    @Override
    public void addNewBook(com.example.mobiletbcn.model.Book book) {
        database.insertDataHasImage(book);
    }

    // Chức năng lấy mọi sách
    @Override
    public List<com.example.mobiletbcn.model.Book> getAllBook() {
        Cursor cursor = database.getData("SELECT id, name, author, type, quantity,  image, description FROM Book");
        List<com.example.mobiletbcn.model.Book> bookList = new ArrayList<>();
        while (cursor.moveToNext()) {
            com.example.mobiletbcn.model.Book book = new com.example.mobiletbcn.model.Book();
            book.setId(cursor.getInt(cursor.getColumnIndex("id")));
            book.setName(cursor.getString(cursor.getColumnIndex("name")));
            book.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
            book.setType(cursor.getString(cursor.getColumnIndex("type")));
            book.setQuantity(cursor.getString(cursor.getColumnIndex("quantity")));
            book.setImage(cursor.getBlob(cursor.getColumnIndex("image")));
            book.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            bookList.add(book);
        }
        return bookList;
    }

   // Cập nhập sách theo ID

    @Override
    public void updateQuantityBookById(int quantity, int idBook) {
        database.queryData("UPDATE Book SET quantity = '" + quantity + "' WHERE id = " + idBook);
    }

    // Tìm sách theo ID
    @Override
    public com.example.mobiletbcn.model.Book findBookById(int id) {
        Cursor cursor = database.getData("SELECT * FROM Book WHERE id = " + id);
        com.example.mobiletbcn.model.Book book = new com.example.mobiletbcn.model.Book();
        while (cursor.moveToNext()) {
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setType(cursor.getString(3));
            book.setQuantity(cursor.getString(4));
            book.setImage(cursor.getBlob(5));
            book.setDescription(cursor.getString(6));

        }
        return book;
    }

    @Override
    public void updateBookById(com.example.mobiletbcn.model.Book book) {
        database.UpdateDataHasImage(book);
    }

    @Override
    public void deleteBookById(int id) {
        database.queryData("DELETE FROM Book WHERE id = " + id);
    }

    @Override
    public List searchNameBook(String name) {
        Cursor cursor = database.getData("SELECT * FROM Book WHERE name LIKE '%" + name + "%' " +
                "OR name LIKE '%" + name + "' " +
                "OR name LIKE '" + name + "%' " +
                "OR name LIKE '" + name + "'");
        List<com.example.mobiletbcn.model.Book> bookList = new ArrayList<>();
        while (cursor.moveToNext()) {
            com.example.mobiletbcn.model.Book book = new com.example.mobiletbcn.model.Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setType(cursor.getString(3));
            book.setQuantity(cursor.getString(4));
            book.setImage(cursor.getBlob(5));
            book.setDescription(cursor.getString(6));
            bookList.add(book);
        }
        return bookList;
    }

    @Override
    public List searchAuthorBook(String name) {
        Cursor cursor = database.getData("SELECT * FROM Book WHERE author LIKE '" + name + "' " +
                "OR author LIKE '%" + name + "%' " +
                "OR author LIKE '%" + name + "' " +
                "OR author LIKE '" + name + "%' " +
                "OR author LIKE ' " + name + " '; ");
        List<com.example.mobiletbcn.model.Book> bookList = new ArrayList<>();
        while (cursor.moveToNext()) {
            com.example.mobiletbcn.model.Book book = new com.example.mobiletbcn.model.Book();
            book.setId(cursor.getInt(0));
            book.setName(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setType(cursor.getString(3));
            book.setQuantity(cursor.getString(4));
            book.setImage(cursor.getBlob(5));
            book.setDescription(cursor.getString(6));

            bookList.add(book);
        }
        return bookList;
    }
}
