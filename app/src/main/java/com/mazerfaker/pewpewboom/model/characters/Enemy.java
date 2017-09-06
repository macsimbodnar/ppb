package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.mazerfaker.pewpewboom.model.weapons.Weapon;
import com.mazerfaker.pewpewboom.util.Constants;

import java.util.Random;


public class Enemy extends Character implements Drawable {

    public Enemy(Bitmap bitmap, Weapon weapon, int life) {
        super(bitmap, weapon, life, Constants.SIMPLE_ENEMY_SPEED);

        Random random = new Random();

        int max = _app.getWindowWidth() - bitmap.getWidth();
        float initialX = random.nextInt(max);
        float initialY = - bitmap.getHeight();

        initHitbox(initialX, initialY);
        _height = bitmap.getHeight();
    }

    @Override
    public void update() {
        _y += _speed;
        _hitbox.updateY(_y);
        fire();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, _x, _y, null);
    }


    private void fire() {
        if(_weapon == null) {
            return;
        }

        Bullet bullet = _weapon.fire(_x + _halfWidth, _y + _height);
        if(bullet != null) {
            _app.addEnemyBullet(bullet);
        }
    }


    private float _height;
}
