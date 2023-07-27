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

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getTypeOfMeal() {
        return typeOfMeal;
    }

    public String getTypeOfFood() {
        return typeOfFood;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

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