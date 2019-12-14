package com.example.overcooked.MainMenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.overcooked.LevelOne.LevelOneActivity;
import com.example.overcooked.LevelThree.LevelThreeMainActivity;
import com.example.overcooked.LevelTwo.LevelTwoCustomization;
import com.example.overcooked.R;
import com.example.overcooked.Scoreboard;

import java.io.File;

public class MainMenuActivity extends MainActivity {
    //main menu

    private TextView status;
    private ImageView apron;
    private ImageView hat;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getIntent();

        TextView oldUser = findViewById(R.id.oldUser);
        Button newGame = findViewById(R.id.newGame);
        Button resumeGame = findViewById(R.id.resumeGame);
        Button statistics = findViewById(R.id.statistics);
        Button logOut = findViewById(R.id.logout);
        Button delete = findViewById(R.id.delete);
        Button leaderBoard = findViewById(R.id.leaderboard);
        Button settings = findViewById(R.id.settings);
        apron = findViewById(R.id.apron);
        hat = findViewById(R.id.hat);

        status = findViewById(R.id.status);
        status.setTextColor(Color.WHITE);
        oldUser.setTextColor(Color.WHITE);

        // Get the Intent that started this activity and extract the string (username)
        username = retrieveUsername();
        oldUser.setText(username);

        newGame.setOnClickListener( // starts new game
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        storeInfo(username, retrievedValue.POINTS, 0);
                        storeInfo(username, retrievedValue.LIVES, 3);
                        storeInfo(username, retrievedValue.LEVEL, 1);
                        resumeGame(username);
                    }
                }
        );

        resumeGame.setOnClickListener( //resumes game
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        resumeGame(username);
                    }
                }
        );

        statistics.setOnClickListener( //view stats
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startIntent(MainMenuActivity.this, UserStatistics.class, username);
                    }
                }
        );

        logOut.setOnClickListener( //log out
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startIntent(MainMenuActivity.this, com.example.overcooked.MainMenu.MainActivity.class, username);
                    }
                }
        );

        leaderBoard.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startIntent(MainMenuActivity.this, Scoreboard.class, username);
                    }
                }
        );

        delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        askDelete();
                    }
                }
        );

        settings.setOnClickListener( //log out
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startIntent(MainMenuActivity.this, Settings.class, username);
                    }
                }
        );

        setBackground();
    }

    /**
     * resumes game
     *
     * @param username username of the account.
     */
    private void resumeGame(String username) {
        int tempLives = (int) retrieveInfo(username, retrievedValue.LIVES);
        int tempLevel = (int) retrieveInfo(username, retrievedValue.LEVEL);
        if (tempLevel == 1 && tempLives > 0) {
            startIntent(MainMenuActivity.this, LevelOneActivity.class, username);
        } else if (tempLevel == 2 && tempLives > 0) {
            startIntent(MainMenuActivity.this, LevelTwoCustomization.class, username);
        } else if (tempLevel == 3 && tempLives > 0) {
            startIntent(MainMenuActivity.this, LevelThreeMainActivity.class, username);
        } else {
            status.setText(R.string.noMoreLives);
        }
    }

    /**
     * @param username username of account
     */
    private void deleteAccount(String username) {
        String fileName = username + ".json";
        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Overcooked", fileName);
        if (directory.delete()) {
            System.out.println("File Deleted");
        } else {
            System.out.println("Failed to delete.");
        }
    }

    /**
     * creates an alert box that asks if you're sure to delete account
     */
    private void askDelete() {
        AlertDialog.Builder alertBox = new AlertDialog.Builder(MainMenuActivity.this);
        alertBox.setMessage("Are you sure you want to delete? This action cannot be reversed.");
        alertBox.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface argument1, int argument2) {
                        deleteAccount(username);
                        Intent intent = new Intent(MainMenuActivity.this, com.example.overcooked.MainMenu.MainActivity.class);
                        startActivity(intent);
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

    private void setBackground(){
        if (retrieveInfo(username, retrievedValue.MAINMENU)!=null) {
            String background = (String) retrieveInfo(username, retrievedValue.MAINMENU);
            if (background != null) {
                if (background.equals("white")) {
                    apron.setImageResource(R.drawable.apronwhite);
                    hat.setImageResource(R.drawable.hatwhite);
                }
                if (background.equals("black")) {
                    apron.setImageResource(R.drawable.apronblack);
                    hat.setImageResource(R.drawable.hatblack);
                }
                if (background.equals("blue")) {
                    apron.setImageResource(R.drawable.apronblue);
                    hat.setImageResource(R.drawable.hatblue);
                }
                if (background.equals("green")) {
                    apron.setImageResource(R.drawable.aprongreen);
                    hat.setImageResource(R.drawable.hatgreen);
                }
                if (background.equals("orange")) {
                    apron.setImageResource(R.drawable.apronorange);
                    hat.setImageResource(R.drawable.hatorange);
                }
                if (background.equals("pink")) {
                    apron.setImageResource(R.drawable.apronpink);
                    hat.setImageResource(R.drawable.hatpink);
                }
                if (background.equals("yellow")) {
                    apron.setImageResource(R.drawable.apronyellow);
                    hat.setImageResource(R.drawable.hatyellow);
                }
            }
        }
    }
}
