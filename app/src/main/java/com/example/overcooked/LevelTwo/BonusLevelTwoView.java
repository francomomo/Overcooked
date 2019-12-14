package com.example.overcooked.LevelTwo;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.overcooked.Ingredient.Ingredients;
import com.example.overcooked.R;


/**
 * A view for bonus level two
 */
public class BonusLevelTwoView extends LevelTwoGameView {

    final private BonusLevelTwoManager bonusLevelTwoManager;

    /**
     * Construct a new BonusLevelTwoView
     */
    public BonusLevelTwoView(LevelTwoGameManager bonusLevelTwoManager) {
        super(bonusLevelTwoManager);
        this.bonusLevelTwoManager = (BonusLevelTwoManager) bonusLevelTwoManager;
        setBackground();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvasWidth = getWidth();
        canvas.drawBitmap(background, 0, 0, null);
        if (bonusLevelTwoManager.getStatistics().getLives() > 0) {
            // Ingredients
            if (!bonusLevelTwoManager.getCurrentIngredient().isGoingDown()) {
                bonusLevelTwoManager.getCurrentIngredient().horizontalMove(canvasWidth);
            } else {
                bonusLevelTwoManager.hitBase();
                bonusLevelTwoManager.reSet();
            }
            // Draw update
            drawUpdate(canvas);
        }
        if (bonusLevelTwoManager.getStatistics().getLives() == 0) {
            // change user's data and go to fail activity;
            bonusLevelTwoManager.gameFail();

        } else if (bonusLevelTwoManager.getTargetNum() == 0) {
            // change user's data and go to success activity;
            bonusLevelTwoManager.gameSuccess();
        }
    }

    @Override
    public void drawBase(Canvas canvas) {
        bonusLevelTwoManager.getBase1().drawBase(canvas);
        bonusLevelTwoManager.getBase2().drawBase(canvas);
    }

    @Override
    public void drawIngredients(Canvas canvas) {
        for (Ingredients ingredient : bonusLevelTwoManager.getFallenIngredients()) {
            canvas.drawBitmap(ingredient.getImage(), ingredient.getX(), ingredient.getY(), null);
        }
    }

    @Override
    public void drawUpdate(Canvas canvas) {
        drawIngredients(canvas);
        drawBase(canvas);
        bonusLevelTwoManager.getStatistics().drawStatistics(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            bonusLevelTwoManager.getCurrentIngredient().setGoingDown(true);
        }
        return true;
    }

    public void setBackground() {
        if (((BonusLevelTwoActivity) bonusLevelTwoManager.getmContext()).getBackground().equals("grass")) {
            background = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
        } else if (((BonusLevelTwoActivity) bonusLevelTwoManager.getmContext()).getBackground().equals("tableware")) {
            background = BitmapFactory.decodeResource(getResources(), R.drawable.tableware);
        } else {
            background = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
        }
    }
}
