package com.example.mobiletbcn;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.mobiletbcn.model.Book;
import com.example.mobiletbcn.model.User;

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
        String sql = "INSERT INTO Book VALUES( Null, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, book.getName());
        sqLiteStatement.bindString(2, book.getAuthor());
        sqLiteStatement.bindString(3, book.getType());
        sqLiteStatement.bindString(4, book.getQuantity());
        sqLiteStatement.bindBlob(5, book.getImage());
        sqLiteStatement.bindString(6, book.getDescription());

        sqLiteStatement.executeInsert();
    }

    public void addnewuser(User user) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO User VALUES( Null, ?, ?, ?, ?, ?)";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, user.getFullName());
        sqLiteStatement.bindString(2, user.getRole());
        sqLiteStatement.bindString(3, user.getUserName());
        sqLiteStatement.bindString(4, user.getPassword());
        sqLiteStatement.bindString(5, user.getcfpass());

        sqLiteStatement.executeInsert();
    }

    public void updateuser(User user) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE User SET  FullName = ?, Role = ?, UserName = ?, Password = ?, cfpass = ? " +
                "WHERE id = ?";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, user.getFullName());
        sqLiteStatement.bindString(2, user.getRole());
        sqLiteStatement.bindString(3, user.getUserName());
        sqLiteStatement.bindString(4, user.getPassword());
        sqLiteStatement.bindString(5, user.getcfpass());
        sqLiteStatement.bindLong(6, user.getId());

        sqLiteStatement.executeInsert();
    }

    public void UpdateDataHasImage(Book book) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Book SET name = ?, author = ?, type = ?, quantity = ?, image = ?,description = ? " +
                "WHERE id = ?";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        //sqLiteStatement.bindLong(0, book.getId());
        sqLiteStatement.bindString(1, book.getName());
        sqLiteStatement.bindString(2, book.getAuthor());
        sqLiteStatement.bindString(3,book.getType());
        sqLiteStatement.bindString(4, book.getQuantity());
        sqLiteStatement.bindBlob(5, book.getImage());
        sqLiteStatement.bindString(6, book.getDescription());
        sqLiteStatement.bindLong(7, book.getId());


        sqLiteStatement.executeInsert();
    }

    // select data
    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
}
