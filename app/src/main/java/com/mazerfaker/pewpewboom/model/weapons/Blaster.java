package com.mazerfaker.pewpewboom.model.weapons;

import android.graphics.Bitmap;

import com.mazerfaker.pewpewboom.model.characters.Bullet;
import com.mazerfaker.pewpewboom.util.Constants;

public class Blaster implements Weapon {


    public Blaster(Bitmap bitmap) {
        _bulletBitmap = bitmap;
        _fireRate = Constants.BLASTER_FIRE_RATE;
        _bulletSpeed = Constants.BLASTER_BULLET_SPEED;
        _fireCount = 0;
    }


    @Override
    public Bullet fire(float x, float y) {
        tick();

        if(_fireCount != 0) {
            return null;
        }

        return new Bullet(_bulletBitmap, x, y, _bulletSpeed);
    }


    private void tick() {
        _fireCount++;

        if(_fireCount == _fireRate) {
            _fireCount = 0;
        }
    }

    private Bitmap _bulletBitmap;
    private int _fireRate;
    private int _bulletSpeed;
    private int _fireCount;
}
