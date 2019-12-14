package com.example.overcooked;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.overcooked.MainMenu.MainMenuActivity;

public class GeneralFailActivity extends Game {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_fail);
        username = retrieveUsername();

        Button mainMenu = findViewById(R.id.mainMenuButton);

        mainMenu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startIntent(GeneralFailActivity.this, MainMenuActivity.class,username);
                    }
                }
        );
    }
}
