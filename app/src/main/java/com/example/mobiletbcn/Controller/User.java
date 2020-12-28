package com.example.mobiletbcn.Controller;

import java.util.List;

public interface User {
    void addnewuser(com.example.mobiletbcn.model.User user);
    void updateuser(com.example.mobiletbcn.model.User user);
    com.example.mobiletbcn.model.User findUserById(int id);
    boolean checkUserByUserNameAndPassword(String userName, String password);
    List getAllUser();
}
