package com.example.mobiletbcn.model;

public class User {
    private int id;
    private String fullName;
    private String role;
    private String userName;
    private String password;
    private String cfpass;
    private int idBooking_User;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getcfpass() {
        return cfpass;
    }

    public void setcfpass(String cfpass) {
        this.cfpass = cfpass;
    }

    public int getIdBooking_User() {
        return idBooking_User;
    }

    public void setIdBooking_User(int idBooking_User) {
        this.idBooking_User = idBooking_User;
    }


}
