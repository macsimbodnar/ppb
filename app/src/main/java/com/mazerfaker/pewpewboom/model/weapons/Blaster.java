package com.mazerfaker.pewpewboom.model.weapons;

import android.graphics.Bitmap;

import com.mazerfaker.pewpewboom.model.characters.StandardBullet;
import com.mazerfaker.pewpewboom.util.Constants;

public class Blaster extends BaseWeapon implements Weapon {


    public Blaster(Bitmap bitmap) {
        super(bitmap, Constants.BLASTER_FIRE_RATE,
                Constants.BLASTER_BULLET_SPEED, Constants.BLASTER_DAMAGE);
    }


    @Override
    public StandardBullet fire(float x, float y) {
        tick();

        if(_fireCount != 0) {
            return null;
        }

        return new StandardBullet(_bulletBitmap, x, y, _bulletSpeed, _damage);
    }
}
