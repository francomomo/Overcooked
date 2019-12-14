package com.example.overcooked.LevelThree;

import java.util.ArrayList;

class Easy extends Difficulty {
    /**
     * the ingredient falling speed
     */
    private final float speed;
    /**
     * the distance between each falling ingredient
     */
    private final int gap;

    Easy() {
        this.speed = 20;
        this.gap = 1500;
    }

    float getSpeed() {
        return this.speed;
    }

    /**
     * getting an ingredients from the ingredient factory
     */
    ArrayList<FallingIngredient> getIngredients() {
        int currY = -5 * 2160 / 4;
        while (currY < 0) {
            super.getFI().add(super.getIFactory().getIngredientLV3(currY, "Easy"));
            currY += gap + 300;
        }
        return super.getFI();
    }

    /**
     * getting an ingredient from the ingredient factory when an ingredient is removed
     */
    FallingIngredient getRemovedIngredient() {
        return super.getIFactory().getIngredientLV3(super.getFI().get(0).getRectangle().top - 100 - gap, "Easy");
    }
}
