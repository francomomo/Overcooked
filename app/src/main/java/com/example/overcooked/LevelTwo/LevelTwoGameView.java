package com.example.overcooked.LevelTwo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;


/**
 * An abstract class that each subGame of level two should inherit
 */
public abstract class LevelTwoGameView extends View implements iLevelTwoGameViewDraw {

    //Canvas
    int canvasWidth;

    Bitmap background;

    LevelTwoGameView(LevelTwoGameManager levelTwoGameManager) {
        super(levelTwoGameManager.getmContext());
    }

    /**
     * Draw on the canvas
     */
    protected abstract void onDraw(Canvas canvas);

    @Override
    public abstract void drawIngredients(Canvas canvas);

    @Override
    public abstract void drawBase(Canvas canvas);

    @Override
    public abstract void drawUpdate(Canvas canvas);

    public abstract void setBackground();

}