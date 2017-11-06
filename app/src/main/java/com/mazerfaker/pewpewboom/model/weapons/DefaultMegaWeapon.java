package com.mazerfaker.pewpewboom.model.weapons;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.mazerfaker.pewpewboom.model.characters.Bullet;
import com.mazerfaker.pewpewboom.model.characters.Character;
import com.mazerfaker.pewpewboom.util.Constants;

import java.util.ArrayList;

public class DefaultMegaWeapon extends BaseMegaWeapon implements Weapon {

    public DefaultMegaWeapon(Bitmap bitmap) {
        super(bitmap, Constants.DEFAULT_MEGA_W_DAMAGE);
    }


    @Override
    public Bullet fire(int x, int y) {
        return new StandardMegaBullet(_bulletBitmap, x, y, _damage);
    }

}


class StandardMegaBullet extends Character implements Bullet {


    public StandardMegaBullet(Bitmap bitmap, int x, int y, int damage) {
        super(bitmap, new ArrayList<Bitmap>(), null, 0, 0, 0);
        _damage = damage;
        _lifetime = Constants.DEFAULT_MEGA_W_LIFETAIME;

        int initialX = x - (bitmap.getWidth() / 2);
        int initialY = y - bitmap.getHeight();
        initHitbox(initialX, initialY);
    }

    @Override
    public void update() {
        _lifetime--;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, _x, _y, null);
    }

    @Override
    public int getDamage() {
        return _damage;
    }


    @Override
    public int getLifetime() {
        return _lifetime;
    }


    private int _damage;
    private int _lifetime;
}
