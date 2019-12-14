package com.example.overcooked.Ingredient;

import com.example.overcooked.Game;
import com.example.overcooked.LevelThree.BadFallingIngredient;
import com.example.overcooked.LevelThree.FallingIngredient;

import java.util.ArrayList;

public class IngredientFactory {
    // number of type of ingredient;
    final private int ingredientNum;

    public IngredientFactory() {
        // list of all ingredient;
        ArrayList<String> ingredientList = new ArrayList<>();
        ingredientList.add("tomato");
        ingredientList.add("bread");
        ingredientList.add("vegetable");
        ingredientList.add("beef");
        ingredientList.add("cheese");

        ingredientNum = ingredientList.size();

    }

    public Ingredients getIngredientLV2(Game subGame) {

        // Generate ingredient randomly;
        double d = Math.random();
        if (d < (float) 1 / ingredientNum) {
            return new Tomato(subGame);

        } else if (d < (float) 2 / ingredientNum) {
            return new Bread(subGame);

        } else if (d < (float) 3 / ingredientNum) {
            return new Vegetable(subGame);
        } else if (d < (float) 4 / ingredientNum) {
            return new Cheese(subGame);
        } else {
            return new Beef(subGame);
        }

    }

    /**
     * get a random ingredient for level three
     *
     * @param currY      the y location of the ingredient
     * @param difficulty the difficulty of the game
     */
    public FallingIngredient getIngredientLV3(int currY, String difficulty) {
        double d = Math.random();
        int xStart;
        if (d > 0.66) {
            xStart = 810;
        } else if (d > 0.33) {
            xStart = 540;
        } else {
            xStart = 270;
        }
        if (difficulty.equals("Hard")) {
            double l = Math.random();
            if (l > 0.5) {
                return new FallingIngredient(xStart, currY);
            } else {
                return new BadFallingIngredient(xStart, currY);
            }
        } else {
            return new FallingIngredient(xStart, currY);
        }
    }
}
