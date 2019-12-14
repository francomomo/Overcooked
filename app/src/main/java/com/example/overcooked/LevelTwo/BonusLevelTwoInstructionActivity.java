package com.example.overcooked.LevelTwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.overcooked.R;

public class BonusLevelTwoInstructionActivity extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonus_level_two_instruction);
        Intent intent = getIntent();
        Bundle information = intent.getExtras();

        if (information != null) {
            try {
                username = information.getString("username");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Go to customization page
     */
    public void customizeBonusLevel(View view) {
        Intent intent = new Intent(this, LevelTwoCustomization.class);
        intent.putExtra("bonusleveltwo", "bonus");
        intent.putExtra("username", username);
        startActivity(intent);
    }

    /**
     * Start bonus level with default difficulty "easy" and background "sky"
     */
    public void startBonusLevel(View view) {
        Intent intent = new Intent(this, BonusLevelTwoActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("difficulty", "easy");
        intent.putExtra("background", "sky");
        startActivity(intent);
    }
}
