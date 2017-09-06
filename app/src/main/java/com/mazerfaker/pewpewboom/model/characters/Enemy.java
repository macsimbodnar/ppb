package com.mazerfaker.pewpewboom.model.characters;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.mazerfaker.pewpewboom.model.weapons.Weapon;
import com.mazerfaker.pewpewboom.util.Constants;


public class Enemy extends Character implements Drawable {

    public Enemy(Bitmap bitmap, Weapon weapon, int life) {
        super(bitmap, weapon, life);

        float initialX = (((float) _app.getWindowWidth()) / 2.0f) - (_bitmap.getWidth() / 2.0f);
        float initialY = ((float) _app.getWindowHeght()) - _bitmap.getHeight() - Constants.SHIP_BOTTOM_PADDING;

        initHitbox(initialX, initialY);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(_bitmap, _x, _y, null);
    }


    private void fire() {
        if(_weapon == null) {
            return;
        }

        Bullet bullet = _weapon.fire(_x + _halfWidth, _y);
        if(bullet != null) {
            _app.addEnemyBullet(bullet);
        }
    }
}
