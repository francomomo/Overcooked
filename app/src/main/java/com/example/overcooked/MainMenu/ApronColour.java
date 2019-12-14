package com.example.overcooked.MainMenu;

import java.util.ArrayList;
import java.util.List;

abstract class ApronColour {

    final private static ArrayList<String> colours = new ArrayList<String>() {{
        add("blue");
        add("pink");
        add("yellow");
        add("white");
        add("black");
        add("orange");
        add("green");
    }};

    /**
     * getter for the ArrayList colours
     *
     * @return ArrayList colours
     */
    static List<String> getColours(){
        return colours;
    }
}
