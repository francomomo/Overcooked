package com.example.overcooked.LevelThree;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BadFallingIngredient extends FallingIngredient {

    public BadFallingIngredient(int startX, int startY) {
        super(startX, startY);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawRect(super.getRectangle(), paint);
    }
}
