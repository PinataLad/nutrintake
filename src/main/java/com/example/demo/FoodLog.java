package com.example.demo;

//Logs individual food information
public class FoodLog {

    private String foodName;
    private double servings;
    private double calories;
    private double protein;
    private double sugar;

    public FoodLog(String foodName, double servings,
                   double calories, double protein, double sugar) {

        this.foodName = foodName;
        this.servings = servings;
        this.calories = calories;
        this.protein = protein;
        this.sugar = sugar;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getServings() {
        return servings;
    }

    public double getCalories() {
        return calories;
    }

    public double getProtein() {
        return protein;
    }

    public double getSugar() {
        return sugar;
    }
}