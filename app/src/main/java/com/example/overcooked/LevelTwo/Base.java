package com.example.overcooked.LevelTwo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.overcooked.Game;
import com.example.overcooked.R;

/**
 * Help class for thr base element in levelTwo
 */

class Base {
    /**
     * x coordinate and y coordinate of the base
     */
    private final int x;
    private final int y;

    /**
     * Image of the base;
     * default image is a bread
     */
    private final Bitmap image;

    Base(Game context, int x, int y) {
        this.x = x;
        this.y = y;
        this.image = BitmapFactory.decodeResource(context.getResources(), R.drawable.bread2);
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    /**
     * Draw the image of base
     */
    void drawBase(Canvas canvas) {
        canvas.drawBitmap(this.image, this.x, this.y, null);
    }
}
