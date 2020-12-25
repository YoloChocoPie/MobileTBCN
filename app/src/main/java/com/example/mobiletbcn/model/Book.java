package com.example.mobiletbcn.model;

public class Book {
    private int id;
    private String name;
    private String author;
    private String type;
    private String  quantity;
    private byte[] image;
    private String description;


    public Book() {
    }


    public Book(int id, String name, String author, String type, String quantity, byte[] image,  String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.type = type;
        this.quantity = quantity;
        this.image = image;
        this.description = description;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
