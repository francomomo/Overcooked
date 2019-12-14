package com.example.overcooked.LevelThree;

import java.util.ArrayList;

class Hard extends Difficulty {
    /**
     * The gap between ingredients
     */
    private final int gap;

    /**
     * The current time of the game
     */
    private long startTime;
    /**
     * The time when the game is initialized
     */
    private final long initTime;

    Hard() {
        startTime = initTime = System.currentTimeMillis();
        this.gap = 800;
    }

    /**
     * the speed increases as the game progresses
     */
    float getSpeed() {
        int elapseTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed =
                (float)
                        (Math.sqrt(10 + (startTime - initTime) / 150.0) * LevelThreeConstants.SCREEN_HEIGHT / 10000.0f);
        return (speed * elapseTime);
    }

    /**
     * getting an ingredients from the ingredient factory
     */
    ArrayList<FallingIngredient> getIngredients() {
        int currY = -5 * 2160 / 4;
        while (currY < 0) {
            super.getFI().add(super.getIFactory().getIngredientLV3(currY, "Hard"));
            currY += gap + 300;
        }
        return super.getFI();
    }

    /**
     * getting an ingredient from the ingredient factory when an ingredient is removed
     */
    FallingIngredient getRemovedIngredient() {
        return super.getIFactory().getIngredientLV3(super.getFI().get(0).getRectangle().top - 100 - gap, "Hard");
    }
}
