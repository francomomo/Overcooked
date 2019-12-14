package com.example.overcooked;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public abstract class Game extends AppCompatActivity {

    /**
     * reads JSONFile
     *
     * @param input the username of the user.
     */
    protected static String readFile(String input) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader file = new BufferedReader(new FileReader(input));
            String line;
            while ((line = file.readLine()) != null) {
                content.append(line);
            }
            file.close();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public enum retrievedValue {
        LIVES, POINTS, LEVEL, PASSWORD, L1BG, L2BG,
        L2DIFF, L3BG, L3PL, L3DIFF, MAINMENU
    }

    /**
     * retrieving the user's info
     *
     * @param username username of account.
     * @param mode the variable you want to retrieve
     */
    public static Object retrieveInfo(String username, retrievedValue mode) {
        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Overcooked");
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().startsWith(username)) { //if username is found in logIn
                    try {
                        JSONObject userFile = new JSONObject(readFile(file.getAbsolutePath()));
                        System.out.println(userFile);
                        if (mode == retrievedValue.LIVES)
                            return Integer.parseInt(userFile.get("lives").toString());
                        if (mode == retrievedValue.POINTS)
                            return Integer.parseInt(userFile.get("points").toString());
                        if (mode == retrievedValue.LEVEL)
                            return Integer.parseInt(userFile.get("level").toString());
                        if (mode == retrievedValue.PASSWORD)
                            return userFile.get("password").toString();
                        if (mode == retrievedValue.MAINMENU)
                            return userFile.get("MAINMENU").toString();
                        if (mode == retrievedValue.L1BG) return userFile.get("L1BG").toString();
                        if (mode == retrievedValue.L2BG) return userFile.get("L2BG").toString();
                        if (mode == retrievedValue.L2DIFF) return userFile.get("L2DIFF").toString();
                        if (mode == retrievedValue.L3BG) return userFile.get("L3BG");
                        if (mode == retrievedValue.L3PL) return userFile.get("L3PL");
                        if (mode == retrievedValue.L3DIFF) return userFile.get("L3DIFF").toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * stores the new stats for the user
     *
     * @param username username of account.
     */
    public static void storeInfo (String username, retrievedValue mode, Object value){
        File directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Overcooked");
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().startsWith(username)) { //if username is found in logIn
                    try {
                        JSONObject userFile = new JSONObject(readFile(file.getAbsolutePath()));
                        if (mode == retrievedValue.LEVEL) userFile.put("level", value);
                        if (mode == retrievedValue.LIVES) userFile.put("lives", value);
                        if (mode == retrievedValue.POINTS) userFile.put("points", value);
                        if (mode == retrievedValue.L1BG) userFile.put("L1BG", value);
                        if (mode == retrievedValue.L2BG) userFile.put("L2BG", value);
                        if (mode == retrievedValue.L2DIFF) userFile.put("L2DIFF", value);
                        if (mode == retrievedValue.L3BG) userFile.put("L3BG", value);
                        if (mode == retrievedValue.L3PL) userFile.put("L3PL", value);
                        if (mode == retrievedValue.MAINMENU) userFile.put("MAINMENU", value);
                        if (mode == retrievedValue.L3DIFF) userFile.put("L3DIFF", value);
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(userFile.toString().getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     *
     * @return returns the username from the previous intent
     */
    protected String retrieveUsername(){
        Intent intent = getIntent();
        Bundle name = intent.getExtras();
        String username = "";

        if (name != null){
            username = name.getString("username");
        }
        return username;
    }

    /**
     *
     * @param current current context
     * @param latter the class that the context is going to
     * @param username username of the account
     */
    protected void startIntent(Context current, Class latter, String username){
        Intent intent = new Intent(current, latter);
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
