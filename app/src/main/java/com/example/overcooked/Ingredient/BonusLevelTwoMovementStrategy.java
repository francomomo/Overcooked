package com.example.overcooked.Ingredient;

/**
 * Movement Strategy for BonusLevelTwo
 */
public class BonusLevelTwoMovementStrategy implements MovementStrategy {

    /**
     * Move the ingredients vertically
     */
    @Override
    public void verticalMove(Ingredients ingredient, int height) {
        ingredient.setY(height);
        ingredient.setGoingDown(false);
    }

    /**
     * Move the ingredients horizontally
     */
    @Override
    public void horizontalMove(Ingredients ingredient, int width) {
        if (ingredient.isGoingRight() && !ingredient.isGoingDown() && !ingredient.isFallen()) {
            //going right
            ingredient.setX(ingredient.getX() + ingredient.getSpeed());
            if (ingredient.getX() > width) {
                ingredient.setGoingRight(false);
                ingredient.setSpeed(ingredient.getSpeed() + 2);
            }
        } else if (!ingredient.isGoingRight() && !ingredient.isGoingDown() && !ingredient.isFallen()) {
            // going left;
            ingredient.setX(ingredient.getX() - ingredient.getSpeed());
            if (ingredient.getX() < 0) {
                ingredient.setGoingRight(true);
                ingredient.setSpeed(ingredient.getSpeed() + 2);
            }
        }
    }

    /**
     * Determine if the fallen ingredient hit the target
     */
    @Override
    public boolean hitTarget(Ingredients ingredient, int targetX, int TargetY) {
        if ((ingredient.getX() - targetX) < 80 && (ingredient.getX() - targetX) > -80) {
            ingredient.setFallen(true);
            return true;
        } else {
            ingredient.setFallen(true);
            return false;
        }
    }

}
