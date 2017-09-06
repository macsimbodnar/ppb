package com.mazerfaker.pewpewboom.model.weapons;


import android.graphics.Bitmap;

import com.mazerfaker.pewpewboom.model.characters.Bullet;
import com.mazerfaker.pewpewboom.util.Constants;

public class EnemyBlaster extends BaseWeapon implements Weapon {


    public EnemyBlaster(Bitmap bitmap) {
        super(bitmap, Constants.ENEMY_BLASTER_FIRE_RATE, Constants.ENEMY_BLASTER_BULLET_SPEED);
    }


    @Override
    public Bullet fire(float x, float y) {
        tick();

        if(_fireCount != 0) {
            return null;
        }

        return new Bullet(_bulletBitmap, x, y, _bulletSpeed);
    }
}
