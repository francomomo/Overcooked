package com.example.overcooked.Ingredient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.overcooked.Game;
import com.example.overcooked.R;

public class Beef extends Ingredients {
    private String name;
    private Bitmap image;
    private int size;


    Beef(Game context) {
        super(context);
        setName();
        setSize();
        setImage();
    }

    @Override
    public void setImage() {
        image = BitmapFactory.decodeResource(super.mContext.getResources(), R.drawable.beef);
    }

    @Override
    public void setName() {
        name = "beef";
    }

    @Override
    public void setSize() {
        size = 23;
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

