package com.example.overcooked.LevelOne;

import java.util.HashMap;

class ScoreFactory {

    final private HashMap<String, Integer> scores;

    ScoreFactory() {
        scores = new HashMap<>();

        scores.put("bread", 3);
        scores.put("cheese", 4);
        scores.put("lettuce", 5);
        scores.put("meat", 6);
        scores.put("tomato", 7);
    }

    int seeScore(String food) {
        if (scores.containsKey(food)) {
            return scores.get(food);
        } else {
            return 0;
        }
    }

}
