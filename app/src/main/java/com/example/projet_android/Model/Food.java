package com.example.projet_android.Model;

import java.io.Serializable;

public class Food implements Serializable {
    private String foodId;
    private String foodName;
    private String imageUrl;
    private String description;

    private String prix;

    public Food() {
        // Default constructor required for Firebase
    }

    public Food(String foodId, String foodName, String imageUrl, String description, String prix) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.prix=prix;
    }

    public Food(String foodName, String foodDescription, String foodPrice, String foodImage) {
    }


    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }
}
