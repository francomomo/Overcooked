package com.example.overcooked.LevelTwo;

import android.graphics.Bitmap;

import com.example.overcooked.Game;
import com.example.overcooked.Ingredient.IngredientFactory;
import com.example.overcooked.Ingredient.Ingredients;
import com.example.overcooked.Statistics.Statistics;

import java.util.ArrayList;

/**
 * Process the ingredient movement and update the data
 */
public abstract class LevelTwoGameManager implements iLevelTwoGameViewProcess {
    private final Game mContext;

    private final IngredientFactory ingredientFactory = new IngredientFactory();

    final ArrayList<Ingredients> fallenIngredients = new ArrayList<>();

    // User statistics
    final Statistics statistics;

    Ingredients currentIngredient;

    protected Bitmap background;

    LevelTwoGameManager(Game context, Statistics stat) {
        mContext = context;

        currentIngredient = ingredientFactory.getIngredientLV2(mContext);
        fallenIngredients.add(currentIngredient);
        // Dependency Injection
        statistics = stat;
        statistics.initialSetting();
    }

    public void reSet() {
        currentIngredient = ingredientFactory.getIngredientLV2(mContext);
        fallenIngredients.add(currentIngredient);
    }

    /**
     * Determine if the falling ingredient hit the base correctly
     */
    public abstract void hitBase();

    /**
     * Call this method when the player fail the game
     */
    public abstract void gameFail();

    /**
     * Call this method when the player finish one level
     */
    public abstract void gameSuccess();

    Ingredients getCurrentIngredient() {
        return currentIngredient;
    }

    ArrayList<Ingredients> getFallenIngredients() {
        return fallenIngredients;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public Game getmContext() {
        return mContext;
    }
}
