package com.example.mobiletbcn;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.mobiletbcn.model.Book;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // crud data
    public void queryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // crud data
    public void insertDataHasImage(Book book) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Book VALUES(NULL, ?, ?, ?, ?, ?)";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, book.getName());
        sqLiteStatement.bindBlob(2, book.getImage());
        sqLiteStatement.bindString(4, book.getAuthor());
        sqLiteStatement.bindString(5, book.getDescription());
        sqLiteStatement.bindString(6, book.getQuantity());

        sqLiteStatement.executeInsert();
    }

    public void UpdateDataHasImage(Book book) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Book SET name = ?, image = ?, author = ?, description = ?, quantity = ? " +
                "WHERE id = ?";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, book.getName());
        sqLiteStatement.bindBlob(2, book.getImage());
        sqLiteStatement.bindString(4, book.getAuthor());
        sqLiteStatement.bindString(5, book.getDescription());
        sqLiteStatement.bindString(6, book.getQuantity());
        sqLiteStatement.bindLong(7, book.getId());

        sqLiteStatement.executeInsert();
    }

    // select data
    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
}
