package com.example.overcooked.LevelOne;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.overcooked.Game;
import com.example.overcooked.GeneralFailActivity;
import com.example.overcooked.GeneralSuccessActivity;

import java.util.Locale;


public class LevelOneActivity extends Game {

    private int score;
    private String username;
    private int lives;

    private BackgroundFactory backgroundFactory;
    private ScoreFactory scoreFactory;

    private TextView countdownText;
    private CountDownTimer mCountdownTimer;

    private ImageView background;

    private long timeLeftInMilliseconds = 30000; // 30 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.example.overcooked.R.layout.activity_level_one);

        // Level 1 score starts at 0 for all cases, regardless of new game or resume game.

        score = 0;

        background = findViewById(com.example.overcooked.R.id.background);

        countdownText = findViewById(com.example.overcooked.R.id.countdown_text);

        username = retrieveUsername();

        startTimer();

        // Extracts information from external storage using the username.*/
        lives = (int) retrieveInfo(username, retrievedValue.LIVES);

        backgroundFactory = new BackgroundFactory();
        scoreFactory = new ScoreFactory();

        String L1BG = (String)retrieveInfo(username, retrievedValue.L1BG);
        background.setTag(L1BG);
        int id = getResources().getIdentifier(L1BG, "drawable", getPackageName());
        background.setImageResource(id);
    }

    /**
     * Begins a countdown from a pre-specified time when called.
     * The user must have > 0 lives when the timer finishes, otherwise loseLife will navigate to a
     * fail screen.
     * This method updates the user's information stored in external storage and
     * brings the app to a successful level end screen.
     */
    private void startTimer() {
        mCountdownTimer = new CountDownTimer(timeLeftInMilliseconds, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilliseconds = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                mCountdownTimer.cancel();
                storeInfo(username, retrievedValue.LIVES, lives);
                storeInfo(username, retrievedValue.POINTS, score);
                storeInfo(username, retrievedValue.LEVEL, 2);
                startIntent(LevelOneActivity.this, GeneralSuccessActivity.class, username);
            }
        }.start();
    }

    /**
     * Helper method to update the TextView that displays remaining time.
     */
    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMilliseconds / 1000) / 60;
        int seconds = (int) (timeLeftInMilliseconds / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countdownText.setText(timeLeftFormatted);
    }

    /**
     * Called with onClick method of food ImageViews in the related XML file.
     * Increments the user's score and updates the TextView displaying this score.
     */
    public void addScore(View view) {
        String foodString = String.valueOf(view.getTag());

        score += scoreFactory.seeScore(foodString);

        String textScore = String.valueOf(score);
        updateTextView(textScore);

        if (score == 207) {
            mCountdownTimer.cancel();
            storeInfo(username, retrievedValue.LIVES, lives);
            storeInfo(username, retrievedValue.POINTS, score);
            storeInfo(username, retrievedValue.LEVEL, 2);
            startIntent(LevelOneActivity.this, GeneralSuccessActivity.class, username);
        }
    }

    /**
     * Called with onClick method of layout ImageView in the related XML file.
     * Removes one life from the user and updates the graphics displaying remaining lives.
     */
    public void loseLife(View view) {
        lives -= 1;
        updateImageView(lives);

        if (lives == 0) {
            mCountdownTimer.cancel();
            storeInfo(username, retrievedValue.LEVEL, 1);
            storeInfo(username, retrievedValue.LIVES, 0);
            storeInfo(username, retrievedValue.POINTS, score);
            startIntent(this, GeneralFailActivity.class, username);
        }
    }

    /**
     * Called with onClick method of buyTime button in the related XML file.
     * Removes one life from the user and adds 5 seconds to the remaining time.
     */
    public void addTime(View view) {
        mCountdownTimer.cancel();
        mCountdownTimer = null;
        timeLeftInMilliseconds += 5000;
        startTimer();
        loseLife(view);
    }

    /**
     * Called with onClick method of changeBackground button in the related XML file.
     * Cycles through pre-specified background images, allowing for user customisation.
     */
    public void updateBackground(View view) {
        String backgroundImage = String.valueOf(background.getTag());

        String newBackground = backgroundFactory.newBackground(backgroundImage);
        background.setTag(newBackground);
        storeInfo(username, retrievedValue.L1BG, newBackground);

        int id = getResources().getIdentifier(newBackground, "drawable", getPackageName());
        background.setImageResource(id);
    }

    /**
     * Helper method for updating the TextView displaying user's score.
     */
    private void updateTextView(String newScore) {
        TextView textView = findViewById(com.example.overcooked.R.id.scoreCount);
        textView.setText(newScore);
    }

    /**
     * Helper method for updating the ImageView displaying remaining lives.
     */
    private void updateImageView(int livesRemaining) {
        ImageView imageView = findViewById(com.example.overcooked.R.id.livesImage);
        if (livesRemaining == 2) {
            imageView.setImageResource(com.example.overcooked.R.drawable.lives2);
        } else if (livesRemaining == 1) {
            imageView.setImageResource(com.example.overcooked.R.drawable.lives3);
        } else {
            imageView.setImageResource(com.example.overcooked.R.drawable.lives4);
        }
    }

}
