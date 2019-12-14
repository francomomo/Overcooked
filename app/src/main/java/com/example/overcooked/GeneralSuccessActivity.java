package com.example.overcooked;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.overcooked.LevelThree.LevelThreeMainActivity;
import com.example.overcooked.LevelThree.LevelThreeSuccessActivity;
import com.example.overcooked.LevelTwo.LevelTwoCustomization;
import com.example.overcooked.MainMenu.MainMenuActivity;

public class GeneralSuccessActivity extends Game {

    private String username;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_success);
        username = retrieveUsername();
        level = (int)retrieveInfo(username, retrievedValue.LEVEL);

        Button mainMenu = findViewById(R.id.mainMenuButton);
        Button next = findViewById(R.id.nextLevelButton);

        mainMenu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startIntent(GeneralSuccessActivity.this, MainMenuActivity.class,username);
                    }
                }
        );

        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        nextLevel();
                    }
                }
        );
    }

    private void nextLevel() {
        Intent intent;
        if (level == 2) {
            intent = new Intent(this, LevelTwoCustomization.class);
        } else if (level == 3) {
            intent = new Intent(this, LevelThreeMainActivity.class);
        } else {
            intent = new Intent(this, LevelThreeSuccessActivity.class);
        }
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
