package com.example.mobiletbcn.Controller;

import java.util.List;

public interface User {
    boolean checkUserByUserNameAndPassword(String userName, String password);
    List getAllUser();
}
