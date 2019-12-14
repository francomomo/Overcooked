package com.example.overcooked.Ingredient;

interface MovementStrategy {
    void verticalMove(Ingredients ingredient, int height);

    void horizontalMove(Ingredients ingredient, int width);

    boolean hitTarget(Ingredients ingredient, int targetX, int targetY);

}
