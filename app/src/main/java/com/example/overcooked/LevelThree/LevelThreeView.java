package com.example.overcooked.LevelThree;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.example.overcooked.Game;

class LevelThreeView extends View {
    /**
     * the current context of the game
     */
    private final LevelThreeActivity mContext;
    /**
     * the player
     */
    private final Player player;
    /**
     * the point representing the player location
     */
    private final Point playerPoint;
    /**
     * manages ingredient falling pattern
     */
    private final FallingIngredientManager fallingIngredientManager;

    private final int backgroundColor;

    /**
     * Construct a new LevelThreeView
     *
     * @param context         the current context
     * @param backgroundColor the background color
     * @param playerColor     the player color
     */
    LevelThreeView(Game context, int backgroundColor, int playerColor, String difficulty) {
        super(context);

        mContext = (LevelThreeActivity) this.getContext();

        player = new Player(new Rect(0, 0, 200, 200), playerColor);
        playerPoint = new Point(540, 1800);
        setFocusable(true);

        if (difficulty.equals("Hard")) {
            fallingIngredientManager = new FallingIngredientManager(new Hard());
        } else {
            fallingIngredientManager = new FallingIngredientManager(new Easy());
        }
        this.backgroundColor = backgroundColor;
    }

    /**
     * sets the user name, lives and score
     *
     * @param user  the username
     * @param lives number of lives the user has
     * @param score user score
     */
    void setUserStats(String user, int lives, int score) {
        player.setStats(user, lives);
        fallingIngredientManager.setScore(score);
    }

    /**
     * Moves the player to the right or left depending on which side of the screen
     * the user clicks on
     *
     * @param event stores user action
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:

                if (x > 540 && playerPoint.x < 810) {
                    playerPoint.x += 270;
                } else if (x < 540 && playerPoint.x > 270) {
                    playerPoint.x -= 270;
                }

        }
        return true;
    }

    private void updateView() {
        player.update(playerPoint);
        fallingIngredientManager.update();
        if (fallingIngredientManager.playerCollide(player) == FallingIngredientManager.receivedTypes.GOOD) {
            fallingIngredientManager.setScore(fallingIngredientManager.getScore() + 1);
            fallingIngredientManager.setGameScore(fallingIngredientManager.getGameScore() + 1);
            fallingIngredientManager.removeTouchIngredient();
        } else if (fallingIngredientManager.playerCollide(player) == FallingIngredientManager.receivedTypes.BAD) {
            fallingIngredientManager.setScore(fallingIngredientManager.getScore() - 10);
            fallingIngredientManager.removeTouchIngredient();
        }
        if (fallingIngredientManager.fallOutScreen() == FallingIngredientManager.receivedTypes.GOOD) {
            player.decLives();
            fallingIngredientManager.removeTouchIngredient();
        } else if (fallingIngredientManager.fallOutScreen() == FallingIngredientManager.receivedTypes.BAD) {
            fallingIngredientManager.removeTouchIngredient();
        }
        if (player.getLives() == 0) {
            mContext.gameFail(player.getUsername(), player.getLives(), fallingIngredientManager.getScore());
        }
        if (fallingIngredientManager.getGameScore() > 5) {
            mContext.gameSuccess(player.getUsername(), player.getLives(), fallingIngredientManager.getScore());
        }
    }

    /**
     * Draws the player and falling ingredients
     *
     * @param canvas the canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(backgroundColor);

        player.draw(canvas);
        fallingIngredientManager.draw(canvas);

        updateView();

    }

}
