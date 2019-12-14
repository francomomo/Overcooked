package com.example.overcooked.Statistics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.overcooked.Game;
import com.example.overcooked.R;

import java.util.ArrayList;

import static com.example.overcooked.Game.retrieveInfo;

public class Statistics implements UpdateStatistics, DrawStatistics {

    private final String userName;
    private final Game subGame;

    //Score
    final private Paint scorePaint = new Paint();
    private int score;
    final private int originalScore;

    //level
    final private Paint levelPaint = new Paint();
    final private int level;

    //life
    final private ArrayList<Bitmap> life = new ArrayList<>(2);

    private int lives;

    public Statistics(String username, Game subGame) {
        //Initialize user's data;
        this.userName = username;
        this.subGame = subGame;
        originalScore = (int) retrieveInfo(username, Game.retrievedValue.POINTS);
        this.score = (int) retrieveInfo(username, Game.retrievedValue.POINTS);
        this.lives = (int) retrieveInfo(username, Game.retrievedValue.LIVES);
        this.level = (int) retrieveInfo(username, Game.retrievedValue.LEVEL);
    }

    private void setScoreFormat() {
        // score
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(32);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    private void setLevelFormat() {
        levelPaint.setColor(Color.DKGRAY);
        levelPaint.setTextSize(32);
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setAntiAlias(true);
    }

    private void setLivesFormat() {
        life.add(BitmapFactory.decodeResource(subGame.getResources(), R.drawable.heart));
        life.add(BitmapFactory.decodeResource(subGame.getResources(), R.drawable.heart_g));
    }

    public void initialSetting() {
        setScoreFormat();
        setLivesFormat();
        setLevelFormat();
    }

    public void drawStatistics(Canvas canvas) {
        canvas.drawText("Score : " + score, 20, 60, scorePaint);
        canvas.drawText("Lv." + level, 500, 60, levelPaint);
        if (lives == 3) {
            canvas.drawBitmap(life.get(0), 660, 30, null);
            canvas.drawBitmap(life.get(0), 720, 30, null);
            canvas.drawBitmap(life.get(0), 780, 30, null);
        } else if (lives == 2) {
            canvas.drawBitmap(life.get(0), 660, 30, null);
            canvas.drawBitmap(life.get(0), 720, 30, null);
            canvas.drawBitmap(life.get(1), 780, 30, null);
        } else if (lives == 1) {
            canvas.drawBitmap(life.get(0), 660, 30, null);
            canvas.drawBitmap(life.get(1), 720, 30, null);
            canvas.drawBitmap(life.get(1), 780, 30, null);
        } else if (lives == 0) {
            canvas.drawBitmap(life.get(1), 660, 30, null);
            canvas.drawBitmap(life.get(1), 720, 30, null);
            canvas.drawBitmap(life.get(1), 780, 30, null);
        }
    }

    public int getLives() {
        return lives;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public String getUsername() {
        return userName;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateSuccess(int level) {
        Game.storeInfo(userName, Game.retrievedValue.LIVES, lives);
        Game.storeInfo(userName, Game.retrievedValue.POINTS, score);
        Game.storeInfo(userName, Game.retrievedValue.LEVEL, level);
    }

    public void updateFail(int level) {
        Game.storeInfo(userName, Game.retrievedValue.LIVES, lives);
        Game.storeInfo(userName, Game.retrievedValue.POINTS, originalScore);
        Game.storeInfo(userName, Game.retrievedValue.LEVEL, level);
    }
}