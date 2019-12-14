package com.example.overcooked.LevelThree;

import com.example.overcooked.Ingredient.IngredientFactory;

import java.util.ArrayList;

abstract class Difficulty {
    private final IngredientFactory ingredientFactory = new IngredientFactory();
    private final ArrayList<FallingIngredient> ingredients = new ArrayList<>();

    IngredientFactory getIFactory() {
        return ingredientFactory;
    }

    ArrayList<FallingIngredient> getFI() {
        return ingredients;
    }

    abstract float getSpeed();

    abstract ArrayList<FallingIngredient> getIngredients();

    abstract FallingIngredient getRemovedIngredient();

}
