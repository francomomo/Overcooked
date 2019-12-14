package com.example.overcooked.LevelTwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.overcooked.Game;
import com.example.overcooked.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Customization page for level2
 */
public class LevelTwoCustomization extends AppCompatActivity implements Customization {

    private Intent GameIntent;
    private String username;
    final private static List<String> backgrounds = new ArrayList<String>() {{
        add("sky");
        add("tableware");
        add("grass");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two_customization);
        Intent intent = getIntent();
        Bundle information = intent.getExtras();
        if(information != null) {
            try {
                username = information.getString("username");
                intent.putExtra("name", username);
                if (information.getString("bonusleveltwo") != null) {
                    GameIntent = new Intent(this, BonusLevelTwoActivity.class);
                } else {
                    GameIntent = new Intent(this, LevelTwoMainActivity.class);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String L2DIFF = (String)Game.retrieveInfo(username, Game.retrievedValue.L2DIFF);
        GameIntent.putExtra("difficulty", L2DIFF);
        String L2BG = (String)Game.retrieveInfo(username, Game.retrievedValue.L2BG);
        GameIntent.putExtra("background", L2BG);
    }

    /**
     * getter for Backgrounds
     */
    public static List<String> getBackgrounds(){
        return backgrounds;
    }

    /**
     * Add background tag to GameIntent
     */
    @Override
    public void setBackground(View view) {
        String background = (String) view.getTag();
        Game.storeInfo(username, Game.retrievedValue.L2BG, background);
        GameIntent.putExtra("background", background);
    }

    /**
     * Add difficulty tag to GameIntent
     */
    @Override
    public void setDifficulty(View view) {
        String difficulty = (String) view.getTag();
        Game.storeInfo(username, Game.retrievedValue.L2DIFF, difficulty);
        GameIntent.putExtra("difficulty", difficulty);
    }

    /**
     * When the button pressed, go to LevelTwoMainActivity or BonusLevelTwoActivity
     */
    public void levelStart(View view) {
        GameIntent.putExtra("username", username);
        startActivity(GameIntent);
    }
}
