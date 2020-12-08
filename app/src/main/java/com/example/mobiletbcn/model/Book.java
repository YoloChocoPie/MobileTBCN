package com.example.mobiletbcn.model;

public class Book {
    private int id;
    private String name;
    private byte[] image;

    private String author;
    private String description;
    private String quantity;

    public Book() {
    }


    public Book(int id, String name, byte[] image, String author, String description, String quantity) {
        this.id = id;
        this.name = name;
        this.image = image;

        this.author = author;
        this.description = description;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
