package com.example.overcooked.LevelOne;

import java.util.ArrayList;
import java.util.List;

/* Factory to easily add new backgrounds for game levels.*/

public class BackgroundFactory {

    final private static List<String> backgroundList = new ArrayList<String>() {{
        add("supermarket");
        add("waterfall");
        add("hallway");
        add("cafe");
    }};
    private int currIndex;

    BackgroundFactory() {
        currIndex = 0;
    }

    static public List<String> getBackgroundList(){
        return backgroundList;
    }

    String newBackground(String currentBackground) {
        currIndex = backgroundList.indexOf(currentBackground);

        if (currIndex == -1) {
            return currentBackground;
        } else if (currIndex + 1 == backgroundList.size()) {
            return backgroundList.get(0);
        } else {
            return backgroundList.get(currIndex + 1);
        }
    }

}
