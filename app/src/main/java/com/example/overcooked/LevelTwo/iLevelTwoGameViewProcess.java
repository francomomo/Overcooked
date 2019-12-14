package com.example.overcooked.LevelTwo;

interface iLevelTwoGameViewProcess {
    /**
     * reset the game
     */
    void reSet();

    /**
     * Determine if the falling ingredient hit the base and choose to keep or drop that ingredient
     */
    void hitBase();

    /**
     * When the player run out of life, go to the corresponding fail page and update statistics
     */
    void gameFail();

    /**
     * When the finish the game, go to the corresponding success page and update statistics
     */
    void gameSuccess();
}
