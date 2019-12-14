package com.example.overcooked.LevelTwo;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.overcooked.Ingredient.Ingredients;
import com.example.overcooked.R;


public class LevelTwoView extends LevelTwoGameView {

    final private LevelTwoManager levelTwoManager;

    public LevelTwoView(LevelTwoGameManager levelTwoManager) {
        super(levelTwoManager);
        this.levelTwoManager = (LevelTwoManager) levelTwoManager;
        setBackground();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvasWidth = getWidth();
        canvas.drawBitmap(background, 0, 0, null);
        if (levelTwoManager.getStatistics().getLives() > 0) {
            // Ingredients
            if (!levelTwoManager.getCurrentIngredient().isGoingDown()) {
                levelTwoManager.getCurrentIngredient().horizontalMove(canvasWidth);
            } else {
                levelTwoManager.hitBase();
                levelTwoManager.reSet();
            }
            // Draw update
            drawUpdate(canvas);
        }
        if (levelTwoManager.getStatistics().getLives() == 0) {
            // change user's data and go to fail activity;
            levelTwoManager.gameFail();
        } else if (levelTwoManager.getTargetNum() == 0) {
            // change user's data and go to success activity;
            levelTwoManager.gameSuccess();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            levelTwoManager.getCurrentIngredient().setGoingDown(true);
        }
        return true;
    }

    /**
     * Set the background and the default setting is sky
     */
    public void setBackground() {
        if (((LevelTwoMainActivity) levelTwoManager.getmContext()).getBackground().equals("grass")) {
            background = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
        } else if (((LevelTwoMainActivity) levelTwoManager.getmContext()).getBackground().equals("tableware")) {
            background = BitmapFactory.decodeResource(getResources(), R.drawable.tableware);
        } else {
            background = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
        }
    }

    @Override
    public void drawIngredients(Canvas canvas) {
        for (Ingredients ingredient : levelTwoManager.getFallenIngredients()) {
            canvas.drawBitmap(ingredient.getImage(), ingredient.getX(), ingredient.getY(), null);
        }
    }

    @Override
    public void drawBase(Canvas canvas) {
        levelTwoManager.getBase().drawBase(canvas);
    }

    @Override
    public void drawUpdate(Canvas canvas) {
        drawIngredients(canvas);
        drawBase(canvas);
        levelTwoManager.getStatistics().drawStatistics(canvas);
    }


}
