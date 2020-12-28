package com.example.mobiletbcn.Controller;

import android.database.Cursor;

import com.example.mobiletbcn.model.KeepInformation;
import com.example.mobiletbcn.Database;

import java.util.ArrayList;
import java.util.List;

public class UserController implements User {
    Database database;


    public UserController(Database database) {
        this.database = database;
    }

    @Override
    public void addnewuser(com.example.mobiletbcn.model.User user) {
        database.addnewuser(user);
    }

    @Override
    public void updateuser(com.example.mobiletbcn.model.User user) {
        database.updateuser(user);
    }

    @Override
    public com.example.mobiletbcn.model.User findUserById(int id) {
        Cursor cursor = database.getData("SELECT * FROM User WHERE id = " + id);
        com.example.mobiletbcn.model.User user = new com.example.mobiletbcn.model.User();
        while (cursor.moveToNext()) {
            user.setId(cursor.getInt(0));
            user.setFullName(cursor.getString(1));
            user.setRole(cursor.getString(2));
            user.setUserName(cursor.getString(3));
            user.setPassword(cursor.getString(4));
            //user.setcfpass(cursor.getString(5));
            //user.setIdBooking_User(cursor.getInt(6));
        }
        return user;
    }


    @Override
    public List getAllUser() {
        Cursor cursor = database.getData("SELECT * FROM User");
        List list = new ArrayList();
        while (cursor.moveToNext()) {
            com.example.mobiletbcn.model.User user = new com.example.mobiletbcn.model.User();
            user.setId(cursor.getInt(0));
            user.setFullName(cursor.getString(1));
            user.setRole(cursor.getString(2));
            user.setUserName(cursor.getString(3));
            user.setPassword(cursor.getString(4));
            user.setIdBooking_User(cursor.getInt(5));
            list.add(user);
        }
        return list;
    }

    @Override
    public boolean checkUserByUserNameAndPassword(String userName, String password) {
        Cursor cursor = database.getData("SELECT * FROM User WHERE userName = '" + userName +"' AND password = '" + password + "'");
        com.example.mobiletbcn.model.User user = new com.example.mobiletbcn.model.User();
        while (cursor.moveToNext()) {
            user.setId(cursor.getInt(0));
            user.setFullName(cursor.getString(1));
            user.setRole(cursor.getString(2));
            user.setUserName(cursor.getString(3));
            user.setPassword(cursor.getString(4));
            user.setcfpass(cursor.getString(5));
        }
        if (user.getId() != 0) {
            KeepInformation.setIdUser(user.getId());
            KeepInformation.setRole(user.getRole());
            return true;
        } else {
            return false;
        }
    }
}
