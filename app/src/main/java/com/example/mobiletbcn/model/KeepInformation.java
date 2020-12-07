package com.example.mobiletbcn.model;

public class KeepInformation {
    private static int idUser;
    private static String role;

    public static int getIdUser() {
        return idUser;
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
