package com.mazerfaker.pewpewboom.model.weapons;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.mazerfaker.pewpewboom.model.Hitbox;

public class Bullet {

    public Bullet(Bitmap bitmap, float x, float y, int speed) {
        _bitmap = bitmap;
        _x = x - (bitmap.getWidth() / 2.0f);
        _y = y - bitmap.getHeight();
        _speed = speed;
        _hitbox = new Hitbox(_x, _y, _bitmap.getWidth(), _bitmap.getHeight());
        _dangerous = true;
    }


    public boolean update() {
        _y -= _speed;

        if((_y + _bitmap.getHeight()) < 0) {
            _dangerous = false;
        }

        return _dangerous;
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, _x, _y, null);
    }


    public boolean isValid() {
        return _dangerous;
    }

    public boolean isNotValid() {
        return !_dangerous;
    }

    private Bitmap _bitmap;
    private float _x;
    private float _y;
    private Hitbox _hitbox;
    private int _speed;
    private int _damage;
    private boolean _dangerous;
}
