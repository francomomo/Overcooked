package com.example.overcooked.MainMenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.overcooked.R;
import com.example.overcooked.Scoreboard;

public class UserStatistics extends MainActivity {
    //user stats
    private TextView level, lives, points;
    private String user = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        getIntent();

        Button back = findViewById(R.id.back);
        Button leaderBoard = findViewById(R.id.leaderboard);
        Button clear = findViewById(R.id.clear);
        TextView username = findViewById(R.id.username);
        username.setTextColor(Color.WHITE);
        level = findViewById(R.id.level);
        level.setTextColor(Color.WHITE);
        lives = findViewById(R.id.lives);
        lives.setTextColor(Color.WHITE);
        points = findViewById(R.id.points);
        points.setTextColor(Color.WHITE);

        // Get the Intent that started this activity and extract the string (username)
        user = retrieveUsername();
        username.setText(user);

        getStats();

        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startIntent(UserStatistics.this, MainMenuActivity.class, user);
                    }
                }
        );

        leaderBoard.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startIntent(UserStatistics.this, Scoreboard.class, user);
                    }
                }
        );

        clear.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        askClear();
                    }
                }
        );
    }

    /**
     * retrieves stats of the user
     */
    private void getStats() {
        StringBuilder JSONLives = new StringBuilder("lives: " + (int) retrieveInfo(user, retrievedValue.LIVES));
        lives.setText(JSONLives);
        StringBuilder JSONPoints = new StringBuilder("points: " + (int) retrieveInfo(user, retrievedValue.POINTS));
        points.setText(JSONPoints);
        StringBuilder JSONLevel = new StringBuilder("level: " + (int) retrieveInfo(user, retrievedValue.LEVEL));
        level.setText(JSONLevel);
    }

    /**
     * creates an alert box that asks if you're sure to reset stats
     */
    private void askClear() {
        AlertDialog.Builder alertBox = new AlertDialog.Builder(UserStatistics.this);
        alertBox.setMessage("Are you sure you want to reset statistics? This action cannot be reversed.");
        alertBox.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface argument1, int argument2) {
                        storeInfo(user, retrievedValue.LEVEL, 1);
                        storeInfo(user, retrievedValue.POINTS, 0);
                        storeInfo(user, retrievedValue.LIVES, 3);
                        startIntent(UserStatistics.this, UserStatistics.class, user);
                    }
                });
        alertBox.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface argument1, int argument2) {
                        System.out.println("not deleted");
                    }
                });
        AlertDialog alertDialog = alertBox.create();
        alertDialog.show();
    }
}