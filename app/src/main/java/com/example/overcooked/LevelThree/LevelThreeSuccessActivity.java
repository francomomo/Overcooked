package com.example.overcooked.LevelThree;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.overcooked.MainMenu.MainMenuActivity;
import com.example.overcooked.R;

import java.util.Objects;

public class LevelThreeSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_three_success);

        getIntent();
        String name = Objects.requireNonNull(getIntent().getExtras()).getString("username");

        final Intent intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra("username", name);

        Button backToMainMenu = findViewById(R.id.button);

        backToMainMenu.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(intent);
                    }
                }
        );
    }
}
