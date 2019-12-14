package com.example.overcooked.LevelThree;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * A FallingIngredient
 */
public class FallingIngredient implements GameObject {

    /**
     * A rectangle representing the FallingIngredient
     */
    private final Rect rectangle;

    /**
     * Return the rectangle
     *
     * @return the rectangle
     */
    Rect getRectangle() {
        return rectangle;
    }


    /**
     * Constructs a FallingIngredient at (startX, startY) with color
     *
     * @param startX the x-coordinate of the FallingIngredient
     * @param startY the y-coordinate of the FallingIngredient
     */
    public FallingIngredient(int startX, int startY) {
        this.rectangle = new Rect(startX - 50, startY, startX + 50, startY + 100);
    }

    /**
     * Increment the position of the rectangle by y.
     *
     * @param y the increment of the position on y-axis.
     */
    void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
    }

    /**
     * Determine whether the player receives the falling ingredient
     *
     * @param player the player.
     */
    boolean playerReceive(Player player) {
        return Rect.intersects(rectangle, player.getRect());
    }

    /**
     * Determine whether the FallingIngredient is out of the screen
     */
    boolean fallOut() {
        return this.rectangle.top >= LevelThreeConstants.SCREEN_HEIGHT;
    }

    /**
     * Draw the FallingIngredient on the screen
     *
     * @param canvas the canvas
     */
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(rectangle, paint);
    }

}
