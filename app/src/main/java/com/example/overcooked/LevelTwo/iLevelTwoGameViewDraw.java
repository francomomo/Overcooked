package com.example.overcooked.LevelTwo;

import android.graphics.Canvas;

interface iLevelTwoGameViewDraw {
    void drawBase(Canvas canvas);

    void drawIngredients(Canvas canvas);

    /**
     * Draw the ingredients, base and statistics
     */
    void drawUpdate(Canvas canvas);
}
