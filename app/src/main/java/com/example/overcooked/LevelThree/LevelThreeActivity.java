package com.example.overcooked.LevelThree;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.example.overcooked.Game;
import com.example.overcooked.GeneralFailActivity;

import java.util.Timer;
import java.util.TimerTask;

public class LevelThreeActivity extends Game {

    /**
     * the content view of LevelThreeActivity
     */
    private LevelThreeView mView;

    /**
     * the time interval between each timer task
     */
    private final static long TIMER_INTERVAL = 1;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        LevelThreeConstants.SCREEN_WIDTH = dm.widthPixels;
        LevelThreeConstants.SCREEN_HEIGHT = dm.heightPixels;

        Intent intent = getIntent();
        Bundle stats = intent.getExtras();
        String name = stats.getString("username");
        String difficulty = stats.getString("difficulty");
        int backgroundColor = stats.getInt("background_color");
        int playerColor = stats.getInt("player_color");
        int lives = (int) retrieveInfo(name, retrievedValue.LIVES);
        int score = (int) retrieveInfo(name, retrievedValue.POINTS);

        mView = new LevelThreeView(this, backgroundColor, playerColor, difficulty);
        setContentView(mView);
        mView.setUserStats(name, lives, score);

        timer = new Timer();
        class RedrawTimerTask extends TimerTask {
            @Override
            public void run() {
                mView.invalidate(); //calls onDraw again
            }
        }
        TimerTask redraw = new RedrawTimerTask();

        timer.schedule(redraw, 0, TIMER_INTERVAL);
    }


    /**
     * stores user info and navigates to fail screen
     */
    void gameFail(String username, int lives, int score) {
        timer.cancel();
        storeInfo(username, retrievedValue.LEVEL, 1);
        storeInfo(username, retrievedValue.POINTS, score);
        storeInfo(username, retrievedValue.LIVES, lives);
        startIntent(this, GeneralFailActivity.class, username);
    }

    /**
     * stores user info and navigates to success screen
     */
    void gameSuccess(String username, int lives, int score) {
        timer.cancel();
        Intent intent = new Intent(this, LevelThreeSuccessActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("lives", lives);
        intent.putExtra("score", score);
        storeInfo(username, retrievedValue.LEVEL, 1);
        storeInfo(username, retrievedValue.POINTS, score);
        storeInfo(username, retrievedValue.LIVES, lives);
        startActivity(intent);
    }

}
