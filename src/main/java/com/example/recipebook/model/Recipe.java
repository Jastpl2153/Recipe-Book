package com.example.recipebook.model;


public class Recipe {
    private String title;

    private String typeOfMeal;

    private String typeOfFood;

    private String ingredients;

    private String instructions;

    public Recipe(String title, String typeOfMeal, String typeOfFood, String ingredients, String instructions) {
        this.title = title;
        this.typeOfMeal = typeOfMeal;
        this.typeOfFood = typeOfFood;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe() {
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeOfMeal() {
        return typeOfMeal;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public String getTypeOfFood() {
        return typeOfFood;
    }

    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    // Override toString() method for easier debugging and printing
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n");
        sb.append("TypeOfMeal: ").append(typeOfMeal).append("\n");
        sb.append("TypeOfFood: ").append(typeOfFood).append("\n");
        sb.append("Ingredients: ").append(ingredients).append("\n");
        sb.append("Instructions: ").append(instructions).append("\n");
        return sb.toString();
    }
}