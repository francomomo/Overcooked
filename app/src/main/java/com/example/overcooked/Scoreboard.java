package com.example.overcooked;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scoreboard extends Game {

    private TextView userHighScore;

    private String username;
    final private List<Integer> highScores = new ArrayList<>();
    final private List<String> usernames = new ArrayList<>();
    final private List<String> scoreboard = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        getIntent();

        Intent intent = getIntent();
        Bundle name = intent.getExtras();

        if (name != null) {
            username = name.getString("username");
        }

        userHighScore = findViewById(R.id.userHighscore);
        userHighScore.setTextColor(Color.WHITE);
        Button backButton = findViewById(R.id.backButton);

        getValues();
        sortScores();
        combineUsernames();

        ListView listview = findViewById(R.id.highscoreList);
        ArrayAdapter adapter = new ArrayAdapter<>(Scoreboard.this, android.R.layout.simple_list_item_1, scoreboard);
        listview.setAdapter(adapter);

        backButton.setOnClickListener( //log out
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Scoreboard.this, com.example.overcooked.MainMenu.MainMenuActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    }
                }
        );
    }

    private void sortScores() {
        int length = highScores.size();
        for (int i = 0; i < length - 1; i++)
            for (int j = 0; j < length - i - 1; j++)
                if (highScores.get(j) < highScores.get(j + 1)) {
                    int temp = highScores.get(j);
                    highScores.set(j, highScores.get(j + 1));
                    highScores.set(j + 1, temp);
                    String temp2 = usernames.get(j);
                    usernames.set(j, usernames.get(j + 1));
                    usernames.set(j + 1, temp2);
                }
    }

    private void getValues() {
        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Overcooked");
        File[] files = directory.listFiles();
        String username;
        int points;

        if (files != null) {
            for (File file : files) {
                try {
                    JSONObject userFile = new JSONObject(readFile(file.getAbsolutePath()));
                    username = userFile.get("username").toString();
                    usernames.add(username);
                    points = Integer.parseInt(userFile.get("points").toString());
                    highScores.add(points);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void combineUsernames(){
        int numberOnList = 1;
        for (int i = 0; i < highScores.size(); i++) {
            String user = usernames.get(i);
            int points = highScores.get(i);
            StringBuilder text = new StringBuilder(numberOnList + ". " + user + "                " + points);
            scoreboard.add(text.toString());
            numberOnList++;
            if (username.equals(user)) {
                userHighScore.setText(text.toString());
            }
        }
    }

}
