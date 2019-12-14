package com.example.overcooked.Ingredient;

import com.example.overcooked.LevelTwo.*;

import android.graphics.Bitmap;

import com.example.overcooked.Game;


public abstract class Ingredients {

    private int x;
    private int y;

    private int speed;
    // true for going right, false for going left
    private boolean goingRight;

    // Determine if this ingredient has fallen or not;
    // true means it has fallen successfully and false means it hasn't fallen or failed to fall;
    private boolean goingDown;
    private boolean fallen;
    private MovementStrategy strategy;

    final Game mContext;


    Ingredients(Game context) {
        this.x = 0;
        this.y = 0;
        this.fallen = false;
        this.speed = 15;
        this.goingDown = false;

        //set initial goingRight
        double d = Math.random();
        if (d < 0.5) {
            goingRight = true; // going right
            this.x = 0;
            this.y = (int) (Math.random() * 1000 + 50);
        } else {
            goingRight = false; // going left
            this.y = (int) (Math.random() * 1000 + 50);
            this.x = 1600;
        }
        this.mContext = context;
        setStrategy();
        setSpeed();
    }

    private void setStrategy() {
        if (mContext instanceof LevelTwoMainActivity) {
            strategy = new LevelTwoMovementStrategy();
        } else if (mContext instanceof BonusLevelTwoActivity) {
            strategy = new BonusLevelTwoMovementStrategy();
        }
        // Add new level strategy
    }

    void setY(int y) {
        this.y = y;
    }

    void setX(int x) {
        this.x = x;
    }

    public abstract void setImage();

    public abstract void setName();

    public abstract void setSize();

    private void setSpeed() {
        if (this.mContext instanceof LevelTwoGame) {
            // Dependence inversion
            if (((LevelTwoGame) this.mContext).getDifficulty().equals("medium")) {
                this.speed = 16;
            } else if (((LevelTwoGame) this.mContext).getDifficulty().equals("hard")) {
                this.speed = 20;
            } else {
                this.speed = 12;
            }
        } else {
            this.speed = 0;
        }
    }

    void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }

    public void setGoingDown(boolean goingDown) {
        this.goingDown = goingDown;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    int getSpeed() {
        return speed;
    }

    public abstract int getSize();

    public abstract Bitmap getImage();

    public abstract String getName();

    boolean isGoingRight() {
        return goingRight;
    }

    boolean isFallen() {
        return fallen;
    }

    void setFallen(boolean fallen) {
        this.fallen = fallen;
    }

    void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    public boolean isGoingDown() {
        return goingDown;
    }

    public void horizontalMove(int width) {
        strategy.horizontalMove(this, width);
    }

    public void verticalMove(int height) {
        strategy.verticalMove(this, height);
    }

    public boolean hitTarget(int targetX, int targetY) {
        return strategy.hitTarget(this, targetX, targetY);
    }
}
