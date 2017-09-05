package com.mazerfaker.pewpewboom.model;

import android.graphics.Bitmap;

public class Hitbox {

    public Hitbox(Bitmap bitmap, int life) {
        _bitmap = bitmap;
        _life = life;
    }

    protected Bitmap _bitmap;
    protected int _life;

}
