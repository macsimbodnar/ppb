package com.mazerfaker.pewpewboom.model.weapons;


import android.graphics.Bitmap;

public class BaseWeapon {

    public BaseWeapon(Bitmap bitmap, int fireRate, int bulletSpeed, int damage) {
        _bulletBitmap = bitmap;
        _fireRate = fireRate;
        _bulletSpeed = bulletSpeed;
        _fireCount = -1;
        _damage = damage;
    }


    protected void tick() {
        _fireCount++;

        if(_fireCount == _fireRate) {
            _fireCount = 0;
        }
    }


    protected Bitmap _bulletBitmap;
    protected int _fireRate;
    protected int _bulletSpeed;
    protected int _fireCount;
    protected int _damage;
}
