package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;

import com.mazerfaker.pewpewboom.model.App;
import com.mazerfaker.pewpewboom.model.Hitbox;
import com.mazerfaker.pewpewboom.model.weapons.Weapon;


public class Character {

    public Character(Bitmap bitmap, Weapon weapon, int life, int speed) {
        _app = App.getInstance();
        _bitmap = bitmap;
        _life = life;
        _halfWidth = _bitmap.getWidth() / 2.0f;
        _halfHeight = _bitmap.getHeight() / 2.0f;
        _weapon = weapon;
        _speed = speed;
    }


    protected void initHitbox(float x, float y) {
        _x = x;
        _y = y;
        _hitbox = new Hitbox(_x, _y, _bitmap.getWidth(), _bitmap.getHeight());
    }


    protected Bitmap _bitmap;
    protected float _x;
    protected float _y;
    protected Hitbox _hitbox;
    protected int _life;
    protected Weapon _weapon;
    protected App _app;
    protected int _speed;

    protected float _halfWidth;
    protected float _halfHeight;
}
