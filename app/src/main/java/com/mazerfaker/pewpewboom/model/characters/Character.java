package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.RectF;

import com.mazerfaker.pewpewboom.controller.App;
import com.mazerfaker.pewpewboom.model.Hitbox;
import com.mazerfaker.pewpewboom.model.weapons.Weapon;


public class Character {

    public Character(Bitmap bitmap, Weapon weapon, int life, int speed, int points) {
        _app = App.getInstance();
        _bitmap = bitmap;
        _life = life;
        _halfWidth = _bitmap.getWidth() / 2.0f;
        _halfHeight = _bitmap.getHeight() / 2.0f;
        _weapon = weapon;
        _speed = speed;
        _points = points;
    }


    protected void initHitbox(float x, float y) {
        _x = x;
        _y = y;
        _hitbox = new Hitbox(_x, _y, _bitmap.getWidth(), _bitmap.getHeight());
    }


    public RectF getHitbox() {
        return _hitbox.getRect();
    }


    public boolean hit(int damage) {
        _life -= damage;

        return _life <= 0;
    }

    protected Bitmap _bitmap;
    protected float _x;
    protected float _y;
    protected Hitbox _hitbox;
    protected int _life;
    protected Weapon _weapon;
    protected App _app;
    protected int _speed;
    protected int _points;

    protected float _halfWidth;
    protected float _halfHeight;
}
