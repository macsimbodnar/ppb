package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class StandardBullet extends Character implements Bullet {

    public StandardBullet(Bitmap bitmap, int x, int y, int speed, int damage) {
        super(bitmap, new ArrayList<Bitmap>(), null, 0, speed, 0);

        int initialX = x - (bitmap.getWidth() / 2);
        int initialY;
        if(speed > 0) {
            initialY = y - bitmap.getHeight();
        } else {
            initialY = y;
        }
        initHitbox(initialX, initialY);

        _damage = damage;
    }


    @Override
    public void update() {
        _y -= _speed;
        _hitbox.updateY(_y);
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, _x, _y, null);
    }


    @Override
    public int getDamage() {
        return _damage;
    }


    @Override
    public int getLifetime() {
        return -1;
    }


    private int _damage;
}
