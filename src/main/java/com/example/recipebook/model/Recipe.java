package com.example.recipebook.model;

import lombok.Data;

@Data
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