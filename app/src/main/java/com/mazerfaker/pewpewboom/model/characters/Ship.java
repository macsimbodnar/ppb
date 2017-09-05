package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import com.mazerfaker.pewpewboom.model.App;
import com.mazerfaker.pewpewboom.model.Hitbox;
import com.mazerfaker.pewpewboom.util.Constants;

public class Ship implements Character {

    private static final String TAG = "Ship";


    public Ship(Bitmap bitmap) {
        _app = App.getInstance();
        _bitmap = bitmap;
        _life = Constants.SHIP_LIFE;
        _x = (((float) _app.getWindowWidth()) / 2.0f) - (_bitmap.getWidth() / 2.0f);
        _y = ((float) _app.getWindowHeght()) - _bitmap.getHeight(); // - Constants.SHIP_BOTTOM_PADDING;
        _hitbox = new Hitbox(_x, _y, _bitmap.getWidth(), _bitmap.getHeight());

        Log.d(TAG, "LAYOUT W: " + _app.getWindowWidth() + " H: " + _app.getWindowHeght());
        Log.d(TAG, "BITMAP W: " + _bitmap.getWidth() + " H: " + _bitmap.getHeight());
        Log.d(TAG, "X: " + _x + " Y: " + _y);
    }


    @Override
    public void update() {

    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, _x, _y, null);
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


    private Bitmap _bitmap;
    private float _x;
    private float _y;
    private Hitbox _hitbox;
    private int _life;
    private App _app;
}
