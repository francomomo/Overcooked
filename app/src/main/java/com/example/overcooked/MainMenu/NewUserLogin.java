package com.example.overcooked.MainMenu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.overcooked.LevelOne.LevelOneActivity;
import com.example.overcooked.R;

public class NewUserLogin extends MainActivity {
    // user login
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        getIntent();

        TextView newUser = findViewById(R.id.newUser);
        Button startNew = findViewById(R.id.startNew);
        Button logOut = findViewById(R.id.logout);
        newUser.setTextColor(Color.WHITE);

        username = retrieveUsername();
        newUser.setText(username);

        startNew.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startIntent(NewUserLogin.this, LevelOneActivity.class, username);
                    }
                }
        );

        logOut.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(NewUserLogin.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
