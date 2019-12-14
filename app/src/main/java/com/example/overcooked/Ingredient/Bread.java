package com.example.overcooked.Ingredient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.overcooked.Game;
import com.example.overcooked.R;

public class Bread extends Ingredients {
    private String name;
    private Bitmap image;
    private int size;

    Bread(Game context) {
        super(context);
        setName();
        setSize();
        setImage();
    }

    @Override
    public void setImage() {
        image = BitmapFactory.decodeResource(super.mContext.getResources(), R.drawable.bread2);
    }

    @Override
    public void setName() {
        name = "bread";
    }

    @Override
    public void setSize() {
        size = 55;
    }

    public int getSize() {
        return size;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
