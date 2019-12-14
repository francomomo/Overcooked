package com.example.overcooked.MainMenu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.overcooked.Game;
import com.example.overcooked.LevelOne.BackgroundFactory;
import com.example.overcooked.LevelTwo.LevelTwoCustomization;
import com.example.overcooked.R;

public class Settings extends Game {

    private String username;
    private String l1BackgroundFinal;
    private String l2BackgroundFinal;
    private int l3BackgroundFinal;
    private int l3PlayerFinal;
    private String l3Diff;
    private String mMFinal;
    private String l2Diff = "easy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getIntent();

        Button back = findViewById(R.id.back);
        Button save = findViewById(R.id.save);
        Button easy = findViewById(R.id.easy);
        Button medium = findViewById(R.id. medium);
        Button hard = findViewById(R.id.hard);
        Button grey = findViewById(R.id.grey);
        Button question = findViewById(R.id.question);
        Button blue = findViewById(R.id.blue);
        Button black = findViewById(R.id.black);
        Button easy3 = findViewById(R.id.easy3);
        Button hard3 = findViewById(R.id.hard3);
        Spinner l1Background = findViewById(R.id.l1backgroundspinner);
        Spinner l2Background = findViewById(R.id.l2background);
        Spinner mainMenuSpinner = findViewById(R.id.mainmenuspinner);
        username = retrieveUsername();

        ArrayAdapter l1BG = new ArrayAdapter<>(Settings.this, android.R.layout.simple_list_item_1,  BackgroundFactory.getBackgroundList());
        l1BG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        l1Background.setAdapter(l1BG);

        ArrayAdapter l2BG = new ArrayAdapter<>(Settings.this, android.R.layout.simple_list_item_1, LevelTwoCustomization.getBackgrounds());
        l2BG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        l2Background.setAdapter(l2BG);

        ArrayAdapter mM = new ArrayAdapter<>(Settings.this, android.R.layout.simple_list_item_1, ApronColour.getColours());
        mM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mainMenuSpinner.setAdapter(mM);

        l1Background.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                l1BackgroundFinal = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                l1BackgroundFinal= "supermarket";
            }
        });

        l2Background.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                l2BackgroundFinal = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                l2BackgroundFinal = "sky";
            }
        });

        mainMenuSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mMFinal = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { mMFinal = "white";
            }
        });

        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startIntent(Settings.this, MainMenuActivity.class, username);
                    }
                });

        save.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        savingButton();
                        startIntent(Settings.this, MainMenuActivity.class, username);
                    }
                }
        );

        easy.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        l2Diff = "easy";
                    }
                }
        );

        medium.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        l2Diff = "medium";
                    }
                }
        );

        hard.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        l2Diff = "hard";
                    }
                }
        );

        grey.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        l3BackgroundFinal = Color.LTGRAY;
                    }
                }
        );

        question.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        l3BackgroundFinal = Color.BLUE;
                    }
                }
        );

        blue.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        l3PlayerFinal = Color.rgb(29, 31, 80);
                    }
                }
        );

        black.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        l3PlayerFinal = Color.BLACK;
                    }
                }
        );

        easy3.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        l3Diff = "Easy";
                    }
                }
        );

        hard3.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        l3Diff = "Hard";
                    }
                }
        );
    }

    private void savingButton(){
        storeInfo(username, retrievedValue.L1BG, l1BackgroundFinal);
        storeInfo(username, retrievedValue.L2BG, l2BackgroundFinal);
        storeInfo(username, retrievedValue.L3BG, l3BackgroundFinal);
        storeInfo(username, retrievedValue.L3PL, l3PlayerFinal);
        storeInfo(username, retrievedValue.MAINMENU, mMFinal);
        storeInfo(username, retrievedValue.L2DIFF, l2Diff);
        storeInfo(username, retrievedValue.L3DIFF, l3Diff);

    }
}
