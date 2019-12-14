package com.example.overcooked.LevelThree;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * A FallingIngredientManager
 */
class FallingIngredientManager {

    /**
     * An ArrayList of FallingIngredient's
     */
    private ArrayList<FallingIngredient> fallingIngredients;
    /**
     * The difficulty of the game
     */
    private Difficulty difficulty;

    private int score = 0;

    private int gameScore = 0;

    /**
     * Get the score
     */
    int getScore() {
        return score;
    }

    int getGameScore() {
        return gameScore;
    }

    /**
     * Set the score
     *
     * @param score the current score
     */
    void setScore(int score) {
        this.score = score;
    }

    void setGameScore(int score) {
        this.gameScore = score;
    }

    enum receivedTypes {GOOD, BAD, FAIL}

    /**
     * Construct a FallingIngredientManager
     *
     * @param difficulty the difficulty of the game
     */
    FallingIngredientManager(Difficulty difficulty) {
        fallingIngredients = new ArrayList<>();
        this.difficulty = difficulty;
        fallingIngredients = difficulty.getIngredients();
    }

    /**
     * Check whether the player collides with an ingredient
     *
     * @param player the current player
     * @return whether the player has collided with a FallingIngredient
     */
    receivedTypes playerCollide(Player player) {
        for (FallingIngredient fallingIngredient : fallingIngredients) {
            if (fallingIngredient.playerReceive(player) && fallingIngredient instanceof BadFallingIngredient) {
                return receivedTypes.BAD;
            } else if (fallingIngredient.playerReceive(player) && fallingIngredient instanceof FallingIngredient) {
                return receivedTypes.GOOD;
            }
        }
        return receivedTypes.FAIL;
    }

    /**
     * Check whether any FallingIngredient's fall out of the screen
     */
    receivedTypes fallOutScreen() {
        for (FallingIngredient i : fallingIngredients) {
            if (i.fallOut() && i instanceof BadFallingIngredient) {
                return receivedTypes.BAD;
            } else if (i.fallOut() && i instanceof FallingIngredient) {
                return receivedTypes.GOOD;
            }
        }
        return receivedTypes.FAIL;
    }

    /**
     * Generate new FallingIngredient when a FallingIngredient has been received by the player or fell out of the screen
     */
    private void generateRemovedRectangle() {
        fallingIngredients.add(0, difficulty.getRemovedIngredient());
    }

    /**
     * Remove FallingIngredient if it collides with the player
     */
    void removeTouchIngredient() {
        fallingIngredients.remove(fallingIngredients.size() - 1);
        generateRemovedRectangle();
    }

    /**
     * Draw the FallingIngredient's on the canvas
     *
     * @param canvas the canvas
     */
    void draw(Canvas canvas) {
        for (FallingIngredient ingredient : fallingIngredients) {
            ingredient.draw(canvas);
        }
        Paint paint = new Paint();
        paint.setTextSize(75);
        paint.setColor(Color.BLACK);
        canvas.drawText("Score: " + score, 50, 50 + paint.descent() - paint.ascent(), paint);
    }

    /**
     * Update the FallingIngredient's on the screen
     */
    void update() {
        float speed = difficulty.getSpeed();
        for (FallingIngredient ingredient : fallingIngredients) {
            ingredient.incrementY(speed);
        }
    }
}
