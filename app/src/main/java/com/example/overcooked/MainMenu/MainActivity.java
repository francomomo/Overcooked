package com.example.overcooked.MainMenu;

import androidx.annotation.Nullable;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.overcooked.Game;
import com.example.overcooked.R;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class MainActivity extends Game {
    //main file

    private EditText username, password;
    private TextView status;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newAccount, logIn;
        TextView userTag, passTag;

        newAccount = findViewById(R.id.newAccount);
        logIn = findViewById(R.id.login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        status = findViewById(R.id.status);
        userTag = findViewById(R.id.usernameTag);
        passTag = findViewById(R.id.passwordTag);
        userTag.setTextColor(Color.WHITE);
        passTag.setTextColor(Color.WHITE);
        status.setTextColor(Color.WHITE);

        // asking for permission to enter the files
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }

        //creates a folder in the devices files
        File directory = new File(Environment.getExternalStorageDirectory() + "/Overcooked");
        if (directory.isDirectory()) {
            System.out.println("Overcooked Folder Already Exists");
        } else {
            directory.mkdirs();
            System.out.println("Overcooked Folder Created");
        }

        newAccount.setOnClickListener( // when new account button is pressed
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        user = username.getText().toString();
                        String pass = password.getText().toString();
                        if (!user.equals("") && !pass.equals("")) {
                            if (!fileSearch(user)) { //checks if the user exists already or not
                                createNew(user, pass);
                                startIntent(MainActivity.this, NewUserLogin.class, user);
                            } else {
                                status.setText(R.string.usernameExists);
                            }
                        } else {
                            status.setText(R.string.isEmpty);
                        }
                    }
                });

        logIn.setOnClickListener( //if log in button pressed
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String user = username.getText().toString();
                        String pass = password.getText().toString();
                        if (!user.equals("") && !pass.equals("")) {
                            if (fileSearch(user) && checkPassword(user, pass)) {
                                startIntent(MainActivity.this, MainMenuActivity.class, user);
                                status.setText(R.string.passCorrect);
                            } else {
                                status.setText(R.string.passWrong);
                            }
                        } else {
                            status.setText(R.string.isEmpty);
                        }
                    }
                }
        );
    }

    /**
     * checks if password entered is the correct password for that account
     *
     * @param username username of account
     * @param password password of account
     */
    private boolean checkPassword(String username, String password) {
        return Objects.requireNonNull(retrieveInfo(username, retrievedValue.PASSWORD)).equals(password);
    }

    /**
     * searching through phone's internal files
     *
     * @param username username of account.
     */
    private boolean fileSearch(String username) {
        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Overcooked");
        File[] files = directory.listFiles();

        if (files == null) return false;

        for (File file : files) {
            if (file.getName().startsWith(username)) { //if username is found in logIn
                return true;
            }
        }
        return false;
    }

    private void createNew(String user, String pass) {
        JSONObject tempJSON = new JSONObject();
        try {
            tempJSON.put("username", user);
            tempJSON.put("password", pass);
            tempJSON.put("lives", 3);
            tempJSON.put("points", 0);
            tempJSON.put("level", 1);
            tempJSON.put("MAINMENU", "white");
            tempJSON.put("L1BG", "supermarket");
            tempJSON.put("L2BG", "sky");
            tempJSON.put("L2DIFF","easy");
            tempJSON.put("L3BG", Color.GRAY);
            tempJSON.put("L3PL", Color.BLACK);
            tempJSON.put("L3DIFF", "Easy");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String fileName = user + ".json";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Overcooked", fileName);

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(tempJSON.toString().getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //checks for username of file
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 42 && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                Uri uri = data.getData();
                if (uri != null) {
                    String path = uri.getPath();
                    if (path != null) {
                        path = path.substring(path.indexOf(":") + 1);
                        status.setText(readFile(path));
                    }
                }
            }
        }
    }
}
