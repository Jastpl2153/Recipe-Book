package com.example.recipebook.model;

import java.util.ArrayList;
import java.util.List;

public class RecipeDataModel {
    private static RecipeDataModel instance;
    private List<Recipe> recipes;

    private RecipeDataModel() {
        recipes = new ArrayList<>();
    }

    public static RecipeDataModel getInstance() {
        if (instance == null) {
            instance = new RecipeDataModel();
        }
        return instance;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}

