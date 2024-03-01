package com.example.minicapstoneproject.Model;

public class Product {
    private String title;
    private String imageURL;
    private int review;
    private double price;
    private double score;
    private int numberInChart;

    public Product(String title, String imageURL, int review, double price, double score, int numberInChart) {
        this.title = title;
        this.imageURL = imageURL;
        this.review = review;
        this.price = price;
        this.score = score;
        this.numberInChart = numberInChart;
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

    public int getNumberInChart() {
        return numberInChart;
    }

    public void setNumberInChart(int numberInChart) {
        this.numberInChart = numberInChart;
    }
}
