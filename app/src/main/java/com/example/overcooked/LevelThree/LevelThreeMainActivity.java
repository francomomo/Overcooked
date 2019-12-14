package com.example.overcooked.LevelThree;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.overcooked.Game;
import com.example.overcooked.R;

/**
 * The main activity of level three game
 */
public class LevelThreeMainActivity extends Game {

    /**
     * whether the background color button has been clicked
     */
    private boolean backgroundColorClicked;
    /**
     * whether the background color button has been clicked
     */
    private boolean playerColorClicked;
    /**
     * whether the difficulty button has been clicked
     */
    private boolean difficultyClicked;

    private String username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow()
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_level_three);

        Intent intent = getIntent();
        Bundle stats = intent.getExtras();
        username = stats.getString("username");

        Button b_color_gray = findViewById(R.id.button_b_gray);
        Button b_color_unknown = findViewById(R.id.button_b_unknown);
        Button p_color_black = findViewById(R.id.button_p_black);
        Button p_color_blue = findViewById(R.id.button_p_blue);
        Button d_easy = findViewById(R.id.button_easy);
        Button d_hard = findViewById(R.id.button_hard);

        backgroundColorClicked = false;
        playerColorClicked = false;
        difficultyClicked = false;

        Button startGame = findViewById(R.id.startGame);

        final Intent INTENT_PASSED = new Intent(LevelThreeMainActivity.this, LevelThreeActivity.class);

        d_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficultyClicked = true;
                INTENT_PASSED.putExtra("difficulty", "Easy");
                storeInfo(username, retrievedValue.L3DIFF, "Easy");
            }
        });
        d_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                difficultyClicked = true;
                INTENT_PASSED.putExtra("difficulty", "Hard");
                storeInfo(username, retrievedValue.L3DIFF, "Hard");
            }
        });
        b_color_gray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColorClicked = true;
                INTENT_PASSED.putExtra("background_color", Color.LTGRAY);
                storeInfo(username, retrievedValue.L3BG, Color.LTGRAY);
            }
        });

        b_color_unknown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundColorClicked = true;
                INTENT_PASSED.putExtra("background_color", Color.BLUE);
                storeInfo(username, retrievedValue.L3BG, Color.BLUE);
            }
        });

        p_color_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerColorClicked = true;
                INTENT_PASSED.putExtra("player_color", Color.BLACK);
                storeInfo(username, retrievedValue.L3PL, Color.BLACK);
            }
        });

        p_color_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerColorClicked = true;
                INTENT_PASSED.putExtra("player_color", Color.rgb(29, 31, 80));
                storeInfo(username, retrievedValue.L3PL, Color.rgb(29, 31, 80));
            }
        });

        INTENT_PASSED.putExtra("username", username);
        if (!backgroundColorClicked) {
            INTENT_PASSED.putExtra("background_color", (int) retrieveInfo(username, retrievedValue.L3BG));
        }
        if (!playerColorClicked) {
            INTENT_PASSED.putExtra("player_color", (int) retrieveInfo(username, retrievedValue.L3PL));
        }
        if (!difficultyClicked) {
            String diff = (String) retrieveInfo(username, retrievedValue.L3DIFF);
            INTENT_PASSED.putExtra("difficulty", diff);
        }

        startGame.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(INTENT_PASSED);
                    }
                }
        );
    }
}
