package com.example.overcooked.Ingredient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.overcooked.Game;
import com.example.overcooked.R;

public class Vegetable extends Ingredients {

    private String name;
    private Bitmap image;
    private int size;

    Vegetable(Game context) {
        super(context);
        setName();
        setSize();
        setImage();
    }

    @Override
    public void setImage() {
        image = BitmapFactory.decodeResource(super.mContext.getResources(), R.drawable.vegetable);
    }

    @Override
    public void setName() {
        name = "vegetable";
    }

    @Override
    public void setSize() {
        size = 55;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public Bitmap getImage() {
        return image;
    }

    @Override
    public String getName() {
        return name;
    }
}
