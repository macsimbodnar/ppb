package com.mazerfaker.pewpewboom.model.weapons;


import android.graphics.Bitmap;

public class BaseMegaWeapon {

    public BaseMegaWeapon(Bitmap bitmap, int damage) {
        _bulletBitmap = bitmap;
        _damage = damage;
    }


    protected Bitmap _bulletBitmap;
    protected int _damage;
}
