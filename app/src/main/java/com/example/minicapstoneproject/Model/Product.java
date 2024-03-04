package com.example.minicapstoneproject.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private String title;
    private String imageURL;
    private String description;
    private int review;
    private double price;
    private double score;
    private int numberInCart;
    public Product(String title, String imageURL, String description, int review, double price, double score, int numberInCart) {
        this.title = title;
        this.imageURL = imageURL;
        this.description = description;
        this.review = review;
        this.price = price;
        this.score = score;
        this.numberInCart = numberInCart;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImageURL() {
        return imageURL;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getReview() {
        return review;
    }
    public void setReview(int review) {
        this.review = review;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getScore() {
        return score;
    }
    public void setScore(double score) {
        this.score = score;
    }
    public int getNumberInCart() {
        return numberInCart;
    }
    public void setNumberInCart(int numberInChart) {
        this.numberInCart = numberInChart;
    }
}
