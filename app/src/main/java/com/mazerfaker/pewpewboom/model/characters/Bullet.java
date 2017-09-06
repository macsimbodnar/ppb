package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bullet extends Character {

    public Bullet(Bitmap bitmap, float x, float y, int speed) {
        super(bitmap, null, 0);

        float initialX = x - (bitmap.getWidth() / 2.0f);
        float initialY = y - bitmap.getHeight();
        initHitbox(initialX, initialY);

        _speed = speed;
        _dangerous = true;
    }


    public boolean update() {
        _y -= _speed;

        if((_y + _bitmap.getHeight()) < 0 || _y > _app.getWindowHeght()) {
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


    private int _speed;
    private int _damage;
    private boolean _dangerous;
}
