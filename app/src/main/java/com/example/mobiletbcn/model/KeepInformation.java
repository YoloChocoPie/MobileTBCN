package com.example.mobiletbcn.model;

public class KeepInformation {
    private static int idUser;
    private static String role;
    private static String fullname;

    public static int getIdUser() {
        return idUser;
    }

    public static String getFullnameUser() {
        return fullname;
    }

    public static void setFullname(String fullname) {
        KeepInformation.fullname = fullname;
    }

    public static void setIdUser(int idUser) {
        KeepInformation.idUser = idUser;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        KeepInformation.role = role;
    }
}
