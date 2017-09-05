package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.mazerfaker.pewpewboom.model.Hitbox;
import com.mazerfaker.pewpewboom.model.characters.Character;
import com.mazerfaker.pewpewboom.util.Constants;

public class Ship extends Hitbox implements Character {

    public Ship(Bitmap bitmap) {
        super(bitmap, Constants.SHIP_LIFE);
    }


    @Override
    public void update() {

    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, 0, 0, null);
    }


    @Override
    public int getLife() {
        return _life;
    }


    @Override
    public int takeDamage(int damage) {
        _life -= damage;
        return (_life < 0) ? 0 : _life;
    }
}
