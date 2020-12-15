package com.example.mobiletbcn.Controller;

import java.util.List;

public interface User {
    void addnewuser(com.example.mobiletbcn.model.User user);
    void updateuser(com.example.mobiletbcn.model.User user);
    boolean checkUserByUserNameAndPassword(String userName, String password);
    List getAllUser();
}
