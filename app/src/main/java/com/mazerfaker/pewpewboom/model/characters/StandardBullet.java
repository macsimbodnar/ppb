package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class StandardBullet extends Character implements Bullet {

    public StandardBullet(Bitmap bitmap, float x, float y, int speed, int damage) {
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
