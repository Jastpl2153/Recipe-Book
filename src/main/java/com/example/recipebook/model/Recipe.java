package com.example.recipebook.model;


public class Recipe {
    private String title;

    private String typeOfMeal;

    private String typeOfFood;

    private String ingredients;

    private String instructions;

    private int id;

    public Recipe(String title, String typeOfMeal, String typeOfFood, String ingredients, String instructions) {
        this.title = title;
        this.typeOfMeal = typeOfMeal;
        this.typeOfFood = typeOfFood;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe(String title, String typeOfMeal, String typeOfFood, String ingredients, String instructions, int id) {
        this.title = title;
        this.typeOfMeal = typeOfMeal;
        this.typeOfFood = typeOfFood;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTypeOfMeal(String typeOfMeal) {
        this.typeOfMeal = typeOfMeal;
    }

    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n");
        sb.append("TypeOfMeal: ").append(typeOfMeal).append("\n");
        sb.append("TypeOfFood: ").append(typeOfFood).append("\n");
        sb.append("Ingredients: ").append(ingredients).append("\n");
        sb.append("Instructions: ").append(instructions).append("\n");
        sb.append("Id: ").append(id).append("\n");
        return sb.toString();
    }
}