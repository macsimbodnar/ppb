package com.mazerfaker.pewpewboom.model.weapons;


import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.mazerfaker.pewpewboom.model.characters.Bullet;
import com.mazerfaker.pewpewboom.model.characters.Character;
import com.mazerfaker.pewpewboom.util.Constants;

public class DefaulMegaWeapon extends BaseMegaWeapon implements Weapon {

    public DefaulMegaWeapon(Bitmap bitmap) {
        super(bitmap, Constants.DEFAULT_MEGA_W_DAMAGE);
    }


    @Override
    public Bullet fire(float x, float y) {
        return new StandardMegaBullet(_bulletBitmap, x, y, _damage);
    }

}


class StandardMegaBullet extends Character implements Bullet {


    public StandardMegaBullet(Bitmap bitmap, float x, float y, int damage) {
        super(bitmap, null, 0, 0);
        _damage = damage;
        _lifetime = Constants.DEFAULT_MEGA_W_LIFETAIME;

        float initialX = x - (bitmap.getWidth() / 2.0f);
        float initialY = y - bitmap.getHeight();
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
