package com.example.overcooked.LevelTwo;

import com.example.overcooked.Game;
import com.example.overcooked.Statistics.Statistics;

public class LevelTwoManager extends LevelTwoGameManager {
    private final LevelTwoMainActivity mContext;

    //Canvas
    private int currentHeight = 1620;

    private int targetNum = 10;

    //Base
    private final Base base;

    public LevelTwoManager(Game context, Statistics stat) {
        super(context, stat);
        mContext = (LevelTwoMainActivity) context;
        base = new Base(mContext, 480, 1650);
    }

    @Override
    public void hitBase() {
        currentIngredient.verticalMove(currentHeight - currentIngredient.getSize());
        if (currentIngredient.hitTarget(base.getX(), base.getY())) {
            currentHeight -= currentIngredient.getSize();
            statistics.setScore(statistics.getScore() + 10);
            targetNum -= 1;
        } else {
            statistics.setLives(statistics.getLives() - 1);
            fallenIngredients.remove(currentIngredient);
        }
    }

    /**
     * Update the failure information and go the fail page
     */
    @Override
    public void gameFail() {
        statistics.updateFail(2);
        mContext.navigateFail();
    }

    /**
     * Update the success information and go the success page
     */
    @Override
    public void gameSuccess() {
        statistics.updateSuccess(3);
        mContext.navigateSuccess();
    }

    Base getBase() {
        return base;
    }

    int getTargetNum() {
        return targetNum;
    }
}
