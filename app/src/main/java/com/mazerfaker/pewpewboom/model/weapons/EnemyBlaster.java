package com.mazerfaker.pewpewboom.model.weapons;


import android.graphics.Bitmap;

import com.mazerfaker.pewpewboom.model.characters.StandardBullet;
import com.mazerfaker.pewpewboom.util.Constants;

public class EnemyBlaster extends BaseWeapon implements Weapon {


    public EnemyBlaster(Bitmap bitmap) {
        super(bitmap, Constants.ENEMY_BLASTER_FIRE_RATE,
                Constants.ENEMY_BLASTER_BULLET_SPEED, Constants.ENEMY_BLASTER_DAMAGE);
    }


    @Override
    public StandardBullet fire(int x, int y) {
        tick();

        if(_fireCount != 0) {
            return null;
        }

        return new StandardBullet(_bulletBitmap, x, y, _bulletSpeed, _damage);
    }
}
