package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.mazerfaker.pewpewboom.model.weapons.Weapon;
import com.mazerfaker.pewpewboom.util.Constants;

import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class Enemy extends Character implements Drawable {

    public Enemy(Bitmap bitmap, List<Bitmap> animation, Weapon weapon, int life, int points) {
        super(bitmap, animation, weapon, life, Constants.SIMPLE_ENEMY_SPEED, points);

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
        super.draw(canvas);
    }


    @Override
    public boolean drawAnimation(Canvas canvas) {
       return super.drawAnimation(canvas);
    }


    @Override
    public int getPoints() {
        return _points;
    }


    public Enemy getCopy() {
        return new Enemy(_bitmap, _animationFrames, _weapon, _life, _points);
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
