package com.example.overcooked.LevelThree;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * A player
 */
class Player implements GameObject {

    /**
     * A rectangle representing the player
     */
    private final Rect rectangle;
    /**
     * The color of the rectangle
     */
    private final int color;
    /**
     * The username
     */
    private String username;
    /**
     * User's lives
     */
    private int lives;

    /**
     * Construct a new player
     *
     * @param rectangle a rectangle
     * @param color     the color of the rectangle
     */
    Player(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
        this.username = "";
        this.lives = 3;
    }

    /**
     * Get the rectangle
     */
    Rect getRect() {
        return this.rectangle;
    }

    /**
     * Get the player's lives
     */
    int getLives() {
        return this.lives;
    }

    /**
     * Decrease player's lives
     */
    void decLives() {
        this.lives -= 1;
    }

    /**
     * Get player's username
     */
    String getUsername() {
        return this.username;
    }

    /**
     * Set the player's statistics
     */
    void setStats(String name, int lives) {
        this.username = name;
        this.lives = lives;
    }

    /**
     * Draw the player on the canvas
     *
     * @param canvas the canvas
     */
    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
        Paint paint2 = new Paint();
        paint2.setTextSize(75);
        paint2.setColor(Color.BLACK);
        canvas.drawText("Lives: " + lives, 650, 50 + paint2.descent() - paint2.ascent(), paint2);
    }

    /**
     * Update the player's position
     *
     * @param point the player's point
     */
    void update(Point point) {
        rectangle.set(
                point.x - rectangle.width() / 2,
                point.y - rectangle.height() / 2,
                point.x + rectangle.width() / 2,
                point.y + rectangle.height() / 2);
    }
}
