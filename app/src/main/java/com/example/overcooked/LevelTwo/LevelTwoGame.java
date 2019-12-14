package com.example.overcooked.LevelTwo;

/**
 * A LevelTwo Game activity interface
 */
public interface LevelTwoGame {
    /**
     * Go to the corresponding fail page
     */
    void navigateFail();

    /**
     * Go to the corresponding Success page
     */
    void navigateSuccess();

    /**
     * Get the username of th current player
     */
    String getUsername();

    String getDifficulty();

    String getBackground();
}
