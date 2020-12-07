package com.example.mobiletbcn.Controller;

import java.util.List;

public interface Book {
    List<com.example.mobiletbcn.model.Book> getAllBook();
    com.example.mobiletbcn.model.Book findBookById(int id);
    void addNewBook(com.example.mobiletbcn.model.Book book);
    void updateBookById(com.example.mobiletbcn.model.Book book);
    void updateQuantityBookById(int quantity, int idBook);
    void deleteBookById(int id);
    List searchNameBook(String name);
    List searchAuthorBook(String name);
}
