package com.example.overcooked.LevelTwo;

import com.example.overcooked.Game;
import com.example.overcooked.Ingredient.Beef;
import com.example.overcooked.Ingredient.Bread;
import com.example.overcooked.Ingredient.Cheese;
import com.example.overcooked.Ingredient.Ingredients;
import com.example.overcooked.Ingredient.Tomato;
import com.example.overcooked.Ingredient.Vegetable;
import com.example.overcooked.Statistics.Statistics;

/**
 * Process the movement of the ingredient and update the data
 */
public class BonusLevelTwoManager extends LevelTwoGameManager {
    private final BonusLevelTwoActivity mContext;

    /**
     * Initial height of two base;
     */
    private int currentHeight1 = 1620;
    private int currentHeight2 = 1620;

    /**
     * Target number of this level;
     */
    private int targetNum = 10;

    /**
     * Two bases;
     */
    private final Base base1;
    private final Base base2;

    /**
     * Construct a new BonusLevelTwoManager
     *
     * @param context the current context
     */
    public BonusLevelTwoManager(Game context, Statistics stat) {
        super(context, stat);
        mContext = (BonusLevelTwoActivity) context;
        base1 = new Base(mContext, 230, 1650);
        base2 = new Base(mContext, 700, 1650);
    }

    /**
     * Determine if the ingredient falls on the base when the user touches the screen;
     * and update the result.
     */
    @Override
    public void hitBase() {
        if (currentIngredient.hitTarget(base1.getX(), base1.getY())) {
            if (currentIngredient instanceof Tomato || currentIngredient instanceof Vegetable) {
                currentHeight1 = recordSuccess(currentHeight1);
            } else {
                statistics.setLives(statistics.getLives() - 1);
                fallenIngredients.remove(currentIngredient);
            }
        } else if (currentIngredient.hitTarget(base2.getX(), base2.getY())) {
            if (currentIngredient instanceof Beef || currentIngredient instanceof Cheese) {
                currentHeight2 = recordSuccess(currentHeight2);
            } else {
                statistics.setLives(statistics.getLives() - 1);
                fallenIngredients.remove(currentIngredient);
            }
        } else if (currentIngredient instanceof Bread) {
            statistics.setScore(statistics.getScore() + 5);
            fallenIngredients.remove(currentIngredient);
        } else {
            statistics.setLives(statistics.getLives() - 1);
            fallenIngredients.remove(currentIngredient);
        }

    }

    /**
     * When the game fails, update the user statistics and navigate to LevelTwoFailActivity;
     */
    @Override
    public void gameFail() {
        statistics.updateFail(2);
        mContext.navigateFail();
    }

    /**
     * When the game success, update the user statistics and navigate to LevelTwoSuccessActivity;
     */
    @Override
    public void gameSuccess() {
        if (statistics.getLives() < 3) {
            statistics.setLives(statistics.getLives() + 1);
        }
        statistics.updateSuccess(3);
        mContext.navigateSuccess();
    }

    /**
     * Update the state of successfully-fallen ingredient and update the canvas information;
     *
     * @param currentHeight current height of the pile of the fallen ingredient
     * @return current height of the pile of the fallen ingredient;
     */
    private int recordSuccess(int currentHeight) {
        currentIngredient.verticalMove(currentHeight - currentIngredient.getSize());
        currentHeight -= currentIngredient.getSize();
        statistics.setScore(statistics.getScore() + 10);
        targetNum -= 1;
        return currentHeight;
    }

    int getTargetNum() {
        return targetNum;
    }

    Base getBase2() {
        return base2;
    }

    Base getBase1() {
        return base1;
    }
}
