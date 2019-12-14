package com.example.overcooked.LevelTwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.overcooked.LevelThree.LevelThreeMainActivity;
import com.example.overcooked.MainMenu.MainMenuActivity;
import com.example.overcooked.R;

public class LevelTwoSuccessActivity extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two_success);
        Intent intent = getIntent();
        Bundle name = intent.getExtras();
        if (name != null) {
            try {
                username = name.getString("username");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * When this method is called, back to main menu
     */
    public void backToMainMenu(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    /**
     * When this method is called, go to level three
     */
    public void goToLevelThree(View view) {
        Intent intent = new Intent(this, LevelThreeMainActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    /**
     * When this method is called, go to bonus level
     */
    public void navigateBonus(View view) {
        Intent intent = new Intent(this, BonusLevelTwoInstructionActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
