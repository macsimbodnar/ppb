package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bullet extends Character {

    public Bullet(Bitmap bitmap, float x, float y, int speed, int damage) {
        super(bitmap, null, 0, speed);

        float initialX = x - (bitmap.getWidth() / 2.0f);
        float initialY;
        if(speed > 0) {
            initialY = y - bitmap.getHeight();
        } else {
            initialY = y;
        }
        initHitbox(initialX, initialY);

        _damage = damage;
    }


    public void update() {
        _y -= _speed;
        _hitbox.updateY(_y);
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, _x, _y, null);
    }


    public int getDamage() {
        return _damage;
    }


    private int _damage;
}
